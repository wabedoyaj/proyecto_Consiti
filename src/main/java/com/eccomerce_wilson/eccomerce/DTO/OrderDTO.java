package com.eccomerce_wilson.eccomerce.DTO;


import com.eccomerce_wilson.eccomerce.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;
    private  Double totalAmount;
    private Long customerId;


}

