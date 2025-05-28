package kr.hhplus.be.server.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.product.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
