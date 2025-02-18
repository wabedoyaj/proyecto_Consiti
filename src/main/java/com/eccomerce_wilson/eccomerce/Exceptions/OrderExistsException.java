package com.eccomerce_wilson.eccomerce.Exceptions;

public class OrderExistsException extends RuntimeException{
    public OrderExistsException(Long orderId) {
        super("La orden con ID " + orderId + " ya existe.");
    }
}
