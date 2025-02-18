package com.eccomerce_wilson.eccomerce.Exceptions.NotFound;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(Long orderItemId) {

        super("La order Item con ID " + orderItemId + " no existe.");
    }

}
