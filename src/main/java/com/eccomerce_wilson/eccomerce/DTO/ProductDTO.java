package com.eccomerce_wilson.eccomerce.DTO;


import com.eccomerce_wilson.eccomerce.Entity.Product;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private Float price;
    private  Integer stockQuantity;
    private Long categoryId;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.categoryId= product.getCategory() != null ? product.getCategory().getCategoryId() : null ;
    }


}

