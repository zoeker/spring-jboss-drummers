package com.moderndrummer.entity.exceptions;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class EntityDateFormatException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String message = "";

    /**
     * Constructor for InvalidDateFormatException.
     * 
     * @param s
     *            String
     */
    public EntityDateFormatException(final String s) {
        super(s);
        message = s;

    }

    /**
     * Method getInvalidDateFormatException.
     * 
     * @return String
     */
    public String getInvalidDateFormatException() {
        return message;
    }

}
