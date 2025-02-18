package com.eccomerce_wilson.eccomerce.DTO;


import com.eccomerce_wilson.eccomerce.Entity.Order;
import com.eccomerce_wilson.eccomerce.Entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long orderItemId;

    private Long orderId;

    private Long productId;

    private  Integer quantity;

    private Double price;

    private  Double subTotal;

}

