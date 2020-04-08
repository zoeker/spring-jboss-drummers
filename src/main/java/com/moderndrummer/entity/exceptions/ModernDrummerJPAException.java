package com.moderndrummer.entity.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ModernDrummerJPAException extends DataAccessException {
    private static final long serialVersionUID = -7746720746846909675L;

    public ModernDrummerJPAException(String msg) {
        super(msg);
    }

}
