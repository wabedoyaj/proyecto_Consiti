package com.eccomerce_wilson.eccomerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_DATE", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "TOTAL_AMOUNT")
    private  Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

}

