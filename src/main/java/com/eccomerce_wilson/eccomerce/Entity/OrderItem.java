package com.eccomerce_wilson.eccomerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn (name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    private  Integer quantity;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "SUBTOTAL")
    private Double subTotal;

    @PrePersist
    @PreUpdate
    private void calcularSubtotal(){
        if(this.price != null && this.quantity != null){
            this.subTotal = this.price * this.quantity;
        }else {
            this.subTotal = 0.0;
        }
    }


}

