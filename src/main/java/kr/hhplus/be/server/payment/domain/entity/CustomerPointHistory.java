package kr.hhplus.be.server.payment.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.hhplus.be.server.payment.domain.dto.TransactionType;
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
	long id;
	long customerId;
	int point;
	TransactionType type;
	long updateMillis;

}
