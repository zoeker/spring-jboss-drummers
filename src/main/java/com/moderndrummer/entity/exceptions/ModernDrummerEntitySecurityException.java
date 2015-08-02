package com.moderndrummer.entity.exceptions;

public class ModernDrummerEntitySecurityException extends RuntimeException {
    /**
     * @author conpem
     * @realname Conny Pemfors
     * @version $Revision: 1.0 $
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for DiveMasterEntitySecurityException.
     * 
     * @param message
     *            String
     */
    public ModernDrummerEntitySecurityException(final String message) {
        super(message);
    }

    /**
     * Constructor for DiveMasterEntitySecurityException.
     * 
     * @param message
     *            String
     * @param cause
     *            Throwable
     */
    public ModernDrummerEntitySecurityException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

}
