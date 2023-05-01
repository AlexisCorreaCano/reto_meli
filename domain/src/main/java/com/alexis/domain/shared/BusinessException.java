package com.alexis.domain.shared;

public class BusinessException extends RuntimeException{

    public static final String MESSAGE_ITEM_EMPTY_OR_NULL = "Item a buscar es nulo o vacío";

    public BusinessException(String message) {
        super(message);
    }
}
