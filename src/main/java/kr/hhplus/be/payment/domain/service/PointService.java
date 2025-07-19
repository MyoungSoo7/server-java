package kr.hhplus.be.payment.domain.service;

import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.customer.entity.Customers;
import kr.hhplus.be.customer.repository.CustomerRepository;
import kr.hhplus.be.points.dto.CustomerPointHistoryDto;
import kr.hhplus.be.points.entity.CustomerPointHistory;
import kr.hhplus.be.payment.domain.dto.TransactionType;
import kr.hhplus.be.config.UserPointLockManager;
import kr.hhplus.be.payment.port.outbound.CustomerPointHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//엔터티 객체의 비즈니스 로직을 조합하거나 처리
public class PointService {

	private final CustomerRepository customerRepository;
	private final CustomerPointHistoryRepository customerPointHistoryRepository;
	private final UserPointLockManager lockManager;
	private static final long MAX_POINT = 10_000_000;
	private static final long MIN_USE_CHARGE_POINT = 500L;

	//식별자를 통해 해당 사용자의 잔액을 조회
	public int selectUserPoint(long customerId) {
		return customerRepository.findById(customerId).get().getPoint();
	}

	public CustomerPointHistoryDto selectUserPointHistory(long customerId) {
		return customerPointHistoryRepository.findByCustomerId(customerId);
	}


	//잔액 충전 및 포인트 충전 정책 ( 충전시 500 이상, 최대 천만)
	@Transactional
	public void charge(long customerId, int amount) {
		Customers customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

		int point = customer.getPoint();

		if (amount < MIN_USE_CHARGE_POINT) {
			throw new IllegalArgumentException("충전할 포인트는 500 이상이어야 합니다.");
		}
		if (point + amount > MAX_POINT) {
			throw new IllegalArgumentException("최대 포인트는 " + MAX_POINT + "입니다.");
		}

		ReentrantLock lock = lockManager.getLock(customerId);
		lock.lock();
		// 충전시 순서대로 충전되도록 lock
		try {

			// 충전 잔액 저장
			Customers customers = customerRepository.findById(customerId).get();
			customers.setPoint(point + amount);
			customerRepository.save(customers);

			// 충전이력 남김
			CustomerPointHistory customerPointHistory = CustomerPointHistory.builder()
				.customerId(customerId)
				.point(amount)
				.type(TransactionType.CHARGE)
				.updateMillis(System.currentTimeMillis())
				.build();

			customerPointHistoryRepository.save(customerPointHistory);
		} finally {
			lock.unlock();
		}

	}

	//잔액 사용
	@Transactional
	public void use(long customerId, int amount) {

		Customers customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

		int point = customer.getPoint();

		if (amount < MIN_USE_CHARGE_POINT) {
			throw new IllegalArgumentException("사용할 포인트는 500 이상이어야 합니다.");
		}
		if (point < amount) {
			throw new IllegalArgumentException("포인트가 부족합니다.");
		}

		ReentrantLock lock = lockManager.getLock(customerId);
		lock.lock();
		// 충전시 순서대로 충전되도록 lock
		try {
			// 잔액 사용 저장
			Customers customers = customerRepository.findById(customerId).get();
			customers.setPoint(point - amount);
			customerRepository.save(customers);

			// 충전이력 남김
			CustomerPointHistory customerPointHistory = CustomerPointHistory.builder()
				.customerId(customerId)
				.point(amount)
				.type(TransactionType.USE)
				.updateMillis(System.currentTimeMillis())
				.build();

			customerPointHistoryRepository.save(customerPointHistory);
		} finally {
			lock.unlock();
		}
	}


    /*public List<PointHistory> selectUserPointHistory(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }*/

}
