package org.example.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.junit.Before;
import org.junit.Test;

public class TimeZoneHelperTest {
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Before
	public void beforeTest(){
//		System.setProperty("user.timezone", "Europe/London");
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
	}
	
	@Test
	public void testTimeZone01_TZAviables() {
		
		System.out.println("-------------------------------------------------");
		TimeZoneHelper.printTimeZones();
	}
	
	@Test
	public void testTimeZone02_Default() {
				
		System.out.println("-------------------------------------------------");
		System.out.println("Default TZ: " + TimeZone.getDefault().getID());
		
	}
	
	@Test
	public void testTimeZone03_SDF() {
				
		System.out.println("-------------------------------------------------");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		System.out.println("GMT+1:         " + simpleDateFormat.format(new Date()));
		
		simpleDateFormat.setTimeZone(TimeZone.getDefault());
		System.out.println("Default:       " + simpleDateFormat.format(new Date()));
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		System.out.println("Europe/Madrid: " + simpleDateFormat.format(new Date()));
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		System.out.println("CET:           " + simpleDateFormat.format(new Date()));
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Kiritimati"));
		System.out.println("Pacific/Kiritimati: " + simpleDateFormat.format(new Date()));
		
	}
	
	@Test
	public void testTimeZone04() {
				
		
		System.out.println("-------------------------------------------------");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, 15);
		System.out.println(simpleDateFormat.format(c.getTime()));
		
		c.setTime(new Date((new Date().getTime() + 900000) - (new Date().getTime())));
		System.out.println(simpleDateFormat.format(c.getTime()));
		
		System.out.println(String.format("%tH", c));
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2010, Calendar.MARCH, 1, 10, 0, 00);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2010, Calendar.MARCH, 1, 10, 0, 00);
		calendar2.add(Calendar.MINUTE, 15);
		
		
		
		System.out.println(DateFormatUtils.format(calendar2, "dd/MM/yyyy HH:mm:ss") + " - " + calendar2.getTimeInMillis());
		System.out.println(DateFormatUtils.format(calendar1, "dd/MM/yyyy HH:mm:ss") + " - " + calendar1.getTimeInMillis());
		
		Calendar resultado = Calendar.getInstance();
		resultado.setTimeInMillis(calendar2.getTimeInMillis() - calendar1.getTimeInMillis());
		
		System.out.println(DateFormatUtils.format(resultado, "dd/MM/yyyy HH:mm:ss"));
		System.out.println(DateFormatUtils.format(resultado, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("GMT")) + " - GMT");
		
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Zulu"));
		System.out.println(simpleDateFormat.format(resultado.getTime()));
		
	}
	

	@Test
	public void testTimeZone05_StringFormat() {

		System.out.println("-------------------------------------------------");
		
		System.out.println(String.format("- %1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS,%1$tL -", new Date()));

	}
	
	@Test
	public void testTimeZone06_JodaTime() {
		
		System.out.println("-------------------------------------------------");
		
		
		DateTime dt1 = new DateTime(2010, 3, 1, 10, 0, 0, 0);
		
		DateTime dt2 = new DateTime(2010, 3, 1, 10, 0, 0, 0);
		dt2 = dt2.plusMinutes(15);
		
		System.out.println(dt2.toString("dd/MM/yyyy HH:mm:ss"));
		System.out.println(dt1.toString("dd/MM/yyyy HH:mm:ss"));
		
		System.out.println(dt2.getMillis() - dt1.getMillis());
		System.out.println(Minutes.minutesBetween(dt1, dt2).getMinutes());
		
		
		
		DateTime dt3 = dt2.minus(dt1.getMillis());
		
		System.out.println(dt3.toString("dd/MM/yyyy HH:mm:ss"));
		
//		System.out.println(simpleDateFormat.format(new Date(new Date().getTime() + 900000)));
//		System.out.println(simpleDateFormat.format(new Date()));
//		
//		
//		//simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
//		Date diferencia = new Date((new Date().getTime() + 900000) - (new Date().getTime()));
//		
//		System.out.println(simpleDateFormat.format(diferencia));
//		
//		System.out.println(DateFormatUtils.format(diferencia, "dd/MM/yyyy HH:mm:ss"));
//		
//		System.out.println(DateFormatUtils.format(diferencia, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("GMT")));

		System.out.println("-------------------------------------------------");
		
		System.out.println(DurationFormatUtils.formatPeriod(dt1.getMillis(), dt2.getMillis(), "dd/MM/yyyy HH:mm:ss"));
		
	}
	
	@Test
	public void testTimeZone07_log4j() {
		System.out.println("-------------------------------------------------");
		
		Logger logger = Logger.getLogger(TimeZoneHelperTest.class);
		
		System.out.println("Time: " + new Date());
		logger.debug("Time: " + new Date());
		
		logger.debug("Time: " + Calendar.getInstance().getTime());
	
		
	}
	
	@Test
	public void testTimeZone08_DSTIndicator() {
		System.out.println("-------------------------------------------------");
		Calendar c = Calendar.getInstance();

		System.out.println(c);

		System.out.println(TimeZone.getDefault().inDaylightTime(c.getTime()));

		c.set(2013, 6, 1);

		System.out.println(c);

		Timestamp timestamp = new Timestamp(1372669173674L);

		c.setTime(new Date(timestamp.getTime()));

		System.out.println(c);

		c.set(2013, Calendar.OCTOBER, 27, 2, 30, 0);

		System.out.println(c);

		System.out.println(c.getTime().getTime());

		c.setTime(new Date(1382841000000L - 3600000L));

		System.out.println(c);
		System.out.println(TimeZoneHelper.isInDST(c.getTimeInMillis()));
	}
	
	@Test
	public void testTimeZone09_HoursOfDST_VI() throws Exception {
		System.out.println("-------------------------------------------------");
		TimeZone tz;
		
//		tz = TimeZone.getTimeZone("Europe/Paris");
//		tz = TimeZone.getTimeZone("Europe/Madrid");
		tz = TimeZone.getTimeZone("Europe/London");
//		tz = TimeZone.getTimeZone("Atlantic/Canary");
		
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm z");
		df.setTimeZone(tz);
		
		Calendar calendar = Calendar.getInstance(tz);
		
		calendar.setTime(df.parse("30/03/2014 00:00 CET"));
		
		for (int i = 0; i < 25*60; i++) {
			
			calendar.add(Calendar.MINUTE, 1);
			
			System.out.println("Periodo: " + DateFormatUtils.format(calendar, "dd/MM/yyyy HH:mm z"));
		}
	}
	
	@Test
	public void testPrintCalendar() {
		
		TimeZoneHelper.print(Calendar.getInstance());
	}

}
