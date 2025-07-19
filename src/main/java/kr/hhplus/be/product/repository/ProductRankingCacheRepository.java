package kr.hhplus.be.product.repository;


import kr.hhplus.be.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRankingCacheRepository {
    private final RedisTemplate<String, ProductDto> productRedisTemplate;
    private final static Duration PRODUCT_CACHE_TTL = Duration.ofDays(3);

    public void setProductDto(ProductDto proudct) {
        String key = getKey(proudct.getProductName());
        try {
            productRedisTemplate.opsForValue().set(key, proudct, PRODUCT_CACHE_TTL);
            log.info("Set ProductDto to Redis {}({})", key, proudct);
        } catch (DataAccessException e) {
            log.error("Redis setProductDto error: {}", e.getMessage(), e);
            // 필요시 커스텀 예외 throw
            // throw new RedisOperationException("Redis 저장 실패", e);
        }

        log.info("Set ProductDto to Redis {}({})", key, proudct);

    }

    public Optional<ProductDto> getProductDto(String productName) {

        try {
            ProductDto data = productRedisTemplate.opsForValue().get(getKey(productName));
            log.info("Get ProductDto from Redis {}", data);
            return Optional.ofNullable(data);
        } catch (DataAccessException e) {
            log.error("Redis getProductDto error: {}", e.getMessage(), e);
            return Optional.empty();
        }

    }


    private String getKey(String productName) {
        return "UID:" + productName;
    }

    
}
