package kr.hhplus.be.server.payment.entity;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class UserPointLockManager {
    private final ConcurrentHashMap<Long, ReentrantLock> lock = new ConcurrentHashMap<>();

    public ReentrantLock getLock(final long userId) {
        return lock.computeIfAbsent(userId, id -> new ReentrantLock(true));
    }
}
