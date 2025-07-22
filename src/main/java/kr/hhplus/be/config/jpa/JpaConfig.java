package kr.hhplus.be.config.jpa;

import org.checkerframework.checker.units.qual.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * JPA 설정 클래스
 * JPA Auditing과 Repository를 활성화하고, 트랜잭션 매니저를 Bean으로 등록합니다.
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "kr.hhplus.be")
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

}