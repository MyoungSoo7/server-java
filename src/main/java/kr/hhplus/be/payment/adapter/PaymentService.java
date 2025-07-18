package kr.hhplus.be.payment.adapter;

import kr.hhplus.be.coupons.service.CouponService;
import kr.hhplus.be.payment.domain.service.PointService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.customer.dto.CustomerDto;
import kr.hhplus.be.customer.service.CustomerService;
import kr.hhplus.be.order.entity.Orders;
import kr.hhplus.be.order.service.OrderService;
import kr.hhplus.be.coupons.entity.Coupons;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
// 포트를 구현하여 실제 데이터 처리나 통신
// 애플리케이션 레이어는 포트 + 어댑터를 연결하여 실제 기능
public class PaymentService {

	private final CustomerService customerService;
	private final OrderService orderService;
	private final PointService pointService;
	private final CouponService couponService;
	private final KafkaTemplate<String, String> kafkaTemplate;

	private static final String TOPIC = "order_topic";
	private static final String INSUFFICIENT_BALANCE_MESSAGE = "잔액이 부족합니다.";
	private static final String INVALID_COUPON_MESSAGE = "유효하지 않은 쿠폰입니다.";


	@Transactional
	public void payOrderByPoint(Long customerId, Long orderId,  int quantity, Long couponId) {
		Orders order = orderService.createOrder(customerId, orderId, quantity);
		// 총 결제 금액 계산 (쿠폰 할인 포함)
		int totalPrice = calculateTotalPriceWithDiscount(order.getTotalPrice(), couponId, customerId);
		// 결제 및 주문 상태 갱신
		deductUserPointsAndMarkOrderPaid(customerId, totalPrice, order);
		// Kafka를 통해 주문 데이터 전송
		publishOrderToKafka(order);
	}

	private int calculateTotalPriceWithDiscount(int originalPrice, Long couponId, Long customerId) {
		if (couponId == null) {
			return originalPrice;
		}
		Coupons coupon = couponService.getUserCoupons(couponId)
			.orElseThrow(() -> new IllegalArgumentException(INVALID_COUPON_MESSAGE));
		couponService.useCoupon(couponId, customerId);
		return originalPrice - couponService.calculateDiscount(coupon, originalPrice);
	}

	private void deductUserPointsAndMarkOrderPaid(Long customerId, int totalPrice, Orders order) {
		validatePayment(customerId, totalPrice);
		pointService.use(customerId, totalPrice);
		// 주문 상태 결재완료 및 저장
		order.setPaid(true); 
		orderService.saveOrder(order);
	}

	private void validatePayment(Long customerId, int totalPrice) {
		CustomerDto customer = customerService.getCustomerById(customerId);
		if (customer.getPoint() < totalPrice) {
			throw new IllegalArgumentException(INSUFFICIENT_BALANCE_MESSAGE);
		}
	}

	private void publishOrderToKafka(Orders order) {
		// Kafka에 메시지 전송
		kafkaTemplate.send(TOPIC, order.toString());
	}

}
