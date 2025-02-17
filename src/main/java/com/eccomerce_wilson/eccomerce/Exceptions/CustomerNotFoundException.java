package com.eccomerce_wilson.eccomerce.Exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long customerId) {

        super("El cliente con ID " + customerId + " no existe.");
    }

}
