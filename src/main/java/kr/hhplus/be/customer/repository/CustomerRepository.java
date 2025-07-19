package kr.hhplus.be.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.customer.entity.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
