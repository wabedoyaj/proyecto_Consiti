package com.eccomerce_wilson.eccomerce.Exceptions;

public class CategoryExistsException extends RuntimeException{
    public CategoryExistsException(Long categoryId) {
        super("La categoria con ID " + categoryId + " ya existe.");
    }
}
