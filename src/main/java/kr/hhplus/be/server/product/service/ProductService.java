package kr.hhplus.be.server.product.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

}
