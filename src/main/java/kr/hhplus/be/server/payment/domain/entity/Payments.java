package kr.hhplus.be.server.payment.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long productId;
    private Long orderId; // 주문 ID
    private int paymentPrice; // 결제금액
    // 포인트 = P
    private String paymentMethod; // 결제 방법 (예: 포인트, 신용카드, 계좌이체, 간편결제 등)

    private String paymentStatus; // 결제 상태 (예: 성공, 실패, 대기 중 등)
    private String paymentDate; // 결제 날짜
    private String paymentAmount; // 결제 금액
    private String paymentCreatedAt; // 결제 생성 시간
    private String paymentUpdatedAt; // 결제 수정 시간

}
