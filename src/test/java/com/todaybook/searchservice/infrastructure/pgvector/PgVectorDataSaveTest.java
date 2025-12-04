package com.todaybook.searchservice.infrastructure.pgvector;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PgVectorDataSaveTest {
  @Autowired VectorStore vectorStore;

  @Test
  void save1() {
    List<Document> documents =
        List.of(
            new Document(
                "0f3e8f0c-e9e0-45e0-b073-684a4dfd8a11",
                "작은 습관이 장기적인 변화를 이끈다는 핵심 원리를 다루며, 일상 속 루틴 설계법을 제시한 세계적 베스트셀러.",
                Map.of(
                    "title", "아주 작은 습관의 힘 (Atomic Habits)", "categories", List.of("자기계발", "심리학"))),
            new Document(
                "7b44f6de-df68-42a8-b0c8-0fb37f05b7dd",
                "삶에서 더 큰 성취와 행복을 얻기 위해 중요한 원칙과 마음가짐을 제시한 전 세계 밀리언셀러.",
                Map.of("title", "미라클 모닝", "categories", List.of("자기계발", "라이프스타일"))),
            new Document(
                "3fa3a9bb-8a89-4a56-9e03-5dc20f33ef58",
                "인생의 본질과 죽음, 존재에 대해 깊이 있게 성찰하며 삶의 태도에 대해 질문을 던지는 철학 에세이.",
                Map.of("title", "죽은 시인의 사회", "categories", List.of("문학", "에세이"))),
            new Document(
                "f4df11fa-9d54-4a02-9cbc-584ebcf4d4c4",
                "사람의 행동과 선택을 객관적으로 바라보게 하는 행동경제학의 대표작.",
                Map.of("title", "넛지", "categories", List.of("경제", "심리학"))),
            new Document(
                "c7b020e3-011a-41a2-b0f4-af0e51ccf028",
                "인간의 사고 체계를 두 가지 모드로 설명하며, 직관과 이성의 작동 방식을 심층적으로 분석한 명저.",
                Map.of("title", "생각에 관한 생각", "categories", List.of("심리학", "경제"))),
            new Document(
                "be4efb6f-65cd-4f49-94e2-cf20dd53d07f",
                "바쁜 현대인들에게 인생의 진정한 의미를 되돌아보게 하는 따뜻한 힐링 에세이.",
                Map.of("title", "모모", "categories", List.of("문학", "에세이"))),
            new Document(
                "c580a0d2-81c3-49c6-b64c-db5c2ef8fa39",
                "살아가는 방식에 대한 성찰과 간결한 메시지로 전 세계 독자에게 사랑받는 작품.",
                Map.of("title", "연금술사", "categories", List.of("철학", "문학"))),
            new Document(
                "d63168d3-1e72-4c6a-a250-0b2514d5e035",
                "최적의 선택을 만드는 의사결정 프레임워크를 제시한 스테디셀러.",
                Map.of("title", "그릿", "categories", List.of("심리학", "자기계발"))),
            new Document(
                "e119e5d1-7db0-4316-a61f-738a78c41f58",
                "마케팅의 본질과 사람의 심리를 관통하는 메시지로 브랜드 전략의 기준을 바꾼 책.",
                Map.of("title", "퍼플 카우", "categories", List.of("마케팅", "비즈니스"))),
            new Document(
                "6c66393e-2c69-42cf-8df4-07c4a5f3e38d",
                "삶의 의미와 관계, 행복을 철학적으로 풀어낸 현대인의 필독서.",
                Map.of("title", "여행의 이유", "categories", List.of("에세이", "문학"))),
            new Document(
                "d94dc0e2-780a-4865-8f24-08e22e6aaf03",
                "실행력을 극대화하는 핵심 원리를 제시하며, 목표 달성을 위한 실천 전략을 안내한다.",
                Map.of("title", "원씽(The ONE Thing)", "categories", List.of("자기계발", "비즈니스"))),
            new Document(
                "e77cf36c-a52a-4e6b-8f64-89ea2f70e7e4",
                "시간 관리 철학을 새롭게 정의한 작품으로, 현대인의 업무 방식을 혁신적으로 바꾼 책.",
                Map.of("title", "에센셜리즘", "categories", List.of("자기계발", "라이프스타일"))),
            new Document(
                "47ac1ef1-d416-4a21-9c4e-c4b646f6a31f",
                "삶의 본질을 통찰하는 심플하고 명확한 메시지로 세계적 사랑을 받은 자기계발서.",
                Map.of("title", "미움받을 용기", "categories", List.of("심리학", "철학"))),
            new Document(
                "1c4c4baf-9424-4778-9b8b-2b88513bf915",
                "감정의 본질과 감정 조절 방식을 과학적으로 설명한 현대 심리학의 대표작.",
                Map.of("title", "감정 수업", "categories", List.of("심리학", "에세이"))),
            new Document(
                "9bd34733-ff72-45bd-b170-7df6a97c1597",
                "도시와 공간의 변화를 인문학적으로 해석하며 현대 사회를 바라보는 새로운 관점을 제시한다.",
                Map.of("title", "지금, 여기의 현대미술", "categories", List.of("예술", "철학"))),
            new Document(
                "e2ab2dce-f964-4d13-8d46-063f734c6144",
                "뇌과학을 기반으로 습관 형성과 행동 변화의 메커니즘을 분석한 과학 교양 베스트셀러.",
                Map.of("title", "습관의 힘", "categories", List.of("심리학", "과학"))),
            new Document(
                "ab0c6cc1-7562-4421-8391-2b8ecdc7e8e7",
                "세계 경제 흐름을 단순하고 명쾌하게 풀어낸 교양 경제학 입문서.",
                Map.of("title", "부의 인문학", "categories", List.of("경제", "인문학"))),
            new Document(
                "32baf3aa-2e7f-4bb8-afdd-3e083e4a794f",
                "삶과 죽음, 관계의 의미를 잔잔한 시선으로 풀어낸 감성 에세이.",
                Map.of("title", "아침, 그리고 저녁", "categories", List.of("에세이", "문학"))),
            new Document(
                "c5716270-0c81-4081-b599-8bf5ef8fcd87",
                "경영 전략과 혁신의 로드맵을 제시하며 전 세계 리더들의 필독서로 자리 잡은 책.",
                Map.of("title", "블루오션 전략", "categories", List.of("비즈니스", "경영"))),
            new Document(
                "a70c5cd6-6299-4eb2-943f-f2f40d45332b",
                "관찰과 통찰을 통해 인간 관계의 본질을 분석하는 심리학 명저.",
                Map.of("title", "말의 품격", "categories", List.of("심리학", "에세이"))));
    vectorStore.add(documents);
  }

  void save2() {
    List<Document> documents =
        List.of(
            new Document(
                "0b1cfae8-dbc7-4ce4-a2b0-2cb7868879c5",
                "흔들리는 감정 속에서도 자신을 잃지 않는 법을 배우게 해주는 따뜻한 심리 치유 에세이.",
                Map.of("title", "하마터면 열심히 살 뻔했다", "categories", List.of("에세이", "심리학"))),
            new Document(
                "c4f438fd-b35d-4d5b-ba9f-06f0e2ce69d7",
                "사람의 마음을 움직이는 언어의 기술을 섬세하게 탐구한 커뮤니케이션 명저.",
                Map.of("title", "넛지 이야기", "categories", List.of("심리학", "커뮤니케이션"))),
            new Document(
                "f1a45598-5f51-4bbf-9791-4685cb2449f2",
                "삶을 바꾸는 선택과 태도에 대한 통찰을 전하는 스테디셀러 인문학 작품.",
                Map.of("title", "데미안", "categories", List.of("문학", "철학"))),
            new Document(
                "82ce48f2-99ff-49ae-8897-7d3e2d6f17ad",
                "불안함을 다루는 방법과 자기 위로의 기술을 섬세하게 풀어낸 힐링 에세이.",
                Map.of("title", "나는 나로 살기로 했다", "categories", List.of("심리학", "에세이"))),
            new Document(
                "aee3f701-d147-4030-86ac-f9318a0effd1",
                "모든 인간 관계의 핵심에는 ‘자존감’이 있다고 설명하며 자기 자신을 이해하도록 돕는 책.",
                Map.of("title", "자존감 수업", "categories", List.of("심리학", "자기계발"))),
            new Document(
                "b78a1c6d-57c5-43e2-8afe-531108c6be0b",
                "일상의 스트레스와 불안 속에서 마음을 지키는 법을 알려주는 현대 심리학의 대표작.",
                Map.of("title", "불안", "categories", List.of("심리학", "철학"))),
            new Document(
                "848d3f7a-7052-4c61-9cc3-6c1f0a2cf0b4",
                "현대 사회를 행복한 방식으로 살아가기 위한 철학적 지침을 제시하는 베스트셀러.",
                Map.of("title", "행복의 조건", "categories", List.of("철학", "에세이"))),
            new Document(
                "d447c3ab-f791-4cd3-ab59-35e7a99f7084",
                "역경을 딛고 성장하는 인간의 힘을 이야기한 감동적인 자전적 소설.",
                Map.of("title", "언제나 길은 있다", "categories", List.of("문학", "인문학"))),
            new Document(
                "f7a56a1f-0cdd-44fe-bee9-20a94b1c8bcd",
                "문학적 상상력과 인간 존재의 의미를 깊이 탐구한 세계문학 걸작.",
                Map.of("title", "1984", "categories", List.of("문학", "철학"))),
            new Document(
                "0b187d33-b0eb-4a4b-bf36-1628f614c8ab",
                "사회 구조와 인간의 욕망을 냉철하게 분석한 심리 사회학 명저.",
                Map.of("title", "사피엔스", "categories", List.of("역사", "인문학"))),
            new Document(
                "a203d4e1-8dcc-4377-bfc2-4a91f3470785",
                "미래 기술과 인간의 삶이 어떻게 바뀌는지 명쾌하게 설명하는 세계적 베스트셀러.",
                Map.of("title", "호모 데우스", "categories", List.of("과학", "역사"))),
            new Document(
                "fdb624e1-0a64-428d-a81f-768d45f87587",
                "시간의 본질과 우주를 이해하는 데 필요한 핵심 이론을 쉽고 흥미롭게 정리한 과학 교양서.",
                Map.of("title", "시간의 역사", "categories", List.of("과학", "철학"))),
            new Document(
                "81a41e0a-4d13-4922-9a09-33f8a6c9b64d",
                "경제 시스템의 원리를 알기 쉽게 풀어내며 부의 흐름을 이해하도록 돕는 책.",
                Map.of("title", "부자 아빠 가난한 아빠", "categories", List.of("경제", "자기계발"))),
            new Document(
                "71bf8f1f-9d44-4dc7-bed9-dcac7d33a3e4",
                "성공의 조건과 실천 방법을 구체적으로 제시한 세계적 자기계발서.",
                Map.of("title", "7가지 성공 법칙", "categories", List.of("자기계발", "비즈니스"))),
            new Document(
                "bb943f1c-5272-4c34-93ad-489cd85c80e4",
                "사람을 움직이는 설득의 기본 원칙을 체계적으로 분석한 세계적 명저.",
                Map.of("title", "설득의 심리학", "categories", List.of("심리학", "커뮤니케이션"))),
            new Document(
                "2a053a7a-8fb6-4a96-b44e-347437b6f0e4",
                "디지털 시대의 마케팅 전략과 소비자 행동을 분석한 현대 비즈니스 필독서.",
                Map.of("title", "컨셉의 힘", "categories", List.of("마케팅", "비즈니스"))),
            new Document(
                "f13981b8-4bc1-4d26-b8cd-3ef8503430a3",
                "세기의 투자자 워런 버핏의 경영 철학과 투자 전략을 깊이 있게 다룬 책.",
                Map.of("title", "워런 버핏 바이블", "categories", List.of("경제", "투자"))),
            new Document(
                "0d1ab322-4c8d-48c2-9dd6-5b77f7f2c7e0",
                "브랜딩과 마케팅의 본질을 재조명하며 성공적인 브랜드 전략을 설명한 명저.",
                Map.of("title", "마케팅 불변의 법칙", "categories", List.of("마케팅", "비즈니스"))),
            new Document(
                "ff4a0f31-88be-48e1-b556-3bcd7de094a1",
                "실패를 성공의 과정으로 바라보는 혁신적 사고방식을 제시하는 자기계발서.",
                Map.of("title", "실패학", "categories", List.of("자기계발", "심리학"))),
            new Document(
                "62d13926-20a9-4aa5-b959-4bfe1135c554",
                "인생을 대하는 자세와 삶의 균형을 철학적으로 풀어낸 베스트셀러.",
                Map.of("title", "무기력의 심리학", "categories", List.of("심리학", "인문학"))));

    vectorStore.add(documents);
  }

  @Test
  void save3() {
    List<Document> documents =
        List.of(
            new Document(
                "403c9b63-f83f-4a0e-a324-a81efc92dd68",
                "이 시대를 살아가는 청년들에게 꿈과 현실, 자존감에 대해 따뜻한 조언을 건네는 베스트셀러.",
                Map.of("title", "청년이여, 울지 말라", "categories", List.of("에세이", "자기계발"))),
            new Document(
                "8d28dcac-98d9-49a9-8d78-8f55ba2e50b2",
                "사람의 마음을 움직이는 카리스마의 비밀을 심리학적으로 해석한 통찰의 책.",
                Map.of("title", "카리스마의 심리학", "categories", List.of("심리학", "커뮤니케이션"))),
            new Document(
                "77b812c8-86e4-40b9-b53d-18a5f7a679e3",
                "끊임없이 변화하는 세계에서 어떻게 배우고 성장해야 하는지 알려주는 필독서.",
                Map.of("title", "배움의 발견", "categories", List.of("인문학", "자기계발"))),
            new Document(
                "e5028bc3-2d80-40e8-9f70-b74ebf4c0b17",
                "성공과 실패의 본질을 분석하며 꾸준함의 중요성을 강조하는 스테디셀러.",
                Map.of("title", "꾸준함의 힘", "categories", List.of("자기계발", "심리학"))),
            new Document(
                "60e8a3c6-1877-4e48-b234-91270eb20d9f",
                "기억, 사고, 감정의 작동 방식을 신경과학적으로 설명한 현대 교양서.",
                Map.of("title", "뇌, 생각의 출현", "categories", List.of("과학", "심리학"))),
            new Document(
                "29144c21-c115-4dff-88f5-d08d9e6c859f",
                "현대인의 마음과 심리를 날카롭게 분석한 에세이로, 공감과 치유를 선물한다.",
                Map.of("title", "보통의 언어들", "categories", List.of("에세이", "심리학"))),
            new Document(
                "a756f44d-d67a-41db-a653-f539a77ca6c1",
                "삶에서 진짜 중요한 것이 무엇인지 깨닫게 해주는 철학적 메시지를 담은 책.",
                Map.of("title", "돈으로 살 수 없는 것들", "categories", List.of("철학", "경제"))),
            new Document(
                "52f7c42d-9702-4768-8041-b5dfaae02713",
                "성공한 사람들의 공통된 사고 패턴을 분석하고 성공 전략을 정리한 명저.",
                Map.of("title", "아웃라이어", "categories", List.of("심리학", "비즈니스"))),
            new Document(
                "d73db0a1-51f4-43a0-8e6d-dce3db0c4b85",
                "지식의 구조와 학습의 본질을 탐구하며 바른 공부법을 제시하는 베스트셀러.",
                Map.of("title", "지적 대화를 위한 넓고 얕은 지식", "categories", List.of("인문학", "교양"))),
            new Document(
                "b8ba0d6a-138d-4baf-bd97-ea76175d462a",
                "관계 속에서 상처받은 마음을 치유하는 감성 가득한 에세이.",
                Map.of("title", "너에게 하고 싶은 말", "categories", List.of("에세이", "감성"))),
            new Document(
                "6d588ea9-8f26-4afc-b3ac-16706c91d77d",
                "어둠 속에서 희망을 찾아가는 인간의 여정을 감동적으로 그린 세계문학.",
                Map.of("title", "죄와 벌", "categories", List.of("문학", "철학"))),
            new Document(
                "95bf83b0-3e35-435f-a3c3-09486b4a6f7b",
                "기술 혁신이 미래 사회를 어떻게 바꿀지 명확하게 설명하는 IT 교양서.",
                Map.of("title", "초예측", "categories", List.of("과학", "기술"))),
            new Document(
                "a02abf7d-6bc8-48b5-b7c2-90428370bfd3",
                "삶에서 나를 지키며 행복하게 살아가기 위한 마음 전략을 담은 심리 에세이.",
                Map.of("title", "오늘도 흔들리는 너에게", "categories", List.of("심리학", "에세이"))),
            new Document(
                "fa32a3c9-93d3-4b70-89ea-c17e8bc0757c",
                "평범한 일상 속에서 발견한 소중함을 잔잔하게 담아낸 베스트셀러 감성 에세이.",
                Map.of("title", "모든 순간이 너였다", "categories", List.of("감성", "에세이"))),
            new Document(
                "c63d193e-4142-4c9a-b2f5-adf918ce6664",
                "대한민국 직장인의 현실과 고민을 위트 있게 풀어낸 스토리텔링 에세이.",
                Map.of("title", "퇴사학교", "categories", List.of("비즈니스", "에세이"))),
            new Document(
                "74756c34-e849-44fe-8df7-826f049ae5b8",
                "리더십의 본질과 조직을 이끄는 방법을 제시한 세계적 경영서.",
                Map.of("title", "리더의 조건", "categories", List.of("경영", "리더십"))),
            new Document(
                "cf048e3f-62c0-409d-8b56-e82d5c1bf269",
                "세계의 언어와 문화가 인간의 사고 방식에 미치는 영향을 탐구하는 흥미로운 인문 교양서.",
                Map.of("title", "생각의 지도", "categories", List.of("인문학", "심리학"))),
            new Document(
                "e0ce7b82-a09c-4e8b-b197-c1b28c2e12cd",
                "투자의 본질과 시장의 흐름을 심리적으로 분석한 투자의 정석으로 불리는 책.",
                Map.of("title", "심리학이 투자에 미치는 영향", "categories", List.of("경제", "투자"))),
            new Document(
                "97a5c1e6-51a3-4f46-a5f1-baeb0e250f40",
                "관계의 본질을 탐구하며 좋은 인간관계의 조건을 설명한 스테디셀러.",
                Map.of("title", "인간 본성의 법칙", "categories", List.of("심리학", "인문학"))),
            new Document(
                "2b0ac68d-d944-4d55-9b3a-59af6092a061",
                "위험한 시대를 살아가는 개인의 생존 전략을 경제학적 관점에서 분석한 책.",
                Map.of("title", "위험사회", "categories", List.of("경제", "사회학"))),
            new Document(
                "3b5bf6b4-df51-4981-ac0d-3363cb0b7df0",
                "현대 사회에서 흔히 겪는 번아웃과 스트레스의 구조를 심리학적으로 분석한 책.",
                Map.of("title", "번아웃의 심리학", "categories", List.of("심리학", "건강"))),
            new Document(
                "e748c15e-3cb0-428d-a710-4ab22e90939f",
                "경제적 자유를 얻기 위한 실제적 방법과 마인드셋을 제시하는 필독 투자서.",
                Map.of("title", "돈의 속성", "categories", List.of("경제", "투자"))),
            new Document(
                "8b2c315d-37fd-470e-be27-a05dba9f5d03",
                "다양한 관점을 이해하고 생각의 폭을 넓히도록 돕는 스토리 기반 인문 베스트셀러.",
                Map.of("title", "나를 돌보는 시간", "categories", List.of("인문학", "에세이"))),
            new Document(
                "cba3edfa-69b3-4574-bf11-e9bb6b4db9c8",
                "시간, 선택, 삶을 철학적으로 탐구하며 현대인의 고민을 깊이 있게 다룬 작품.",
                Map.of("title", "삶의 철학", "categories", List.of("철학", "에세이"))),
            new Document(
                "8a3494fd-0eca-48ab-b30f-389fa2622a03",
                "평범한 일상에서 발견하는 소소한 행복의 의미를 재조명한 감성 에세이.",
                Map.of("title", "오늘도 빛나는 너에게", "categories", List.of("감성", "에세이"))),
            new Document(
                "f3fc91e9-e67e-48ed-bbf7-57c95f4ab0dd",
                "미래를 예측하고 변화에 대비하는 전략적 사고를 제시한 인문학적 미래예측서.",
                Map.of("title", "미래의 정석", "categories", List.of("미래학", "인문학"))),
            new Document(
                "4ccd0b8a-fc7c-4a4f-a41d-985c9dd1099f",
                "관찰의 힘과 사유의 깊이를 기반으로 세상을 해석하는 인문학적 통찰서.",
                Map.of("title", "관찰의 기술", "categories", List.of("인문학", "교양"))),
            new Document(
                "97c5cee8-74a3-4b6d-a5d7-aa625f72c787",
                "모든 문제는 관계에서 비롯된다는 관점에서 인간 심리를 분석한 심리학 명저.",
                Map.of("title", "관계의 본질", "categories", List.of("심리학", "에세이"))),
            new Document(
                "a4f59550-bc30-4121-ae4a-680694ad3e75",
                "인생의 목적과 의미에 대해 물으며 어떻게 살아야 하는지 철학적으로 성찰하도록 돕는 책.",
                Map.of("title", "왜 사는가", "categories", List.of("철학", "인문학"))),
            new Document(
                "232b662a-cefe-47aa-8623-4ab6aee7f8c4",
                "마음 건강을 유지하고 회복하는 현실적인 방법을 소개하는 심리 치유서.",
                Map.of("title", "마음의 힘", "categories", List.of("심리학", "건강"))),
            new Document(
                "dcc18119-8f60-4e40-a770-f4ea30c32a44",
                "올바른 선택을 위한 사고의 틀과 의사결정 전략을 제시하는 실용적인 책.",
                Map.of("title", "생각의 기술", "categories", List.of("자기계발", "비즈니스"))),
            new Document(
                "23cdd57c-0677-4c1a-8c45-dceabc653bb2",
                "각자의 자리에서 자신만의 방식으로 성장하는 법을 보여주는 감성 에세이.",
                Map.of("title", "오늘, 우리의 이야기", "categories", List.of("에세이", "감성"))),
            new Document(
                "e7dd868a-3533-4af1-bee2-898f1599bfa4",
                "불확실한 시대에 견디는 힘과 회복탄력성을 설명하는 심리 기반 자기계발서.",
                Map.of("title", "회복탄력성", "categories", List.of("심리학", "자기계발"))),
            new Document(
                "8479ae11-b3cd-40e5-90d1-12f4a449dbb0",
                "복잡한 세상을 살아가는 데 필요한 지적 도구들을 정리한 교양 인문서.",
                Map.of("title", "생각의 무기", "categories", List.of("인문학", "교양"))),
            new Document(
                "1dd07e25-9fcf-44f6-93c7-3b0dd4cf3111",
                "심리학 연구를 토대로 소통의 기술과 공감 능력을 향상시키는 방법을 연구한 책.",
                Map.of("title", "말의 심리학", "categories", List.of("심리학", "커뮤니케이션"))),
            new Document(
                "21bdb608-96fe-49a3-b642-1c0578089b8d",
                "사람의 행동을 분석하여 경제적 선택의 본질을 설명한 교양 경제서.",
                Map.of("title", "보이지 않는 손", "categories", List.of("경제", "심리학"))),
            new Document(
                "ed3452f9-64ff-42dd-b3a1-64d1c3cf5f94",
                "자기 이해와 감정 조절의 중요성을 다룬 심리학 베스트셀러.",
                Map.of("title", "내 마음을 읽는 시간", "categories", List.of("심리학", "에세이"))),
            new Document(
                "c1ec07bb-53ab-4e2e-8560-bf99c4b70713",
                "성공한 창업자들과 기업 사례를 통해 혁신 전략을 설명하는 비즈니스 교양서.",
                Map.of("title", "창업의 모든 것", "categories", List.of("비즈니스", "경영"))),
            new Document(
                "d7195e49-8fec-40b3-b1f8-0d48dd3c0157",
                "디지털 시대의 정보 홍수 속에서 올바르게 판단하는 사고법을 설명한 책.",
                Map.of("title", "팩트풀니스", "categories", List.of("교양", "과학"))),
            new Document(
                "c5a52df2-57cc-4745-9cb2-c34c2cde05ac",
                "감정적 소모를 줄이고 더 나은 인간관계를 만드는 방법을 제시하는 베스트셀러.",
                Map.of("title", "감정적 그루밍", "categories", List.of("심리학", "자기계발"))),
            new Document(
                "083c3795-3efb-4db0-9d5e-22ca2d8a3663",
                "세상과 나를 이해하는 데 필요한 철학적 질문들을 던지는 현대 철학 입문서.",
                Map.of("title", "철학의 첫걸음", "categories", List.of("철학", "교양"))),
            new Document(
                "51a2c3f8-a5ed-4393-8c73-265d77460b8a",
                "우리가 선택하는 모든 행동 뒤에 숨겨진 심리를 과학적으로 분석하는 심리 교양서.",
                Map.of("title", "마음의 과학", "categories", List.of("과학", "심리학"))),
            new Document(
                "a4c2d8a1-0dec-4b96-9dd9-0728c30a04f2",
                "효율적인 시간 관리와 생산성 향상 전략을 담은 실용적인 자기계발서.",
                Map.of("title", "시간의 기술", "categories", List.of("자기계발", "비즈니스"))));

    vectorStore.add(documents);
  }

  @Test
  void save4() {
    List<Document> documents = List.of();

    vectorStore.add(documents);
  }

  @Test
  void save5() {
    List<Document> documents = List.of();

    vectorStore.add(documents);
  }
}
