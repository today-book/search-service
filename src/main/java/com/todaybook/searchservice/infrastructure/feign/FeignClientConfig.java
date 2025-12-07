package com.todaybook.searchservice.infrastructure.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.todaybook.searchservice")
public class FeignClientConfig {}
