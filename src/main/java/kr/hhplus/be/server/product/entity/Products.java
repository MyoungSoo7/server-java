package kr.hhplus.be.server.product.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String productDescription;
    private String productImageUrl;
    private String productCategory;
    private int productPrice;
    private int productStock;
    private boolean productIsActive;
    private String productCreatedAt;
    private String productUpdatedAt;




}
