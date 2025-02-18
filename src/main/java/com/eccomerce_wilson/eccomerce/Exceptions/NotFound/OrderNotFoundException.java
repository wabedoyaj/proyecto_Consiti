package com.eccomerce_wilson.eccomerce.Exceptions.NotFound;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long orderId) {

        super("La orden con ID " + orderId + " no existe.");
    }

}
