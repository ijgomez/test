package org.example.test.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateHelper.class);

	public static boolean getDSTIndicator(long timeInMillis, TimeZone timeZone) {
		return (timeZone.getOffset(timeInMillis) == (timeZone.getRawOffset() + timeZone.getDSTSavings()));
	}
	
	public static void print(String value, Long timeInMillis) {

			LOGGER.debug(timeInMillis + " - " + value + " - " + DateFormatUtils.format(timeInMillis, "dd/MM/yyyy HH:mm:ss z Z"));	


	}
	
	public static Calendar stringToCalendar(String date, String format) {
		SimpleDateFormat formatter;
		
		try {
			formatter = new SimpleDateFormat(format);
			formatter.setTimeZone(TimeZone.getDefault());
			formatter.setLenient(true);

			Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(date));
			
			return c;
		} catch (Exception e) {
			return null;
		}       
    }
}
