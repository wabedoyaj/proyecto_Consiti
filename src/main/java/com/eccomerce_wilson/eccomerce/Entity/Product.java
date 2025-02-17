package com.eccomerce_wilson.eccomerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "STOCK_QUANTITY")
    private  Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


}

