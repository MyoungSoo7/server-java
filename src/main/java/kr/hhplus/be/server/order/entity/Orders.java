package kr.hhplus.be.server.order.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private Long productId;
    private int productPrice;
    private int quantity;
    private String orderDate;
    private String status; // 예: "주문 접수", "배송 중", "배송 완료", "취소됨"
    private String deliveryAddress;
    private String paymentMethod; // 예: "신용카드", "계좌이체", "간편결제"
    private String deliveryDate; // 예상 배송 날짜
    private String orderCreatedAt; // 주문 생성 시간
    private String orderUpdatedAt; // 주문 수정 시간
}
