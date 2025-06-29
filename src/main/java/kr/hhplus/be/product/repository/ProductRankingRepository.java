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
public class ProductRankingRepository {

    private final RedisTemplate<String, ProductDto> redisTemplate;

    private final static Duration CACHE_TTL = Duration.ofDays(3);

    public void setProductRanking(ProductDto products) {
        String key = getKey(products.getProductName());
        log.info("Set Product Ranking to Redis {}({})", key, products);
        redisTemplate.opsForValue().set(key, products, CACHE_TTL);
    }

    public Optional<ProductDto> getProductRanking(String productId) {
        ProductDto data = redisTemplate.opsForValue().get(getKey(productId));
        log.info("Get Product Ranking from Redis {}", data);
        return Optional.ofNullable(data);
    }
    private String getKey(String productId) {
        return "PRD:" + productId;
    }



}
