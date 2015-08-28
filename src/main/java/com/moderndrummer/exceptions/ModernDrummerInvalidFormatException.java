package com.moderndrummer.exceptions;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class ModernDrummerInvalidFormatException extends IllegalArgumentException {

    /**
     * 
     */
    private static final long serialVersionUID = -6893460911475103181L;
    String message = "";

    /**
     * Constructor for InvalidDateFormatException.
     * 
     * @param s
     *            String
     */
    public ModernDrummerInvalidFormatException(final String s) {
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
