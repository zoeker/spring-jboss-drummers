package com.moderndrummer.entity.exceptions;

public class FileUploaderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for DiveMasterFileUploaderException.
     * 
     * @param s
     *            String
     */
    public FileUploaderException(final String s) {
        super(s);

    }
}