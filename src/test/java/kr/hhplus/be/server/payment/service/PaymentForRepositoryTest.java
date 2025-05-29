package kr.hhplus.be.server.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;
import kr.hhplus.be.server.order.entity.Orders;
import kr.hhplus.be.server.order.repository.OrderRepository;
import kr.hhplus.be.server.payment.entity.Payments;
import kr.hhplus.be.server.payment.repository.PaymentRepository;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class PaymentForRepositoryTest {

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private PaymentRepository paymentRepository;

	// 고객이 상품들을 등록하고, 포인트로 결제했다.
	Customers customers = new Customers();
	Products products = new Products();
	Orders orders = new Orders();
	Payments payments = new Payments();

	@BeforeEach
	void setUp() {
		customers.setId(1L);
		customers.setName("홍길동");
		customers.setPhoneNumber("010-1234-5678");
		customers.setEmail("iamipro@naver.com");

		products.setId(1L);
		products.setProductName("세발자전거");
		products.setProductPrice(10000);
		products.setProductStock(100);
		products.setProductDescription("세발자전거의 설명입니다.");
		products.setProductCategory("가전");

		orders.setId(1L);
		orders.setCustomerId(1L);
		orders.setProductId(1L);
		orders.setProductPrice(10000);
		orders.setStatus("주문완료");

		payments.setId(1L);
		payments.setUserId(1L);
		payments.setProductId(1L);
		payments.setOrderId(1L);
		payments.setPaymentPrice(10000);

	}


	@Test
	@DisplayName("사용자 등록")
	void insertUserPoint() {

		when(customerRepository.save(any())).thenReturn(customers);

		//when
		Customers result = customerRepository.save(customers);

		//then
		assertEquals(customers.getId(), result.getId());
		assertEquals(customers.getName(), result.getName());
		assertEquals(customers.getPhoneNumber(), result.getPhoneNumber());
		assertEquals(customers.getEmail(), result.getEmail());
	}

	@Test
	@DisplayName("상품등록")
	public void insertProduct() {
		when(productRepository.save(any())).thenReturn(products);

		//when
		Products result = productRepository.save(products);

		assertEquals(products.getId(), result.getId());
		assertEquals(products.getProductName(), result.getProductName());
		assertEquals(products.getProductPrice(), result.getProductPrice());
		assertEquals(products.getProductStock(), result.getProductStock());
		assertEquals(products.getProductDescription(), result.getProductDescription());
		assertEquals(products.getProductCategory(), result.getProductCategory());

	}


	@Test
	@DisplayName("주문등록")
	public void orderInsert() {
		when(orderRepository.save(any())).thenReturn(orders);

		Orders result = orderRepository.save(orders);

		assertEquals(orders.getId(), result.getId());
		assertEquals(orders.getCustomerId(), result.getCustomerId());
		assertEquals(orders.getProductId(), result.getProductId());
		assertEquals(orders.getProductPrice(), result.getProductPrice());
		assertEquals(orders.getStatus(), result.getStatus()); 

	}
	
	@Test
	@DisplayName("결제등록")
	public void paymentInsert() {

		when(paymentRepository.save(any())).thenReturn(payments);

		Payments result = paymentRepository.save(payments);

		assertEquals(payments.getId(), result.getId());
		assertEquals(payments.getUserId(), result.getUserId());
		assertEquals(payments.getProductId(), result.getProductId());
		assertEquals(payments.getOrderId(), result.getOrderId());
		assertEquals(payments.getPaymentPrice(), result.getPaymentPrice());
		
	}

}