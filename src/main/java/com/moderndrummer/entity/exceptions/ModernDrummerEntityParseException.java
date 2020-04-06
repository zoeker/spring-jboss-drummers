package com.moderndrummer.entity.exceptions;

import java.text.ParseException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class ModernDrummerEntityParseException extends ParseException {

    String errorMessage = "";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    ModernDrummerEntityParseException() {
        super("", 0);
    }

    /**
     * Constructor for DiveMasterParseException.
     * 
     * @param s
     *            String
     */
    public ModernDrummerEntityParseException(final String s) {
        super(s, 0);
        errorMessage = s;
    }

    /**
     * Constructor for DiveMasterParseException.
     * 
     * @param s
     *            String
     * @param errorOffset
     *            int
     */
    public ModernDrummerEntityParseException(final String s, final int errorOffset) {
        super(s, errorOffset);
    }

    /**
     * Method getErrorMessage.
     * 
     * @return String
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
