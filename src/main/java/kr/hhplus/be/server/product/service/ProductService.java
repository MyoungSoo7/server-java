package kr.hhplus.be.server.product.service;

import kr.hhplus.be.server.order.repository.OrderRepository;
import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

	private static final int TOP_PRODUCTS_LIMIT = 5;
	private static final String STOCK_ERROR_MESSAGE = "재고는 0개 이하로 떨어질 수 없습니다.";
	private static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다. ID: ";
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;

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
	// TODO: 통계 데이터를 실시간으로 계산하는 것은 성능상의 부담이 될 수 있으니 캐싱(예: Redis)을 활용하여 반복적인 요청 처리 성능을 개선할 수 있도록 합니다.
	// TODO: 상품 판매 통계는 일정 주기로 업데이트하여 최신 데이터를 유지하는 방식을 고려할 수 있습니다.

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
