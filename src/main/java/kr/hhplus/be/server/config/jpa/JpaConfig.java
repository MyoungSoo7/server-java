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

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
    
// 설계 → 단위 기능 개발 → 통합 기능 → 서버 구축까지가 목표입니다. 2주차에는 기능 태스크 정의 및 우선순위 정리가 핵심입니다.
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

}