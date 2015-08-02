package com.moderndrummer.util;

import java.math.BigInteger;

import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.model.Member;

// conpem 15 dec 2014
public class ObjectUtil<E> {

  public static <T> boolean nullElements(final T... e) {
    for (final T parameter : e) {
      if (parameter == null) {
        return true;
      }
    }

    return false;
  }


  public static boolean verifyMemberExists(final Object object) {
    if (nullElements(object)) {
      return false;
    }
    if (!(object instanceof Member)) {
      return false;
    }
    if (((Member) object).getId() == 0) {
      return false;
    }

    return true;
  }


  public static <E> boolean verifyObjectExists(E e) {
    if (e == null) {
      return false;
    }
    if (e instanceof Member) {
      return Long.valueOf(((Member) e).getId()).intValue() > 0;
    }


    return true;
  }

  public static <E> int getId(E e) {
    if (e == null) {
      return 0;
    }
    if (e instanceof Member) {
      return Long.valueOf(((Member) e).getId()).intValue();
    }


    return 0;
  }

  public static boolean validIntegerValue(final int value) {
    if (!noNullElements(value) && value == 0) {
      return false;
    }

    return true;
  }
  
  public static <E> boolean isValid(E e) {
    if  (!noNullElements(e) ) {
      return false;
    }
   

    return true;
  }
  
  
  
  public static <E> boolean isValidId(E e) {
    if  (nullElements(e) ) {
      return false;
    }
    if (e instanceof Member) {
      return Long.valueOf(((Member) e).getId()).intValue() > 0;
    }
    
    return false;
  }

  public static <T> boolean noNullElements(final T... e) {
    for (final T parameter : e) {
      if (parameter == null) {
        return false;
      }
    }

    return true;
  }

  private Integer isValidReturnInteger(E e) throws ModernDrummerException {
    if (e == null) {
      throw new ModernDrummerException();
    }
    if (e instanceof Double) {
      return ((Double) e).intValue();
    } else if (e instanceof Integer) {
      return ((Integer) e).intValue();
    } else if (e instanceof Float) {
      return ((Float) e).intValue();
    } else if (e instanceof BigInteger) {
      return ((BigInteger) e).intValue();
    } else if (e instanceof Long) {
      return ((Long) e).intValue();
    } else if (e instanceof Short) {
      return ((Short) e).intValue();
    }
    return 0;
  }

}
