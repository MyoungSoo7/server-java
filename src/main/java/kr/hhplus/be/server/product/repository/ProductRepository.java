package kr.hhplus.be.server.product.repository;

import kr.hhplus.be.server.product.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.product.entity.Products;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    List<ProductDto> findByProductName(String productName);

}
