package kr.hhplus.be.server.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.customer.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
