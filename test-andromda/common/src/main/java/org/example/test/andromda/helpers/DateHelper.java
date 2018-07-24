package org.example.test.andromda.helpers;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateHelper {

	public static Calendar toCalendar(Timestamp timestamp) {
		Calendar c;
		
		c = Calendar.getInstance();
		c.setTimeInMillis(timestamp.getTime());
		
		return c;
	}
}
