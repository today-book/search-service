package com.todaybook.searchservice.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // 가능한 가장 앞에서 실행
public class RequestTimingAspect {

  private static final Logger log = LoggerFactory.getLogger(RequestTimingAspect.class);

  // 요청(스레드) 단위로 타이밍 정보를 보관
  private static final ThreadLocal<TimingContext> CTX = new ThreadLocal<>();

  /**
   * 컨트롤러 진입점 - HTTP 요청의 시작과 끝을 감싸기 위한 포인트컷
   */
  @Pointcut(
      "within(@org.springframework.web.bind.annotation.RestController *) || " +
          "within(@org.springframework.stereotype.Controller *)"
  )
  private void anyController() {
  }

 /**
 * 시간 측정 대상
 * - Service / Repository
 * - infrastructure 하위 Component
 *   (단, Properties / Configuration 계열은 제외)
 */
@Pointcut(
    "within(@org.springframework.stereotype.Service *) || " +
    "within(@org.springframework.stereotype.Repository *) || " +
    "(" +
    "  within(com.todaybook.searchservice.infrastructure..*) && " +
    "  !within(*..*Properties) && " + // 클래스명 *Properties 제외
    "  !@within(org.springframework.boot.context.properties.ConfigurationProperties) && " +
    "  !within(@org.springframework.context.annotation.Configuration *)" +
    ")"
)
  private void timedTargets() {
  }

  /**
   * Controller 기준 AOP - 요청 시작 시 TimingContext 생성 - 요청 종료 시 전체 호출 시간 요약 로그 출력
   */
  @Around("anyController()")
  public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
    long startNs = System.nanoTime();
    boolean created = false;

    // 최초 컨트롤러 진입 시에만 컨텍스트 생성
    if (CTX.get() == null) {
      CTX.set(new TimingContext());
      created = true;
    }

    HttpServletRequest request = currentRequest();
    String uri = (request != null)
        ? request.getMethod() + " " + request.getRequestURI()
        : "(no-request)";

    try {
      return pjp.proceed();
    } finally {
      long totalMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

      // 최상위 컨트롤러에서만 로그 출력
      if (created) {
        TimingContext ctx = CTX.get();
        CTX.remove();

        // 느린 순으로 정렬
        List<TimingContext.Entry> entries = new ArrayList<>(ctx.entries.values());
        entries.sort(Comparator.comparingLong(TimingContext.Entry::totalMs).reversed());

        StringBuilder sb = new StringBuilder(512);
        sb.append("\n[REQUEST TIMING] ")
            .append(uri)
            .append(" | total=")
            .append(totalMs)
            .append("ms | calls=")
            .append(ctx.callCount)
            .append("\n");

        // 서비스/인프라 호출 시간 요약 출력
        for (TimingContext.Entry e : entries) {
          sb.append(String.format(
              "  - %-100s count=%3d total=%6dms max=%6dms avg=%6dms%n",
              e.signature,
              e.count,
              e.totalMs(),
              e.maxMs,
              e.avgMs()
          ));
        }

        log.info(sb.toString());
      }
    }
  }

  /**
   * Service / Repository / infrastructure Component 호출 시간 측정
   */
  @Around("timedTargets()")
  public Object aroundTimedTargets(ProceedingJoinPoint pjp) throws Throwable {
    TimingContext ctx = CTX.get();

    // 컨트롤러 외부 호출(스케줄러, 이벤트 리스너 등)은 제외
    if (ctx == null) {
      return pjp.proceed();
    }

    long startNs = System.nanoTime();
    try {
      return pjp.proceed();
    } finally {
      long elapsedNs = System.nanoTime() - startNs;
      ctx.callCount++;

      String signature = pjp.getSignature().toShortString();
      ctx.entries.compute(signature, (k, v) -> {
        if (v == null) {
          v = new TimingContext.Entry(signature);
        }
        v.add(elapsedNs);
        return v;
      });
    }
  }

  /**
   * 현재 HTTP 요청 조회
   */
  private HttpServletRequest currentRequest() {
    RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
    if (attrs instanceof ServletRequestAttributes sra) {
      return sra.getRequest();
    }
    return null;
  }

  /**
   * 요청 단위 타이밍 정보 저장용 컨텍스트
   */
  static class TimingContext {

    // 메서드 시그니처별 누적 시간
    final Map<String, Entry> entries = new HashMap<>();
    long callCount = 0;

    static class Entry {

      final String signature;
      long totalNs = 0;
      long maxMs = 0;
      int count = 0;

      Entry(String signature) {
        this.signature = signature;
      }

      // 호출 시간 누적
      void add(long elapsedNs) {
        count++;
        totalNs += elapsedNs;
        long ms = TimeUnit.NANOSECONDS.toMillis(elapsedNs);
        if (ms > maxMs) {
          maxMs = ms;
        }
      }

      long totalMs() {
        return TimeUnit.NANOSECONDS.toMillis(totalNs);
      }

      long avgMs() {
        return count == 0 ? 0 : totalMs() / count;
      }
    }
  }
}
