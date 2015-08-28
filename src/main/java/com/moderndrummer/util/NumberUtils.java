package com.moderndrummer.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moderndrummer.ejb.regexp.RegExpExpressions;
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class NumberUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger("errorslog");

	public static BigDecimal bigDecimal(final String value) {
		if (value == null || value.length() == 0 || !value.matches(RegExpExpressions.DIGITS_SEPARATORS)) {
			return BigDecimal.ZERO;
		} else {
			if (value.contains(",") || value.contains(".")) {
				try {

					final NumberFormat format = NumberFormat.getInstance();
					final Number number = format.parse(value);
					return BigDecimal.valueOf(number.doubleValue());

				} catch (final ParseException e) {
					LOGGER.error("failed parse value " + value, e);
					return BigDecimal.ZERO;
				}
			}

			return BigDecimal.valueOf(Long.valueOf(value));
		}
	}

}
