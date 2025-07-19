package kr.hhplus.be.points.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PointServiceImpl {
    private final AtomicInteger balance = new AtomicInteger(0);

    // 포인트 충전
    public void charge(Long userId, int amount) {
        balance.addAndGet(amount);
    }

    // 결제 (포인트 차감)
    public void pay(Long userId, int amount) {
        if (balance.get() < amount) {
            throw new IllegalArgumentException("잔액 부족");
        }
        balance.addAndGet(-amount);
    }

    // 잔액 조회
    public int getBalance(Long userId) {
        return balance.get();
    }
}

