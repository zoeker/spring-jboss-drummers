package com.moderndrummer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.EmailValidator;

import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.messages.ModernDrummerMessages;

/***
 * 
 * @author conpem 2015-08-03
 *
 */

public class DCStringUtils {

  protected static final String EMAIL_PATTERN =
      "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static final String VALID_STRING = "VALID";
  public static final String IN_VALID_STRING = "INVALID";

  public static final String swedishPhoneRegExp = "^[0-9]{1,4}-[0-9]{3,7}$";
  public static final String basicPhoneRegExp =
      "^([0-9]{1,10}?\u002D)+[0-9]{1,10}$";
  public static final String onlyDigits = "^([0-9]{5,12})$";
  public static final String twoNumbers =
      "(\\+\\(\\d{2,3}\\))?(\\d{2,4}-)?\\d{5,12}";

  public static String getExtensionFileName(String fileName) {
    fileName = fileName.trim();
    final int mid = fileName.lastIndexOf(".");
    final String extension = fileName.substring(mid + 1, fileName.length());

    return extension;
  }

  public static int getExtensionIndex(String fileName) {
    fileName = fileName.trim();
    return fileName.lastIndexOf(".");

  }
  
  public static  boolean isEmpty(String value){
    return StringUtils.isEmpty(value);
  }

  public static int getLastIndexOf(final String value, final char separator) {
    if (isEmpty(value)) {
      return 0;
    }
    int index = value.lastIndexOf(separator);
    if (index == -1) {
      index = value.length();
      if (value.contains(".")) {
        index = getExtensionIndex(value);
      }
    }

    return index;
  }

  public static String getValueAsStringEmpty(final String value) {
    return value != null ? value : "";
  }

  public static String getValueDescriptive(final String value) {
    return value != null ? value : ModernDrummerMessages.NO_DESCRIPTION;
  }

  public static boolean isEmptyOrNull(final Object... value) {

    for (final Object parameter : value) {

      if (parameter == null) {
        return true;
      }
      if (parameter instanceof String) {
        if (((String) parameter).isEmpty()) {
          return true;
        } else {
          return false;
        }

      }

    }

    return false;
  }

  @SuppressWarnings("deprecation")
  public static boolean validateEmail(final String email) {
    final EmailValidator emailValidator = EmailValidator.getInstance();
    return emailValidator.isValid(email);
  }

  public static boolean validateEmailOld(final String email) {
    final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    final Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean validatePhoneNr(final String phoneNr) {

    final Matcher matches = Pattern.compile(twoNumbers).matcher(phoneNr);

    final Matcher mSwedishRegexp =
        Pattern.compile(swedishPhoneRegExp).matcher(phoneNr);

    final Matcher mBasicRegexp =
        Pattern.compile(basicPhoneRegExp).matcher(phoneNr);

    final Matcher digitsRegexp = Pattern.compile(onlyDigits).matcher(phoneNr);

    if (!matches.matches() && !mSwedishRegexp.matches()
        && !mBasicRegexp.matches() && !digitsRegexp.matches()) {
      return false;
    } else {
      return true;
    }

  }

  public static boolean verifyPdf(final String fileName)
      throws EntityParseException {
    if (!isEmptyOrNull(fileName) && fileName.contains(".pdf")) {
      return true;
    } else {
      throw new EntityParseException("invalid filename");
    }
  }

  public static String verifySpecified(final String value) {
    if (isEmptyOrNull(value)) {
      return "Not specified";
    } else {
      return value;
    }
  }

  public static String verifyStringValue(final String value) {
    if (isEmpty(value)) {
      return "";
    } else {
      return value;
    }
  }

  public static String verifyStringValue(final String value,
      final String otherMessage) {
    if (isEmpty(value)) {
      return otherMessage;
    } else {
      return value;
    }
  }

  public static String verifyStringValueNullable(final String value) {
    if (isEmpty(value)) {
      return null;
    } else {
      return value;
    }
  }

  public static <T> boolean verifyUrltoWebSite(final T... object) {

    for (final T parameter : object) {
      if (ObjectUtil.noNullElements(object)) {
        return false;
      }

    }
    return false;

  }

  public static String verifyUrlToWebSite(final String url) {
    if (ObjectUtil.nullElements(url)) {
      return "";
    }
    if (url.startsWith("http://") && url.contains(".")) {
      return url;
    } else {
      return "";
    }
  }

  public int emptyReturnsZero(final String value) {
    if (value == null || value.length() == 0) {
      return 0;
    }

    return Integer.valueOf(value);
  }


}
