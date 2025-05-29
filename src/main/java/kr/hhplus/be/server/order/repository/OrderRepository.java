package kr.hhplus.be.server.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.order.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
	@Query("SELECT o.productId, SUM(o.quantity) AS totalSold " +
		"FROM Orders o " +
		"WHERE o.createdAt >= :startDate " +
		"GROUP BY o.productId " +
		"ORDER BY totalSold DESC " +
		"LIMIT :limit")
	List<Object[]> findTopSellingProducts(@Param("startDate") LocalDateTime startDate, @Param("limit") int limit);
}
