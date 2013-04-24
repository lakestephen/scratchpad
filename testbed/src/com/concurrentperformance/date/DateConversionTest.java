package com.concurrentperformance.date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

public class DateConversionTest {

	
	@Test
	public void testClientDateConversion() {
		
		long millis = 1320019200000l; //original date
		//long millis = 1319846400000l;
		java.sql.Date originalDate = new java.sql.Date(millis);

		Calendar startOfMonthIndexDate = newGmtCalendar();
        startOfMonthIndexDate.setTimeInMillis(originalDate.getTime());
        startOfMonthIndexDate.set(Calendar.DAY_OF_MONTH, 1);
		
        long startOfMonthMillis = startOfMonthIndexDate.getTime().getTime();
        
		java.sql.Date correctedDate = new java.sql.Date(startOfMonthMillis);
        
        
		int dateVal = dateToYYYYMMDD(correctedDate); 
		
		System.out.println("originalDate = " + originalDate);
		System.out.println("startOfMonthIndexDate = " + startOfMonthIndexDate);
		System.out.println("dateVal = " + dateVal);
	}
	
	
    public static int dateToYYYYMMDD(java.util.Date date) {

        // to speed up this method, see code in previous versions of this class
        Calendar calendar = newGmtCalendar();
        calendar.setTime(date);
        return ((calendar.get(Calendar.YEAR) * 10000) +
                ((calendar.get(Calendar.MONTH) + 1) * 100) +
                calendar.get(Calendar.DAY_OF_MONTH));
    }
    
    public static Calendar newGmtCalendar() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    }	
}
