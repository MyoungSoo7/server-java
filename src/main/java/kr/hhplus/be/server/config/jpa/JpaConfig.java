package kr.hhplus.be.server.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
public class JpaConfig {
    //오늘의 학습 목표를 한 문장으로 다시 말해보면 더 잘 기억돼요.
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
      /*- 시나리오 선정  및 프로젝트 Milestone 작성
- 문서는 README 또는 docs 디렉토리 내 .md 파일로 작성할 수 있습니다.
    - README에는 상대 경로 링크만 걸어두는 방식이 추천됩니다
- 시나리오 요구사항 분석 및 문서 작성 ( e.g. 시퀀스 다이어그램, ERD 등 )
- 인프라 구성도를 만들고, 작성한 인프라 구성도에 있는 항목들을 간단하게 기술해주세요.
    - 주의할점은 다른 프로젝트 구성원이 봤을때 이해가 되어야 한다는 점을 고려해주세요.*/

    // 심화
    /*
    - Mock API 및 Swagger-API 코드 작성
- **(NiceToHave)** API E2E 테스트 작성해보기*/
// 설계 → 단위 기능 개발 → 통합 기능 → 서버 구축까지가 목표입니다. 2주차에는 기능 태스크 정의 및 우선순위 정리가 핵심입니다.
    /*- **추상적 요구사항 구체화가 어려울 때 팁은?**
    A : ‘무엇을 해야 하는가’를 먼저 정의하고, 기능별 필요한 정책/동작을 글로 나열한 후, 다이어그램으로 구조화해 나가면 효과적입니다.*/


    // 서버 설계 핵심 원칙 이해 - 성능/확장성/가용성/보안성/유지보수성/비용 효율성
    /*- 설계 없이 구현할 때 발생하는 가장 흔한 문제는?
        - 설계 시 고려해야 할 핵심 6요소는?*/
//요구사항 정의 & 트래픽 예측 - 사전 리서치가 설계의 절반
    /***빠르게 배우는 포인트**
        - 기능/비기능 요구사항 정리 방법
- 트래픽 추산 → 서버 수/부하 예측 공식
**퀴즈**
        - “비기능적 요구사항”에 해당하는 요소는?
        - 트래픽 예측에 필요한 3가지 주요 수치는?*/
    //애플리케이션 구조 설계 & API 문서화
/***핵심 메시지** - DDD 기반 구조 + RESTful API 설계가 협업의 기반이다.*/
/***빠르게 배우는 포인트**
        - 도메인 중심 패키지 구조 (user/order/product 등)
- Swagger 기반 API 문서 자동화 및 Mock API 생성
**퀴즈**
        - 기술 중심 구조와 도메인 중심 구조의 차이점은?
        - API 버전 관리가 필요한 이유는?*/
    //인프라 구성 전략 및 실전 설계 과제 가이드
    //인프라 설계는 장애 대응과 확장성의 핵심이다.
    /***빠르게 배우는 포인트**
        - 로드밸런서, 캐시, 메시지 큐, 도커/쿠버네티스의 활용 전략
- 인프라 구성도 작성 & 실전 설계 과제 접근법 (시나리오 기반)
**퀴즈**
        - 로드밸런서의 핵심 기능은?
        - 쿠버네티스의 오토스케일이 필요한 상황은?*/

}