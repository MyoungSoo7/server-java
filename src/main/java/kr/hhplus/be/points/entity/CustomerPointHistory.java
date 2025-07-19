package kr.hhplus.be.points.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.payment.domain.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customerPointHistory")
public class CustomerPointHistory {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private long id;
	private long customerId;
	private int point;
	private TransactionType type;
	private long updateMillis;

}
