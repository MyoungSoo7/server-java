package kr.hhplus.be.config.cofing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Security 설정 클래스
 * BCryptPasswordEncoder를 Bean으로 등록하여 비밀번호 암호화를 지원합니다.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 생성
    }
}
