package com.moderndrummer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.entity.exceptions.ModernDrummerException;
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class IntegerConverter {

	/**
	 * Method checkInteger.
	 * 
	 * @param value
	 *            Integer
	 * 
	 * @return Integer
	 */
	public static Integer checkInteger(final Integer value) {
		if (value == null) {
			return Integer.valueOf(0);
		} else {
			return value;
		}
	}

	/**
	 * Method checkInteger.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException * @throws
	 *         DiveMasterParseException * @throws DiveMasterParseException
	 */
	public static Integer checkInteger(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			return Integer.valueOf(0);
		} else if (value.length() > 0 && value.matches("[0-9]+")) {
			return Integer.valueOf(value);
		} else {
			throw new EntityParseException("not a integer value");
		}
	}

	public static Integer emptyValueReturnZero(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			return 0;
		} else if (Pattern.matches("[a-zA-Z]+", value)) {
			throw new EntityParseException(value + " " + ModernDrummerException.NO_NUMBER_VALUE);

		} else if (value.length() > 0) {
			return 0;

		} else {
			throw new EntityParseException(ModernDrummerException.NO_NUMBER_VALUE);

		}
	}

	public static Integer findIntegerValue(final String value) {
		final String regExp = "[0-9]";
		final boolean found = Pattern.compile(regExp).matcher(value).find();

		if (value == null || value.length() == 0) {
			return 1;
		} else if (value.length() > 0 && found) {
			final Pattern pattern = Pattern.compile(regExp);
			final Matcher matcher2 = pattern.matcher(value);
			// Check all occurrences
			Integer counter = 1;
			while (matcher2.find()) {
				counter = Integer.valueOf(value.substring(matcher2.start(), matcher2.end()));
			}

			while (value.indexOf(String.valueOf(counter), 0) != -1) {
				counter++;
			}
			return counter;
		}

		return 1;
	}

	/**
	 * Method getInteger.
	 * 
	 * @param value
	 *            String
	 * 
	 * @return Integer
	 */
	public static Integer getInteger(final String value) {
		if (value == null) {
			return new Integer(0);
		} else if (value.length() > 0 && value.matches("[0-9]+")) {
			return Integer.getInteger(value);
		} else {
			return 0;
		}
	}

	/**
	 * Method toDouble.
	 * 
	 * @param value
	 *            Integer
	 * 
	 * @return Double
	 */
	public static Double toDouble(final Integer value) {
		if (value == null) {
			return Double.valueOf(0d);
		} else {
			return value.doubleValue();
		}
	}

	/**
	 * Method toFloat.
	 * 
	 * @param value
	 *            Integer
	 * 
	 * @return Float
	 */
	public static Float toFloat(final Integer value) {
		if (value == null) {
			return new Float(0f);
		} else {
			return value.floatValue();
		}
	}

	/**
	 * Method toInt.
	 * 
	 * @param value
	 *            Long
	 * 
	 * @return Integer
	 */
	public static Integer toInt(final Long value) {
		if (value == null) {
			return new Integer(0);
		} else {
			return value.intValue();
		}
	}

	/**
	 * Method toInteger.
	 * 
	 * @param value
	 *            Long
	 * 
	 * @return Integer
	 */
	public static Integer toInteger(final Long value) {
		if (value == null) {
			return new Integer(0);
		} else {
			return value.intValue();
		}
	}

	/**
	 * Method toIntegerFromMs.
	 * 
	 * @param value
	 *            Long
	 * 
	 * @return Integer
	 */
	public static Integer toIntegerFromMs(final Long value) {
		if (value == null) {
			return new Integer(0);
		} else {
			return value.intValue() / 1000;
		}
	}

	/**
	 * Method toLong.
	 * 
	 * @param value
	 *            Integer
	 * 
	 * @return Long
	 */
	public static Long toLong(final Integer value) {
		if (value == null) {
			return new Long(0L);
		} else {
			return value.longValue();
		}
	}

	/**
	 * Method validateInteger.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException * @throws
	 *         DiveMasterParseException * @throws DiveMasterParseException
	 */
	public static Integer validateInteger(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			throw new EntityParseException(ModernDrummerException.EMPTY_NUMBER_VALUE);
		} else if (value.length() > 0 && value.matches("[0-9]+")) {
			return Integer.valueOf(value);
		} else {
			throw new EntityParseException(ModernDrummerException.NO_NUMBER_VALUE);
		}
	}

	/**
	 * Method validateInteger.
	 * 
	 * @param value
	 *            String
	 * @param field
	 *            String
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException
	 */
	public static Integer validateInteger(final String value, final String field) throws EntityParseException {
		if (value == null || value.length() == 0) {
			throw new EntityParseException(field + " " + ModernDrummerException.EMPTY_NUMBER_VALUE);
		} else if (value.length() > 0 && value.matches("[0-9]+")) {
			return Integer.valueOf(value);
		} else {
			throw new EntityParseException(field + " " + ModernDrummerException.NO_NUMBER_VALUE);
		}
	}

	/**
	 * Method validateIntegerCountry.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException * @throws
	 *         DiveMasterParseException
	 */
	public static Integer validateIntegerCountry(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			throw new EntityParseException(ModernDrummerException.EMPTY_NUMBER_VALUE + " Choose country");
		} else if (Pattern.matches("[a-zA-Z]+", value)) {
			throw new EntityParseException(value + " " + ModernDrummerException.NO_NUMBER_VALUE + " Choose country");

		} else if (value.length() > 0) {
			final boolean matches = Pattern.matches("[0]{1}", value);
			if (matches) {
				throw new EntityParseException(
						value + " " + ModernDrummerException.ZERO_NUMBER_VALUE + " Choose country");
			} else {
				return Integer.valueOf(value);
			}

		} else {
			throw new EntityParseException(ModernDrummerException.ZERO_NUMBER_VALUE + " Choose country");
		}
	}

	/**
	 * Method validateIntegerUpdate.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException
	 */
	public static Integer validateIntegerUpdate(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			return 0;
		} else if (value.length() > 0 && value.matches("[0-9]+")) {
			return Integer.valueOf(value);
		} else {
			throw new EntityParseException(ModernDrummerException.NO_NUMBER_VALUE);
		}
	}

	/**
	 * Method validateIntegerZero.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException * @throws
	 *         DiveMasterParseException * @throws DiveMasterParseException
	 */
	public static Integer validateIntegerZero(final String value) throws EntityParseException {
		if (value == null || value.length() == 0) {
			throw new EntityParseException(ModernDrummerException.EMPTY_NUMBER_VALUE);
		} else if (Pattern.matches("[a-zA-Z]+", value)) {
			throw new EntityParseException(value + " " + ModernDrummerException.NO_NUMBER_VALUE);

		} else if (value.length() > 0) {
			final boolean matches = Pattern.matches("[0]{1}", value);
			if (matches) {
				throw new EntityParseException(value + " " + ModernDrummerException.ZERO_NUMBER_VALUE);
			} else {
				return Integer.valueOf(value);
			}

		} else {
			throw new EntityParseException(ModernDrummerException.NO_NUMBER_VALUE);

		}
	}

	/**
	 * Method validateIntegerZero.
	 * 
	 * @param value
	 *            String
	 * @param field
	 *            String
	 * 
	 * 
	 * @return Integer * @throws DiveMasterParseException
	 */
	public static Integer validateIntegerZero(final String value, final String field) throws EntityParseException {

		if (value == null || value.length() == 0) {
			throw new EntityParseException(field + " " + ModernDrummerException.EMPTY_NUMBER_VALUE);
		} else if (Pattern.matches("[a-zA-Z]+", value)) {
			throw new EntityParseException(field + " " + value + " " + ModernDrummerException.NO_NUMBER_VALUE);

		} else if (value.length() > 0) {
			final boolean matches = Pattern.matches("[0]{1}", value);
			if (matches) {
				throw new EntityParseException(field + " " + value + " " + ModernDrummerException.ZERO_NUMBER_VALUE);
			} else {
				return Integer.valueOf(value);
			}

		} else {
			throw new EntityParseException(field + " " + ModernDrummerException.NO_NUMBER_VALUE);

		}
	}

}
