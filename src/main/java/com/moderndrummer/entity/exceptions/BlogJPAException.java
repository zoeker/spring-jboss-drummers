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
public class BlogJPAException extends DataAccessException {

    private static final long serialVersionUID = -4744430183523721711L;

    private String message = "This is an exception..";

    public BlogJPAException(String message) {
        super(message);

    }

    @Override
    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }

}
