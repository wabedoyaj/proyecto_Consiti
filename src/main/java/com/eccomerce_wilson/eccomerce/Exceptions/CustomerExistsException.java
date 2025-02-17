package com.eccomerce_wilson.eccomerce.Exceptions;

public class CustomerExistsException extends RuntimeException{
    public CustomerExistsException(Long customerId) {

        super("El cliente con ID " + customerId + " ya existe.");
    }
}
