package kr.hhplus.be.product.repository;


import kr.hhplus.be.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRankingCacheRepository {
    private final RedisTemplate<String, ProductDto> userRedisTemplate;

    private final static Duration USER_CACHE_TTL = Duration.ofDays(3);


    public void setProductDto(ProductDto user) {
        String key = getKey(user.getProductName());
        log.info("Set ProductDto to Redis {}({})", key, user);
        userRedisTemplate.opsForValue().set(key, user, USER_CACHE_TTL);
    }

    public Optional<ProductDto> getProductDto(String userName) {
        ProductDto data = userRedisTemplate.opsForValue().get(getKey(userName));
        log.info("Get ProductDto from Redis {}", data);
        return Optional.ofNullable(data);
    }


    private String getKey(String userName) {
        return "UID:" + userName;
    }

    
}
