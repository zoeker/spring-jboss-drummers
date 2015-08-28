package com.moderndrummer.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moderndrummer.entity.exceptions.EntityDateFormatException;
import com.moderndrummer.entity.exceptions.EntityParseException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class DateConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger("errorslog");

	public static final String[] REGEXP_DATEFORMAT = { "[\\d]{4}-[\\d]{2}-[\\d]{2}", "[\\d]{2}-[\\d]{2}-[\\d]{2}",
			"[\\d]{2}[\\d]{2}[\\d]{2}", "[\\d]{4}[\\d]{2}[\\d]{2}" };

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final DateTimeFormatter DATE_DEFAULT = DateTimeFormat.forPattern("yyyy-MM-dd");
	public static final DateTimeFormatter DATE_YEAR = DateTimeFormat.forPattern("yyyy");

	public static final DateTimeFormatter DATE_TIME = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

	public static final DateTimeFormatter DATE_TIME_SECONDS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	public static Timestamp convertToTimeStamp(final Date date) {
		final long millis = DateUtils.truncate(date, Calendar.MILLISECOND).getTime();
		return new java.sql.Timestamp(millis);
	}

	public static String getDateWithHoursAndMinutes(final Date date) {
		return DATE_TIME.print(new DateTime(date));
	}

	public static int getCurrentYear() {
		final Date current = new Date(System.currentTimeMillis());
		return Integer.valueOf(parseYear(current));
	}

	/**
	 * Method getDate.
	 * 
	 * @param day
	 *            int
	 * @param month
	 *            int
	 * @param year
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDate(final int day, final int month, final int year) {

		final Calendar gigCal = Calendar.getInstance();
		if (year < 2000 && year > 1900) {
			gigCal.set(year, month - 1, day);

		} else if (year < 1900) {
			gigCal.set(year + 2000, month - 1, day);

		} else {
			gigCal.set(year, month - 1, day);

		}
		return gigCal.getTime();

	}

	/**
	 * Method getDateAdd.
	 * 
	 * @param date
	 *            Date
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateAdd(final Date date, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(date.getYear() + 1900, date.getMonth(), date.getDate() + pos, 0, 0, 0);

		return gigCal.getTime();

	}

	/**
	 * Method getDateAdd.
	 * 
	 * @param day
	 *            int
	 * @param month
	 *            int
	 * @param year
	 *            int
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateAdd(final int day, final int month, final int year, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(year, month, day + pos, 0, 0, 0);

		return gigCal.getTime();

	}

	/**
	 * Method getDateAddYear.
	 * 
	 * @param date
	 *            Date
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateAddYear(final Date date, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(date.getYear() + 1900 + pos, date.getMonth(), date.getDate(), 0, 0, 0);

		return gigCal.getTime();

	}

	/**
	 * Method getDateFormat.
	 * 
	 * @param date
	 *            Date
	 * 
	 * @return String
	 */
	public static String getDateFormat(final Date date) {
		if (ObjectUtil.noNullElements(date)) {
			final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			return format.format(date);
		}
		return "";
	}

	public static String getDateFormat(final Date date, final String parseFormat) {
		final SimpleDateFormat format = new SimpleDateFormat(parseFormat);
		return format.format(date);
	}

	/**
	 * Method getDateFormat.
	 * 
	 * @param toParse
	 *            String
	 * 
	 * 
	 * 
	 * 
	 * @return Date * @throws DiveMasterParseException * @throws
	 *         DiveMasterParseException * @throws DiveMasterParseException
	 *         * @throws DiveMasterParseException
	 */
	public static Date getDateFormat(final String toParse) throws EntityParseException {
		try {

			return DATE_DEFAULT.parseDateTime(toParse).toDate();

		} catch (final IllegalArgumentException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException("Invalid date" + " " + toParse);
		}

	}

	/**
	 * Method getDateFormat.
	 * 
	 * @param toParse
	 *            String
	 * @param field
	 *            String
	 * 
	 * 
	 * @return Date * @throws DiveMasterParseException
	 */
	public static Date getDateFormat(final String toParse, final String field) throws EntityParseException {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(toParse);

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException(field + " " + "Invalid date" + " " + toParse);
		}

	}

	public static Date getDateFormatRemoveT(String toParse, final String field) throws EntityParseException {
		try {
			if (toParse == null) {
				throw new EntityParseException(field + " Value is empty");
			}
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (toParse.contains("T")) {
				toParse = toParse.replace("T", " ");

			}
			if (toParse.contains("Z")) {
				toParse = toParse.replace("Z", "");
			}
			return new Date(format.parse(toParse).getTime());

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException(field + " " + p.getMessage());
		}

	}

	public static Date getDateFormatWithT(final String toParse, final String field) throws EntityParseException {
		try {
			if (toParse == null) {
				throw new EntityParseException(field + " Value is empty");
			}
			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			return new Date(format.parse(toParse).getTime());

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException(field + " " + p.getMessage());
		}

	}

	/**
	 * Method getDateFormatWithTime.
	 * 
	 * @param toParse
	 *            String
	 * 
	 * @return Date
	 */
	public static Date getDateFormatWithTime(final String toParse) {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.parse(toParse);

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			return null;
		}

	}

	public static Date getDateFormatWithTime(final String toParse, final String field) throws EntityParseException {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.parse(toParse);

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException("Field: " + field + ", value: " + toParse);
		}

	}

	/**
	 * Method getDateFromString.
	 * 
	 * @param value
	 *            String
	 * 
	 * 
	 * 
	 * 
	 * @return Date * @throws InvalidDateFormatException * @throws
	 *         InvalidDateFormatException * @throws InvalidDateFormatException
	 *         * @throws InvalidDateFormatException
	 */
	public static Date getDateFromString(final String value) throws EntityDateFormatException {
		Locale.getDefault();
		if (value.isEmpty()) {
			return null;
		} else {

			int year = 0, month = 0, day = 0;

			for (final String s : REGEXP_DATEFORMAT) {

				final Pattern pattern = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
				final CharSequence inputStr = value;
				final Matcher matcher = pattern.matcher(inputStr);
				while (matcher.find()) {
					if (value.contains("-")) {
						final int first = value.indexOf("-");
						year = Integer.parseInt(value.substring(0, first));
						month = Integer.parseInt(value.substring(first + 1, first + 3));
						final int second = value.indexOf("-", first + 1);
						day = Integer.parseInt(value.substring(second + 1, second + 3));

					} else {
						if (value.length() == 8) {
							year = Integer.parseInt(value.substring(0, 4));
							month = Integer.parseInt(value.substring(5, 6));
							day = Integer.parseInt(value.substring(7, 7));

						} else if (value.length() == 6) {

							year = Integer.parseInt(value.substring(0, 2));
							month = Integer.parseInt(value.substring(3, 4));
							day = Integer.parseInt(value.substring(5, 6));

						}

					}

					LOGGER.info("value: " + value + " " + year + " " + month + " " + day);

					return getDate(day, month, year);
				} // if find match
			} // end for loop

			throw new EntityDateFormatException("Invalid date");

		}

	}

	/**
	 * Method getDateSubtract.
	 * 
	 * @param date
	 *            Date
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateSubtract(final Date date, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(date.getYear() + 1900, date.getMonth(), date.getDate() - pos, 0, 0, 0);
		return gigCal.getTime();

	}

	/**
	 * Method getDateSubtract.
	 * 
	 * @param day
	 *            int
	 * @param month
	 *            int
	 * @param year
	 *            int
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateSubtract(final int day, final int month, final int year, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(year, month, day - pos, 0, 0, 0);
		return gigCal.getTime();

	}

	/**
	 * Method getDateSubtractYear.
	 * 
	 * @param date
	 *            Date
	 * @param pos
	 *            int
	 * 
	 * @return Date
	 */
	public static Date getDateSubtractYear(final Date date, final int pos) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(date.getYear() + 1900 - pos, date.getMonth(), date.getDate(), 0, 0, 0);

		return gigCal.getTime();

	}

	/**
	 * Method getDateTime.
	 * 
	 * @param day
	 *            int
	 * @param month
	 *            int
	 * @param year
	 *            int
	 * 
	 * @return long
	 */
	public static long getDateTime(final int day, final int month, final int year) {

		final Calendar gigCal = Calendar.getInstance();
		gigCal.set(year, month - 1, day);
		return gigCal.getTimeInMillis();

	}

	/**
	 * Method getDateTimeFormat.
	 * 
	 * @param date
	 *            Date
	 * 
	 * @return String
	 */
	public static String getDateTimeFormat(final Date date) {
		final SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		return format.format(date);
	}

	/**
	 * Method getDateTimeFormatYear.
	 * 
	 * @param date
	 *            Date
	 * 
	 * @return String
	 */
	public static String getDateTimeFormatYear(final Date date) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(date);
	}

	/**
	 * Method getDateTimeFormatYearMonth.
	 * 
	 * @param date
	 *            Date
	 * 
	 * @return String
	 */
	public static String getDateTimeFormatYearMonth(final Date date) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(date);
	}

	public static Date getTimeFormat(final String toParse, final String field) throws EntityParseException {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			return format.parse(toParse);

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			throw new EntityParseException("Field: " + field + ", value: " + toParse);
		}

	}

	/**
	 * Method getTimeFormat.
	 * 
	 * @param date
	 *            Timestamp
	 * 
	 * @return String
	 */
	public static String getTimeFormat(final Timestamp date) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * Method getTimestampFormat.
	 * 
	 * @param toParse
	 *            String
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTimestampFormat(final String toParse) {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return new Timestamp(format.parse(toParse).getTime());

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			return null;
		}

	}

	/**
	 * Method getTimestampFormatWithTime.
	 * 
	 * @param toParse
	 *            String
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTimestampFormatWithTime(final String toParse) {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return new Timestamp(format.parse(toParse).getTime());

		} catch (final ParseException p) {
			LOGGER.info(p.getMessage());
			return null;
		}

	}

	public static boolean isDate(final String toParse) throws EntityParseException {
		try {

			final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			format.parse(toParse);
			return true;

		} catch (final ParseException p) {
			return false;
		}

	}

	public static String parseDate(final Date date) {
		return DATE_DEFAULT.print(new DateTime(date));
	}

	public static Date parseDate(final String date) {
		return DATE_DEFAULT.parseDateTime(date).toDate();
	}

	public static String parseYear(final Date date) {
		return DATE_YEAR.print(new DateTime(date));
	}

	/**
	 * Method returnValidSQLDate.
	 * 
	 * @param date
	 *            java.util.Date
	 * 
	 * @return String
	 */
	public static String returnValidSQLDate(final java.util.Date date) {
		String validDate = null;

		final Calendar gigCal = Calendar.getInstance();
		gigCal.setTime(date);
		final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

		validDate = dateFormat.format(date);

		return validDate;

	}

	/**
	 * Method returnValidSQLDate.
	 * 
	 * @param date
	 *            Timestamp
	 * 
	 * @return String
	 */
	public static String returnValidSQLDate(final Timestamp date) {
		String validDate = null;

		final Calendar gigCal = Calendar.getInstance();
		gigCal.setTime(date);
		final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

		validDate = dateFormat.format(date);

		return validDate;

	}

	public static Date setDateByString(final String toParse) {
		if (StringUtils.isEmpty(toParse)) {
			return null;
		}

		return DATE_DEFAULT.parseDateTime(toParse).toDate();
	}

	public static SimpleDateFormat simpleDateFormatDate(final String format) {
		return new SimpleDateFormat(format);
	}

	public static SimpleDateFormat simpleDateFormatDefaultDate() {
		return new SimpleDateFormat(DATE_FORMAT);
	}

	public static Date simplifyDate(final Date toParse) throws EntityParseException {
		try {

			final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(format.format(toParse));

		} catch (final ParseException p) {
			// logger.info(p.getMessage());
			throw new EntityParseException("Invalid date" + " " + toParse);
		}

	}

	/**
	 * Method validateDateFormat.
	 * 
	 * @param date
	 *            String
	 * 
	 * @return boolean
	 */
	public static boolean validateDateFormat(final String date) {
		if (date.length() > 0) {
			if (date.matches("[1-2]{1}[0-9]{3}-[0-9]{2}-[0-9]{2}")) {
				return true;
			}
		}

		return false;
	}

}
