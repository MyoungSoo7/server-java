/*
package kr.hhplus.be.order;

import kr.hhplus.be.coupons.service.CouponService;
import kr.hhplus.be.customer.service.CustomerService;
import kr.hhplus.be.order.service.OrderService;
import kr.hhplus.be.payment.adapter.PaymentService;
import kr.hhplus.be.payment.domain.service.PointService;
import kr.hhplus.be.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderIntegrationTest {
    @Autowired
    OrderService orderService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    PointService pointService;
    @Autowired
    CouponService couponService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    @Nested
    @DisplayName("주문 생성 테스트")
    class OrderCreateTest {
        @Test
        @DisplayName("상품, 고객, 수량 등 필수 정보가 올바르게 처리되는지 검증")
        void testOrderCreateWithValidInfo() {
            // given
            Long customerId = customerService.create("테스트고객").getCustomerId();
            Long productId = productService.create("테스트상품", 10000, 10).getProductId();
            int quantity = 2;

            int beforeStock = productService.getProductById(productId).getProductStock();

            // when
            var order = orderService.createOrder(customerId, productId, quantity);

            // then
            assertThat(order.getCustomerId()).isEqualTo(customerId);
            assertThat(order.getProductId()).isEqualTo(productId);
            assertThat(order.getQuantity()).isEqualTo(quantity);
            assertThat(order.getTotalPrice()).isEqualTo(10000 * quantity);
            assertThat(productService.getProductById(productId).getProductStock()).isEqualTo(beforeStock - quantity);
        }

        @Test
        @DisplayName("상품 재고 부족 시 예외가 발생하는지 검증")
        void testOrderCreateWithInsufficientStock() {
            // given
            Long customerId = customerService.create("테스트고객2").getCustomerId();
            Long productId = productService.create("테스트상품2", 5000, 1).getProductId();
            int quantity = 2; // 재고보다 많은 수량

            // when/then
            assertThatThrownBy(() -> orderService.createOrder(customerId, productId, quantity))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("상품 재고가 부족");
        }
    }

    @Nested
    @DisplayName("포인트 결제 테스트")
    class PointPaymentTest {
        @Test
        @DisplayName("포인트 차감 및 부족 시 예외 처리 검증")
        void testPointDeductionAndInsufficient() {
            // given
            // ...포인트 충분/부족 케이스 준비...
            // when/then
            // ...포인트 차감 및 예외 검증...
        }
    }

    @Nested
    @DisplayName("쿠폰 할인 및 사용 이력 테스트")
    class CouponDiscountTest {
        @Test
        @DisplayName("쿠폰 적용 시 할인 및 사용 이력 검증")
        void testCouponDiscountAndHistory() {
            // given
            // ...쿠폰 발급 및 주문 준비...
            // when
            // ...쿠폰 적용 결제...
            // then
            // ...할인 금액, 쿠폰 사용 이력 검증...
        }
    }

    @Nested
    @DisplayName("트랜잭션 일관성 테스트")
    class TransactionConsistencyTest {
        @Test
        @DisplayName("주문-결제-쿠폰 사용 트랜잭션 일관성 검증")
        void testOrderPaymentCouponTransactionConsistency() {
            // given
            // ...주문, 결제, 쿠폰 준비...
            // when
            // ...트랜잭션 내 처리...
            // then
            // ...롤백/커밋 일관성 검증...
        }
    }

    @Nested
    @DisplayName("동시성 문제 테스트")
    class ConcurrencyTest {
        @Test
        @DisplayName("포인트/쿠폰 중복 사용 방지 검증")
        void testPointAndCouponConcurrency() throws Exception {
            // given
            // ...동시성 테스트 준비 (멀티스레드 등)...
            // when
            // ...동시 결제 시도...
            // then
            // ...중복 차감/사용 방지 검증...
        }
    }
}

*/
