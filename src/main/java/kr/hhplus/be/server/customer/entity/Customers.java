package kr.hhplus.be.server.customer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String email;
    private String birthDate;


}
