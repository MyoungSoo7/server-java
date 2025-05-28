package kr.hhplus.be.server.payment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.payment.entity.UserPoint;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {


	@Test
	@DisplayName("사용자 포인트 조회")
	void selectUserPoint() {
		// given => id
		final long userId = 1L;
		final long amount = 1000L;

		//when
		PointService pointService = new PointService();
		UserPoint userPoint = pointService.selectUserPoint(userId);
	}

}