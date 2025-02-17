package com.eccomerce_wilson.eccomerce.Exceptions;

public class ProductExistsException extends  RuntimeException {
    public ProductExistsException(Long productId) {
        super("El producto con ID " + productId + " ya existe.");
    }
}
