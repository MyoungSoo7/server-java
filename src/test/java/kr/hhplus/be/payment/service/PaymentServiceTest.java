package kr.hhplus.be.payment.service;

import kr.hhplus.be.payment.adapter.PaymentService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

	@Mock
	private PaymentService paymentService;

	@Test
	@DisplayName("결제 테스트")
	public void paymentTest() {
		long customerId = 1L;
		long orderId = 1L;
		int quantity = 1;
		long couponId = 1L;

		//when


		//then

		// 결제 성공
		// 결제 실패
	}

}