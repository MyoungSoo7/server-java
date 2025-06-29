package kr.hhplus.be.server.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password:}")
    private String redisPassword;

    /**
     * RedissonClient Bean 생성
     * Redis 서버에 연결하기 위한 RedissonClient를 설정합니다.
     * @return RedissonClient 인스턴스
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        String address = String.format("redis://%s:%d", redisHost, redisPort);
        if (redisPassword != null && !redisPassword.isEmpty()) {
            config.useSingleServer()
                .setAddress(address)
                .setPassword(redisPassword);
        } else {
            config.useSingleServer()
                .setAddress(address);
        }
        return Redisson.create(config);
    }
}
