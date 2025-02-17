package com.eccomerce_wilson.eccomerce.Exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long categoryId) {
        super("La categoria con ID " + categoryId + " no existe.");
    }

}
