package kr.hhplus.be.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * UserPointLockManager
 * 사용자별 포인트 잠금을 관리하는 클래스
 * 각 사용자 ID에 대해 ReentrantLock을 생성하고 관리
 */
@Component
public class UserPointLockManager {
    private final ConcurrentHashMap<Long, ReentrantLock> lock = new ConcurrentHashMap<>();

    /**
     * 사용자 ID에 대한 ReentrantLock을 반환
     * 해당 사용자 ID가 없으면 새로 생성하여 반환
     *
     * @param userId 사용자 ID
     * @return 해당 사용자 ID에 대한 ReentrantLock
     */
    public ReentrantLock getLock(final long userId) {
        return lock.computeIfAbsent(userId, id -> new ReentrantLock(true));
    }
}
