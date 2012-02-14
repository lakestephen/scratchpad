package com.lake.dateparse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class DateParseTest {

	@Test
	public void testDateParse() {
		
		Integer settlementDateValue = 20110526;
		SimpleDateFormat marketViewDateFormat = new SimpleDateFormat("yyyyMMdd");
		Date settlementDate = null;
		try {
			settlementDate = marketViewDateFormat.parse(settlementDateValue.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long milliseconds = settlementDate.getTime();
		
		java.sql.Date javaDate = new java.sql.Date(milliseconds);
		
	//	Calendar.getInstance("GMT");
		
		System.out.println(settlementDate);
		System.out.println(javaDate);
	}
	
}
