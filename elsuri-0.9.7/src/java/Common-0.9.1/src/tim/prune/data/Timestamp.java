package tim.prune.data;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to hold the timestamp of a track point
 * and provide conversion functions
 */
public class Timestamp
{
	private boolean _valid = false;
	private long _milliseconds = 0L;
	private String _text = null;
	private String _timeText = null;

	private static final DateFormat DEFAULT_DATE_FORMAT = DateFormat.getDateTimeInstance();
	private static final DateFormat DEFAULT_TIME_FORMAT = DateFormat.getTimeInstance();
	private static final DateFormat ISO_8601_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static final DateFormat ISO_8601_FORMAT_NOZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static DateFormat[] ALL_DATE_FORMATS = null;
	private static Calendar CALENDAR = null;
	private static final Pattern ISO8601_FRACTIONAL_PATTERN
		= Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})(?:[\\.,](\\d{1,3}))?(Z|[\\+-]\\d{2}(?::?\\d{2})?)?");
	    //                    year     month     day T  hour    minute    sec             millisec   Z or +/-  hours  :   minutes
	private static final Pattern GENERAL_TIMESTAMP_PATTERN
		= Pattern.compile("(\\d{4})\\D(\\d{2})\\D(\\d{2})\\D(\\d{2})\\D(\\d{2})\\D(\\d{2})");
	private static long SECS_SINCE_1970 = 0L;
	private static long SECS_SINCE_GARTRIP = 0L;
	private static long MSECS_SINCE_1970 = 0L;
	private static long MSECS_SINCE_1990 = 0L;
	private static long TWENTY_YEARS_IN_SECS = 0L;
	private static final long GARTRIP_OFFSET = 631065600L;

	/** Specifies original timestamp format */
	public static final int FORMAT_ORIGINAL = 0;
	/** Specifies locale-dependent timestamp format */
	public static final int FORMAT_LOCALE = 1;
	/** Specifies ISO 8601 timestamp format */
	public static final int FORMAT_ISO_8601 = 2;

	/** Identifier for the parsing strategy to use */
	private enum ParseType
	{
		NONE,
		ISO8601_FRACTIONAL,
		LONG,
		FIXED_FORMAT0,
		FIXED_FORMAT1,
		FIXED_FORMAT2,
		FIXED_FORMAT3,
		FIXED_FORMAT4,
		FIXED_FORMAT5,
		FIXED_FORMAT6,
		GENERAL_STRING
	}

	/** Array of parse types to loop through (first one is changed to last successful type) */
	private static ParseType[] ALL_PARSE_TYPES = {ParseType.NONE, ParseType.ISO8601_FRACTIONAL, ParseType.LONG,
		ParseType.FIXED_FORMAT0, ParseType.FIXED_FORMAT1, ParseType.FIXED_FORMAT2, ParseType.FIXED_FORMAT3,
		ParseType.FIXED_FORMAT4, ParseType.FIXED_FORMAT5, ParseType.FIXED_FORMAT6, ParseType.GENERAL_STRING};

	// Static block to initialise offsets
	static
	{
		CALENDAR = Calendar.getInstance();
		TimeZone gmtZone = TimeZone.getTimeZone("GMT");
		CALENDAR.setTimeZone(gmtZone);
		MSECS_SINCE_1970 = CALENDAR.getTimeInMillis();
		SECS_SINCE_1970 = MSECS_SINCE_1970 / 1000L;
		SECS_SINCE_GARTRIP = SECS_SINCE_1970 - GARTRIP_OFFSET;
		CALENDAR.add(Calendar.YEAR, -20);
		MSECS_SINCE_1990 = CALENDAR.getTimeInMillis();
		TWENTY_YEARS_IN_SECS = (MSECS_SINCE_1970 - MSECS_SINCE_1990) / 1000L;
		// Set timezone for output
		ISO_8601_FORMAT.setTimeZone(gmtZone);
		DEFAULT_DATE_FORMAT.setTimeZone(gmtZone);
		// Date formats
		ALL_DATE_FORMATS = new DateFormat[] {
			DEFAULT_DATE_FORMAT,
			new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy"),
			new SimpleDateFormat("HH:mm:ss dd MMM yyyy"),
			new SimpleDateFormat("dd MMM yyyy HH:mm:ss"),
			new SimpleDateFormat("yyyy MMM dd HH:mm:ss"),
			ISO_8601_FORMAT, ISO_8601_FORMAT_NOZ
		};
	}


	/**
	 * Constructor
	 * @param inString String containing timestamp
	 */
	public Timestamp(String inString)
	{
		_valid = false;
		if (inString != null && !inString.equals(""))
		{
			// Try each of the parse types in turn
			for (ParseType type : ALL_PARSE_TYPES)
			{
				if (parseString(inString, type))
				{
					ALL_PARSE_TYPES[0] = type;
					_valid = true;
					return;
				}
			}
		}
	}

	/**
	 * Try to parse the given string in the specified way
	 * @param inString String to parse
	 * @param inType parse type to use
	 * @return true if successful
	 */
	private boolean parseString(String inString, ParseType inType)
	{
		if (inString == null || inString.equals("")) {
			return false;
		}
		switch (inType)
		{
			case NONE: return false;
			case LONG:
				// Try to parse into a long
				try
				{
					long rawValue = Long.parseLong(inString.trim());
					_milliseconds = getMilliseconds(rawValue);
					return true;
				}
				catch (NumberFormatException nfe)
				{}
				break;

			case ISO8601_FRACTIONAL:
				final Matcher fmatcher = ISO8601_FRACTIONAL_PATTERN.matcher(inString);
				if (fmatcher.matches())
				{
					try {
						_milliseconds = getMilliseconds(Integer.parseInt(fmatcher.group(1)), // year
							Integer.parseInt(fmatcher.group(2)), // month
							Integer.parseInt(fmatcher.group(3)), // day
							Integer.parseInt(fmatcher.group(4)), // hour
							Integer.parseInt(fmatcher.group(5)), // minute
							Integer.parseInt(fmatcher.group(6)), // second
							fmatcher.group(7),                   // fractional seconds
							fmatcher.group(8));                  // timezone, if any
						return true;
					}
					catch (NumberFormatException nfe) {}
				}
				break;

			case FIXED_FORMAT0: return parseString(inString, ALL_DATE_FORMATS[0]);
			case FIXED_FORMAT1: return parseString(inString, ALL_DATE_FORMATS[1]);
			case FIXED_FORMAT2: return parseString(inString, ALL_DATE_FORMATS[2]);
			case FIXED_FORMAT3: return parseString(inString, ALL_DATE_FORMATS[3]);
			case FIXED_FORMAT4: return parseString(inString, ALL_DATE_FORMATS[4]);
			case FIXED_FORMAT5: return parseString(inString, ALL_DATE_FORMATS[5]);
			case FIXED_FORMAT6: return parseString(inString, ALL_DATE_FORMATS[6]);

			case GENERAL_STRING:
				if (inString.length() == 19)
				{
					final Matcher matcher = GENERAL_TIMESTAMP_PATTERN.matcher(inString);
					if (matcher.matches())
					{
						try {
							_milliseconds = getMilliseconds(Integer.parseInt(matcher.group(1)),
								Integer.parseInt(matcher.group(2)),
								Integer.parseInt(matcher.group(3)),
								Integer.parseInt(matcher.group(4)),
								Integer.parseInt(matcher.group(5)),
								Integer.parseInt(matcher.group(6)),
								null, null); // no fractions of a second and no timezone
							return true;
						}
						catch (NumberFormatException nfe2) {} // parse shouldn't fail if matcher matched
					}
				}
				return false;
		}
		return false;
	}


	/**
	 * Try to parse the given string with the given date format
	 * @param inString String to parse
	 * @param inDateFormat Date format to use
	 * @return true if successful
	 */
	private boolean parseString(String inString, DateFormat inDateFormat)
	{
		inDateFormat.setLenient(false);
		ParsePosition pPos = new ParsePosition(0);
		Date date = inDateFormat.parse(inString, pPos);
		if (date != null && inString.length() == pPos.getIndex()) // require use of _all_ the string, not just the beginning
		{
			CALENDAR.setTime(date);
			_milliseconds = CALENDAR.getTimeInMillis();
			return true;
		}

		return false;
	}


	/**
	 * Constructor giving each field value individually
	 * @param inYear year
	 * @param inMonth month, beginning with 1
	 * @param inDay day of month, beginning with 1
	 * @param inHour hour of day, 0-24
	 * @param inMinute minute
	 * @param inSecond seconds
	 */
	public Timestamp(int inYear, int inMonth, int inDay, int inHour, int inMinute, int inSecond)
	{
		_milliseconds = getMilliseconds(inYear, inMonth, inDay, inHour, inMinute, inSecond, null, null);
		_valid = true;
	}


	/**
	 * Constructor giving millis
	 * @param inMillis milliseconds since 1970
	 */
	public Timestamp(long inMillis)
	{
		_milliseconds = inMillis;
		_valid = true;
	}


	/**
	 * Convert the given timestamp parameters into a number of milliseconds
	 * @param inYear year
	 * @param inMonth month, beginning with 1
	 * @param inDay day of month, beginning with 1
	 * @param inHour hour of day, 0-24
	 * @param inMinute minute
	 * @param inSecond seconds
	 * @param inFraction fractions of a second
	 * @param inTimezone timezone, if any
	 * @return number of milliseconds
	 */
	private static long getMilliseconds(int inYear, int inMonth, int inDay,
		int inHour, int inMinute, int inSecond, String inFraction, String inTimezone)
	{
		Calendar cal = Calendar.getInstance();
		// Timezone, if any
		if (inTimezone == null || inTimezone.equals("") || inTimezone.equals("Z")) {
			// No timezone, use zulu
			cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		}
		else {
			// Timezone specified, pass to calendar
			cal.setTimeZone(TimeZone.getTimeZone("GMT" + inTimezone));
		}
		cal.set(Calendar.YEAR, inYear);
		cal.set(Calendar.MONTH, inMonth - 1);
		cal.set(Calendar.DAY_OF_MONTH, inDay);
		cal.set(Calendar.HOUR_OF_DAY, inHour);
		cal.set(Calendar.MINUTE, inMinute);
		cal.set(Calendar.SECOND, inSecond);
		int millis = 0;
		if (inFraction != null)
		{
			try {
				int frac = Integer.parseInt(inFraction);
				final int fracLen = inFraction.length();
				switch (fracLen) {
					case 1: millis = frac * 100; break;
					case 2: millis = frac * 10;  break;
					case 3: millis = frac;       break;
				}
			}
			catch (NumberFormatException nfe) {} // ignore errors, millis stay at 0
		}
		cal.set(Calendar.MILLISECOND, millis);
		return cal.getTimeInMillis();
	}

	/**
	 * Convert the given long parameters into a number of millisseconds
	 * @param inRawValue long value representing seconds / milliseconds
	 * @return number of milliseconds
	 */
	private static long getMilliseconds(long inRawValue)
	{
		// check for each format possibility and pick nearest
		long diff1 = Math.abs(SECS_SINCE_1970 - inRawValue);
		long diff2 = Math.abs(MSECS_SINCE_1970 - inRawValue);
		long diff3 = Math.abs(MSECS_SINCE_1990 - inRawValue);
		long diff4 = Math.abs(SECS_SINCE_GARTRIP - inRawValue);

		// Start off with "seconds since 1970" format
		long smallestDiff = diff1;
		long millis = inRawValue * 1000;
		// Now check millis since 1970
		if (diff2 < smallestDiff)
		{
			// milliseconds since 1970
			millis = inRawValue;
			smallestDiff = diff2;
		}
		// Now millis since 1990
		if (diff3 < smallestDiff)
		{
			// milliseconds since 1990
			millis = inRawValue + TWENTY_YEARS_IN_SECS * 1000L;
			smallestDiff = diff3;
		}
		// Lastly, check gartrip offset
		if (diff4 < smallestDiff)
		{
			// seconds since gartrip offset
			millis = (inRawValue + GARTRIP_OFFSET) * 1000L;
		}
		return millis;
	}

	/**
	 * @return true if timestamp is valid
	 */
	public boolean isValid()
	{
		return _valid;
	}

	/**
	 * @param inOther other Timestamp
	 * @return true if this one is at least a second after the other
	 */
	public boolean isAfter(Timestamp inOther)
	{
		return getSecondsSince(inOther) > 0L;
	}

	/**
	 * Calculate the difference between two Timestamps in seconds
	 * @param inOther other, earlier Timestamp
	 * @return number of seconds since other timestamp
	 */
	public long getSecondsSince(Timestamp inOther)
	{
		return (_milliseconds - inOther._milliseconds) / 1000L;
	}

	/**
	 * Calculate the difference between two Timestamps in milliseconds
	 * @param inOther other, earlier Timestamp
	 * @return number of millisseconds since other timestamp
	 */
	public long getMillisecondsSince(Timestamp inOther)
	{
		return _milliseconds - inOther._milliseconds;
	}

	/**
	 * @param inOther other timestamp to compare
	 * @return true if they're equal to the nearest second
	 */
	public boolean isEqual(Timestamp inOther)
	{
		return getSecondsSince(inOther) == 0L;
	}

	/**
	 * @param inOther other Timestamp
	 * @return true if this one is before the other
	 */
	public boolean isBefore(Timestamp inOther)
	{
		return getSecondsSince(inOther) < 0L;
	}

	/**
	 * Add the given number of seconds offset
	 * @param inOffset number of seconds to add/subtract
	 */
	public void addOffset(long inOffset)
	{
		_milliseconds += (inOffset * 1000L);
		_text = null;
	}

	/**
	 * Add the given TimeDifference to this Timestamp
	 * @param inOffset TimeDifference to add
	 * @return new Timestamp object
	 */
	public Timestamp createPlusOffset(TimeDifference inOffset)
	{
		return createPlusOffset(inOffset.getTotalSeconds());
	}

	/**
	 * Add the given number of seconds to this Timestamp
	 * @param inSeconds number of seconds to add
	 * @return new Timestamp object
	 */
	public Timestamp createPlusOffset(long inSeconds)
	{
		return new Timestamp(_milliseconds + (inSeconds * 1000L));
	}


	/**
	 * Subtract the given TimeDifference from this Timestamp
	 * @param inOffset TimeDifference to subtract
	 * @return new Timestamp object
	 */
	public Timestamp createMinusOffset(TimeDifference inOffset)
	{
		return new Timestamp(_milliseconds - (inOffset.getTotalSeconds() * 1000L));
	}


	/**
	 * @return Description of timestamp in locale-specific format
	 */
	public String getText()
	{
		return getText(FORMAT_LOCALE);
	}

	/**
	 * @param inFormat format of timestamp
	 * @return Description of timestamp in required format
	 */
	public String getText(int inFormat)
	{
		if (!_valid) {return "";}
		if (inFormat == FORMAT_ISO_8601) {
			return format(ISO_8601_FORMAT);
		}
		if (_text == null) {
			_text = format(DEFAULT_DATE_FORMAT);
		}
		return _text;
	}

	/**
	 * @return Description of time part of timestamp in locale-specific format
	 */
	public String getTimeText()
	{
		if (_timeText == null)
		{
			if (_valid) {
				_timeText = format(DEFAULT_TIME_FORMAT);
			}
			else _timeText = "";
		}
		return _timeText;
	}

	/**
	 * Utility method for formatting dates / times
	 * @param inFormat formatter object
	 * @return formatted String
	 */
	private String format(DateFormat inFormat)
	{
		CALENDAR.setTimeZone(TimeZone.getTimeZone("GMT"));
		CALENDAR.setTimeInMillis(_milliseconds);
		return inFormat.format(CALENDAR.getTime());
	}

	/**
	 * @return a Calendar object representing this timestamp
	 */
	public Calendar getCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTimeInMillis(_milliseconds);
		return cal;
	}
}
