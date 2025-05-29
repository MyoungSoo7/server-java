package kr.hhplus.be.server.order.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.order.entity.Orders;
import kr.hhplus.be.server.order.repository.OrderRepository;
import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;

/*
사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API 를 작성합니다.
결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다. ( 데이터 플랫폼이 어플리케이션 외부 라는 가정만 지켜 작업해 주시면 #됩니다 )*/


@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductService productService;

	public Orders createOrder(Long customerId, Long productId, int quantity) {
		// 상품 정보 확인
		ProductDto product = productService.selectProductById(productId);

		// 재고 확인
		if (product.getProductStock() < quantity) {
			throw new IllegalArgumentException("상품 재고가 부족합니다.");
		}

		// 총 주문 금액 계산
		int totalPrice = product.getProductPrice() * quantity;

		// 주문 생성
		Orders order = Orders.builder()
			.customerId(customerId)
			.productId(productId)
			.productPrice(product.getProductPrice())
			.quantity(quantity)
			.totalPrice(totalPrice)
			.isPaid(false) // 초기값: 결제 전 상태
			.createdAt(String.valueOf(LocalDateTime.now()))
			.paymentMethod("P")
			.deliveryAddress("서울시 강남구")
			.deliveryDate(String.valueOf(LocalDateTime.now().plusDays(1)))
			.createdAt( String.valueOf( LocalDateTime.now() ))
			.orderUpdatedAt(String.valueOf( LocalDateTime.now() ))
			.build();

		// 상품 재고 감소
		productService.updateProductStock(productId, quantity);

		// 주문 저장
		return orderRepository.save(order);
	}

	public void saveOrder(Orders order) {
		// 주문 저장
		orderRepository.save(order);
	}
}
