package kr.hhplus.be.server.payment.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.service.CustomerService;
import kr.hhplus.be.server.order.entity.Orders;
import kr.hhplus.be.server.order.service.OrderService;
import kr.hhplus.be.server.payment.entity.Coupons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

	private final CustomerService customerService;
	private final OrderService orderService;
	private final PointService pointService;
	private final CouponService couponService;
	private static final String TOPIC = "order_topic";
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Transactional
	public void payOrder(Long customerId, Long orderId,  int quantity, Long couponId) {
		// 사용자의 잔액 정보 확인
		Customers user = customerService.getUserById(customerId)
			.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

		// 주문 정보 확인
		Orders order = orderService.createOrder(customerId,orderId,quantity);

		if (order.isPaid()) {
			throw new IllegalArgumentException("이미 결제 완료된 주문입니다.");
		}
		int totalPrice = order.getTotalPrice(); // 원래 주문 금액

		// 쿠폰 사용 여부 체크 및 할인 적용
		if (couponId != null) {
			Coupons coupon = couponService.getUserCoupons(couponId)
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 쿠폰입니다."));

			// 쿠폰 할인율 적용
			int discount = couponService.calculateDiscount(coupon, totalPrice);
			totalPrice -= discount;

			// 쿠폰 사용 처리
			couponService.useCoupon(couponId, customerId);
		}

		// 잔액 확인 및 결제 진행
		if (user.getPoint() < totalPrice) {
			throw new IllegalArgumentException("잔액이 부족합니다.");
		}

		// 포인트 사용 (잔액 차감 /결제완료)
		pointService.use(user.getId(), totalPrice);
		// 결제 완료 처리
		order.setPaid(true);

		// 결제 성공 시 주문 정보 전송
		sendOrderToPlatform(order);
	}

	public void sendOrderToPlatform(Orders order) {
		// Kafka에 메시지 전송
		kafkaTemplate.send(TOPIC, order.toString());

	}

}
