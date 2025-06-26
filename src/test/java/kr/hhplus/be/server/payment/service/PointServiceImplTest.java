package kr.hhplus.be.server.payment.service;

import kr.hhplus.be.server.points.service.PointServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class PointServiceImplTest {
    @Autowired
    private PointServiceImpl pointService;

    /**
     * 테스트 전 포인트 충전
     * @throws Exception
     */
    @BeforeEach
    void setUp() {
        pointService.charge(1L, 10000); // 테스트 전 초기화
    }

    /**
     * 포인트 충전 테스트
     */
    @Test
    void 동시에_여러건_결제시_포인트_정합성_보장() throws Exception {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        Long userId = 1L;
        int paymentAmount = 1000;

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.pay(userId, paymentAmount);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        int expected = 10000 - (paymentAmount * threadCount);
        int actual = pointService.getBalance(userId);
        Assertions.assertEquals(expected, actual);
    }
}

