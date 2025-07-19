package kr.hhplus.be.customer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
/*    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CustomerService customerService;*/

    @Test
    @DisplayName("고객 ID로 고객을 정상 조회한다")
    public void getCustomerById_success() {
        System.out.println(1);

       /* // given
        Customers customer = Customers.builder().id(1L).name("홍길동").build();
        CustomerDto dto = CustomerDto.builder().id(1L).name("홍길동").build();
        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));
        given(modelMapper.map(customer, CustomerDto.class)).willReturn(dto);
        // when
        CustomerDto result = customerService.getCustomerById(1L);
        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("홍길동");*/
    }

    @Test
    @DisplayName("고객 ID로 조회 시 고객이 없으면 예외를 던진다")
    public void getCustomerById_notFound() {
     /*   // given
        given(customerRepository.findById(2L)).willReturn(Optional.empty());
        // when & then
        assertThatThrownBy(() -> customerService.getCustomerById(2L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining("고객을 찾을 수 없습니다");*/
    }
}