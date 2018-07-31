package org.example.test;

import java.util.Calendar;
import java.util.TimeZone;

public final class TimeZoneHelper {
	
	public static void printTimeZones() {
		
		for (String tz : TimeZone.getAvailableIDs()) {
			System.out.println(tz + " => " + (TimeZone.getTimeZone(tz).getRawOffset()/(3600000)) + "h [" + (TimeZone.getTimeZone(tz).getDSTSavings()/(3600000))+ "]");
		}
		
	} 
	
	public static void print(Calendar c) {
		System.out.println("ERA: " + c.get(Calendar.ERA));
	    System.out.println("YEAR: " + c.get(Calendar.YEAR));
	    System.out.println("MONTH: " + c.get(Calendar.MONTH));
	    System.out.println("WEEK_OF_YEAR: " + c.get(Calendar.WEEK_OF_YEAR));
	    System.out.println("WEEK_OF_MONTH: " + c.get(Calendar.WEEK_OF_MONTH));
	    System.out.println("DATE: " + c.get(Calendar.DATE));
	    System.out.println("DAY_OF_MONTH: " + c.get(Calendar.DAY_OF_MONTH));
	    System.out.println("DAY_OF_WEEK: " + c.get(Calendar.DAY_OF_WEEK));
	    System.out.println("DAY_OF_WEEK_IN_MONTH: " + c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	    System.out.println("AM_PM: " + c.get(Calendar.AM_PM));
	    System.out.println("MILLISECOND: " + c.get(Calendar.MILLISECOND));
	    System.out.println("ZONE_OFFSET: "+ c.get(Calendar.ZONE_OFFSET));
	    System.out.println("DST_OFFSET: "+ c.get(Calendar.DST_OFFSET));
	}

	public static boolean isInDST(long timeInMillis) {
		return isInDST(timeInMillis, TimeZone.getDefault());
	}
	
	public static boolean isInDST(long timeInMillis, TimeZone timeZone) {
		return (timeZone.getOffset(timeInMillis) == (timeZone.getRawOffset() + timeZone.getDSTSavings()));
	}

}
