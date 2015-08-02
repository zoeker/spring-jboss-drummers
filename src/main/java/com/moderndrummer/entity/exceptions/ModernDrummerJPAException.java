package com.moderndrummer.entity.exceptions;

import org.springframework.dao.DataAccessException;

public class ModernDrummerJPAException extends DataAccessException {
  private static final long serialVersionUID = -7746720746846909675L;
  
  public ModernDrummerJPAException(String msg) {
    super(msg);
  }

 
  

}
