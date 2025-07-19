package kr.hhplus.be.product.service;

import kr.hhplus.be.config.exception.ErrorCode;
import kr.hhplus.be.config.exception.ProductOrderApplicationException;
import kr.hhplus.be.order.repository.OrderRepository;
import kr.hhplus.be.product.dto.ProductDto;
import kr.hhplus.be.product.entity.Products;
import kr.hhplus.be.product.repository.ProductRankingCacheRepository;
import kr.hhplus.be.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

	private static final int TOP_PRODUCTS_LIMIT = 5;
	private static final String secretKey = "swkey";
	private static final Long expiredTimeMs = 2_592_000_000L; // 30일
	private static final String STOCK_ERROR_MESSAGE = "재고는 0개 이하로 떨어질 수 없습니다.";
	private static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다. ID: ";
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final BCryptPasswordEncoder encoder;
	private final ProductRankingCacheRepository redisRepository;
	private final ModelMapper modelMapper;

	public Optional<ProductDto> loadProductDtoByProductDtoname(String productName)  {
		return redisRepository.getProductDto(productName);
	}

	public String setProductRank(String productName) {
		ProductDto savedProductDto = loadProductDtoByProductDtoname(productName)
				.orElseThrow(()-> new ProductOrderApplicationException(
					ErrorCode.PRODUCT_CAN_NOT_FOUND,
					String.format("Product with name %s not found.", productName)
				));

		redisRepository.setProductDto(savedProductDto);

		return "Rank has been set successfully.";
	}


	public ProductDto getProductById(long productId) {
		Products product = validateProductExistence(productId);
		return modelMapper.map(product, ProductDto.class);
	}

	public void updateProductStock(long productId, int updatedStock) {
		Products product = validateProductExistence(productId);
		validateStock(product.getProductStock(), updatedStock);

		product.setProductStock(product.getProductStock() + updatedStock);
		productRepository.save(product);
	}

	// 최근 3일간 가장 많이 팔린 상위 5개 상품 정보 조회
    @Cacheable(value = "topSellingProducts", key = "#root.methodName")
	public List<ProductDto> getTopSellingProductsInLast3Days() {
		LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
		List<Object[]> results = orderRepository.findTopSellingProducts(threeDaysAgo, TOP_PRODUCTS_LIMIT);

		return results.stream()
			.map(this::mapToProductDtoWithSales)
			.collect(Collectors.toList());
	}

	/*@CacheEvict(value = "topSellingProducts", allEntries = true)
	public void clearTopSellingProductsCache() {
		// 캐시 삭제 로직 (DB 실시간 업데이트 시 호출)
	}

	@Scheduled(fixedRate = 3600000) // 1시간마다 실행
	@CacheEvict(value = "topSellingProducts", allEntries = true)
	public void refreshTopSellingProductsCache() {
		// 캐시를 비우기만 하면 다음 호출 시 새롭게 DB에서 데이터를 가져옵니다.
	}*/



	public List<ProductDto> getAllProducts() {
		List<Products> products = productRepository.findAll();
		return products.stream().map(product ->
				modelMapper.map(product, ProductDto.class))
			.collect(Collectors.toList());
	}

	private Products validateProductExistence(long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_FOUND + productId));
	}

	private void validateStock(int currentStock, int updatedStock) {
		if (currentStock + updatedStock < 0) {
			throw new IllegalArgumentException(STOCK_ERROR_MESSAGE +
				" 현재 재고: " + currentStock + ", 감소하려는 재고: " + updatedStock);
		}
	}

	private ProductDto mapToProductDtoWithSales(Object[] result) {
		Long productId = (Long) result[0];
		Long totalSold = (Long) result[1];

		Products product = validateProductExistence(productId);
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		productDto.setTotalSold(totalSold.intValue());

		return productDto;
	}


}
