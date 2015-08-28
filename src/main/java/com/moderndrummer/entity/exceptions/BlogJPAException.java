package com.moderndrummer.entity.exceptions;

import org.springframework.dao.DataAccessException;
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
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
