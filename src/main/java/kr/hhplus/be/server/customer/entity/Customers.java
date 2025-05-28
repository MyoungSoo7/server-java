package kr.hhplus.be.server.customer.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long point;
    private String name;
    private String phoneNumber;
    private String address1;
    private String address2;
    private String email;
    private Date birthDate;
    private String createdAt;
    private String updatedAt;



}
