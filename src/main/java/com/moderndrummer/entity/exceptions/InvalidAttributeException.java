package com.moderndrummer.entity.exceptions;

public class InvalidAttributeException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public InvalidAttributeException(final String s) {
        super(s);
    }

}
