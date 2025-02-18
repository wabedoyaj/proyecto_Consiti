package com.eccomerce_wilson.eccomerce.Exceptions;

public class OrderItemExistsException extends RuntimeException{
    public OrderItemExistsException(Long orderItemId) {

        super("La order Item con ID " + orderItemId + " ya existe.");
    }
}
