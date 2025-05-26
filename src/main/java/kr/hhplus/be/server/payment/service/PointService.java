package kr.hhplus.be.server.payment.service;

import kr.hhplus.be.server.payment.entity.PointHistory;
import kr.hhplus.be.server.payment.entity.UserPoint;

import java.util.List;

public interface PointService {

    UserPoint selectUserPoint(long id);

    List<PointHistory> selectUserPointHistory(long id);

    UserPoint chargeUserPoint(long id, long amount);

    UserPoint useUserPoint(long id, long amount);

}
