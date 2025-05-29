package kr.hhplus.be.server.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.order.repository.OrderRepository;
import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;

	public ProductDto selectProductById(long productId) {
		return getProductDto( productId);
	}

	private ProductDto getProductDto(Long productId) {
		Products product = productRepository.findById(productId).orElseThrow(() ->
			new IllegalArgumentException("Product not found: " + productId)
		);
		return modelMapper.map(product, ProductDto.class); // ModelMapper로 변환
	}

	public List<ProductDto> selectAllProducts() {
		List<Products> products = productRepository.findAll();
		return products.stream().map(product ->
			modelMapper.map(product, ProductDto.class))
			.collect(Collectors.toList()); 
	}

	
	// 재고는 0개 이하 불가
	public void updateProductStock(long productId, int updatedStock) {
		Products product = productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + productId));

		// 기존 재고 확인
		int currentStock = product.getProductStock();

		// 재고 감소로 인해 기존 재고가 0개 이하로 내려가는 경우 정책 위반
		if (currentStock + updatedStock < 0) {
			throw new IllegalArgumentException("현재 재고는 0개 이하로 떨어질 수 없습니다. 현재 재고: "
				+ currentStock + ", 감소하려는 재고: " + (-updatedStock));
		}

		// 재고 업데이트
		product.setProductStock(currentStock + updatedStock);
		productRepository.save(product); // 변경 사항 저장
	}

	// 최근 3일간 가장 많이 팔린 상위 5개 상품 정보 조회
	// TODO: 통계 데이터를 실시간으로 계산하는 것은 성능상의 부담이 될 수 있으니 캐싱(예: Redis)을 활용하여 반복적인 요청 처리 성능을 개선할 수 있도록 합니다.
	// TODO: 상품 판매 통계는 일정 주기로 업데이트하여 최신 데이터를 유지하는 방식을 고려할 수 있습니다.
	public List<ProductDto> getTop5SellingProductsLast3Days() {
		// 최근 3일의 시작 시간을 계산
		LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);

		// 주문 데이터를 기반으로 가장 많이 팔린 상위 5개 상품 조회
		List<Object[]> results = orderRepository.findTopSellingProducts(threeDaysAgo, 5);

		// 결과 데이터를 ProductDto로 매핑
		return results.stream().map(result -> {
			Long productId = (Long) result[0]; // 상품 ID
			Long totalSold = (Long) result[1]; // 총 판매량
			Products product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. ID: " + productId));

			// 제품 정보에 판매량 추가하여 Dto 구성
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDto.setTotalSold(totalSold.intValue()); // 판매량 정보 추가
			return productDto;
		}).collect(Collectors.toList());
	}
}
