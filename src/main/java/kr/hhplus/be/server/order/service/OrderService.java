package kr.hhplus.be.server.order.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.customer.dto.CustomerDto;
import kr.hhplus.be.server.customer.service.CustomerService;
import kr.hhplus.be.server.order.entity.Orders;
import kr.hhplus.be.server.order.repository.OrderRepository;
import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final CustomerService customerService;
	private final ProductService productService;
	private final OrderRepository orderRepository;

	public Orders createOrder(Long customerId, Long productId, int quantity) {
		// 상품 정보 확인
		ProductDto product = validateProductStock(productId, quantity);
		// 주문 생성
		Orders order = buildOrder(customerId, productId, quantity, product);
		// 상품 재고 감소
		productService.updateProductStock(productId, quantity);
		// 주문 저장
		return orderRepository.save(order);
	}

	private ProductDto validateProductStock(Long productId, int quantity) {
		ProductDto productDto = productService.selectProductById(productId);
		if (productDto.getProductStock() < quantity) {
			throw new IllegalArgumentException("상품 재고가 부족합니다.");
		}
		return productDto;
	}

	private Orders buildOrder(Long customerId, Long productId, int quantity, ProductDto productDto) {
		CustomerDto customer = customerService.getUserById(customerId);

		int totalPrice = productDto.getProductPrice() * quantity;
		LocalDateTime now = LocalDateTime.now(); // 동일한 시간 기준 사용을 위해 변수로 저장

		return Orders.builder()
			.customerId(customerId)
			.productId(productId)
			.productPrice(productDto.getProductPrice())
			.quantity(quantity)
			.totalPrice(totalPrice)
			.isPaid(false)  
			.createdAt(now.toString())
			.paymentMethod("P")
			.deliveryAddress(
				(customer.getAddress1() != null ? customer.getAddress1() : "서울시") +
					(customer.getAddress2() != null ? customer.getAddress2() : "강남")
			)
			.deliveryDate(now.plusDays(1).toString())
			.orderUpdatedAt(now.toString())
			.build();
	}


	public void saveOrder(Orders order) {
		// 주문 저장
		orderRepository.save(order);
	}
}
