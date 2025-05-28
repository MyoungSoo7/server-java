package kr.hhplus.be.server.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.order.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

}
