package com.eccomerce_wilson.eccomerce.Exceptions.NotFound;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("El producto con ID " + productId + " no existe.");
    }

}
