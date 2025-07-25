package kr.hhplus.be.config.cofing;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 설정 클래스
 * OpenAPI 문서를 생성하고 Swagger UI에서 API 문서를 표시하기 위한 설정을 포함합니다.
 */
@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI 설정을 위한 Bean 생성
     * Swagger UI에서 API 문서를 표시하기 위한 설정을 포함
     *
     * @return OpenAPI 인스턴스
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("이커머스 서비스 API 문서")
                        .version("1.0")
                        .description("이커머스 백엔드 서비스 API 문서"))
                .components(new Components()
                        .addSecuritySchemes("queue_token",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("Queue-Token")
                                        .description("대기열 토큰을 Authorization 헤더에 Bearer 방식으로 전송")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("queue_token"));
    }
}
