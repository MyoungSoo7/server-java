package kr.hhplus.be.server.payment.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;
import kr.hhplus.be.server.payment.database.PointHistoryTable;
import kr.hhplus.be.server.payment.database.UserPointTable;
import kr.hhplus.be.server.payment.entity.Payments;
import kr.hhplus.be.server.payment.entity.PointHistory;
import kr.hhplus.be.server.payment.entity.TransactionType;
import kr.hhplus.be.server.payment.entity.UserPoint;
import kr.hhplus.be.server.payment.entity.UserPointLockManager;
import kr.hhplus.be.server.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final UserPointTable userPointTable;
    private final PointHistoryTable pointHistoryTable;
    private final UserPointLockManager lockManager;
    private final PaymentRepository paymentRepository;

    //식별자를 통해 해당 사용자의 잔액을 조회
    @Override
    public UserPoint selectUserPoint(long id) {
        return userPointTable.selectById(id);
    }

    //결제에 사용될 금액을 충전하는 API
    // => 사용자 식별자 및 충전할 금액을 받아 잔액을 충전?
    @Override
    public UserPoint chargeUserPoint(long id, long amount) {
        ReentrantLock lock = lockManager.getLock(id);
        lock.lock();
        // 충전시 순서대로 충전되도록 lock
        try {
            Optional<Payments> payments = paymentRepository.findById(id);
            UserPoint currentUser = userPointTable.selectById(id);
            // 충전 금액을 가산 후 충전
            UserPoint validUserPoint = currentUser.charge(amount);
            // 충전요금 금액에 대한 검사
            UserPoint result = userPointTable.insertOrUpdate(validUserPoint.id(), validUserPoint.point());
            // 충전 후 금액이 적합한지 검사
            pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
            return result;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public UserPoint useUserPoint(long id, long usePoint) {
        ReentrantLock lock = lockManager.getLock(id);
        lock.lock();
        try {
            UserPoint currentUserPoint = userPointTable.selectById(id);
            // 충전요금 금액에 대한 검사
            UserPoint validUserPoint = currentUserPoint.use(usePoint);
            UserPoint result = userPointTable.insertOrUpdate(validUserPoint.id(), validUserPoint.point()  );
            pointHistoryTable.insert(validUserPoint.id(), usePoint, TransactionType.USE, System.currentTimeMillis());
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<PointHistory> selectUserPointHistory(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }



}
