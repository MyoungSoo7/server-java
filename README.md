# 📁 프로젝트 구조 및 기술 스택

## ⚙️ 기술 스택

### ✅ 주요 기술

* Java 17
* Spring Boot 3.4.1
* Spring Web / Spring MVC
* Spring Data JPA
* Lombok

### ✅ 데이터베이스

* MariaDB 11

---

## 🗂 패키지 구조 (헥사고날 아키텍처 기반)

```
com.example.hhpb.ecommerce/
├── domain/                                 # 도메인 레이어
│   ├── model/                              # 엔티티, VO, 애그리거트 루트
│   ├── event/                              # 도메인 이벤트
│   ├── exception/                          # 도메인 예외
│   └── enums/                              # 도메인 열거형
│
├── application/                            # 애플리케이션 레이어
│   ├── usecase/                            # 입력/출력 포트 정의
│   └── service/                            # 유즈케이스 구현 (입력 포트 구현)
│
├── adapter/                                # 어댑터
│   ├── in/                                 # 입력 어댑터
│   │   └── web/                            # REST API Controller
│   └── out/                                # 출력 어댑터
│       ├── persistence/                    # JPA 리포지토리 구현
│       └── event/                          # 이벤트 발행기
│
├── config/                                 # 설정 파일
└── common/                                 # 공통 유틸
```
---

## 시나리오 분석

* [Milestone](https://github.com/boldfaced7/hhplus-e-commerce/milestones)
* [Requirement](./docs/REQUIREMENT.md)
* [Flowchart](./docs/FLOWCHART.md)
* [Class Diagram](./docs/CLASS_DIAGRAM.md)
* [Sequence Diagram](./docs/SEQUENCE_DIAGRAM.md)
* [ERD Diagram](./docs/ERD.md)
* [API Spec](./docs/API_SPEC.md)
