package com.moderndrummer.ejb.regexp;

import java.util.regex.Pattern;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class RegExpExpressions {
    public static final String onlyDigits = "^([0-9]{1,12})$";
    public static final String DIGITS_SEPARATORS = "^[0-9,.]*$";

    public static final String DATE_FORMAT_REGEXP = ".*[\\d]{4}_[\\d]{2}_[\\d]{2}.*";
    public static final Pattern INCLUDE_DATE_PATTERN = Pattern
            .compile(".*([\\d]{4}_[\\d]{2}_[\\d]{2}).*");
    public static final String DATE_FORMAT = "[\\d]{4}-[\\d]{2}-[\\d]{2}";

}
