package kr.hhplus.be.server.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.hhplus.be.server.customer.repository.CustomerRepository;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.repository.ProductRepository;

/*상품 정보 ( ID, 이름, 가격, 잔여수량 ) 을 조회하는 API 를 작성합니다.
조회시점의 상품별 잔여수량이 정확하면 좋습니다.*/
class ProductServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ProductRepository productRepository;
	@InjectMocks
	private ProductService productService; // 실질적으로 테스트할 클래스

	private Products products;
	private static final Long SAMPLE_PRODUCT_ID = 1L; // 상수 도입

	@BeforeEach
	void setUp()   {
		MockitoAnnotations.openMocks(this);
		products = createSampleProduct();
	}

	private Products createSampleProduct() { // 상품 생성 로직 추출
		return Products.builder()
			.id(SAMPLE_PRODUCT_ID)
			.productName("세발자전거")
			.productPrice(10000)
			.productStock(100)
			.productDescription("세발자전거의 설명입니다.")
			.productCategory("가전")
			.build();
	}

	@Test
	@DisplayName("상품 조회 테스트")
	void productTest() {
		//given
		when(productRepository.findById(SAMPLE_PRODUCT_ID)).thenReturn(Optional.of(products));

		//when
		Products result = productRepository.findById(SAMPLE_PRODUCT_ID).orElseThrow();

		//then
		assertEquals(products, result);
		// 조회시점의 상품별 잔여수량이 정확하면 좋습니다.
		assertEquals(products.getProductStock(), result.getProductStock());
	}



}