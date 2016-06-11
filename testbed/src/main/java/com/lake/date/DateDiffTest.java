package com.lake.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class DateDiffTest {

	
	@Test
	public void testDateDiff() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));

		String maturityDate = "23/06/2018"; 
		Date maturity = df.parse(maturityDate);
		
		int months = getMonthsDiff(maturity);
		
		//Schatz contracts are 1.75 - 2.25 years, or 8 - 15 months
		final int SHORTEST_SCHATZ_MONTHS = 8;
		final int LONGEST_SCHATZ_MONTHS = 15;
		//Bobl contracts are 4.5 - 5.5 years, or 8 - 15 months
		final int SHORTEST_BOBL_MONTHS = 54;
		final int LONGEST_BOBL_MONTHS = 65;
		//Bund contracts are 8.5 - 10.5 years, or 8 - 15 months
		final int SHORTEST_BUND_MONTHS = 102;
		final int LONGEST_BUND_MONTHS = 126;

		if (months < SHORTEST_SCHATZ_MONTHS) {  
			
		} else if (months >= SHORTEST_SCHATZ_MONTHS && 
				   months <= LONGEST_SCHATZ_MONTHS) { 
			
		} else if (months > LONGEST_SCHATZ_MONTHS && 
				   months < SHORTEST_BOBL_MONTHS) { 

		} else if (months >= SHORTEST_BOBL_MONTHS && 
				   months <= LONGEST_BOBL_MONTHS) {
			
		} else if (months > LONGEST_BOBL_MONTHS && 
				   months < SHORTEST_BUND_MONTHS) {

		} else if (months >= SHORTEST_BUND_MONTHS && 
				   months <= LONGEST_BUND_MONTHS) {

		} else if (months > LONGEST_BUND_MONTHS) {
		
		}

		System.out.println(months);
		
	}

	private int getMonthsDiff(Date maturity) {
		Calendar today  = Calendar.getInstance();
		Calendar maturityCal  = Calendar.getInstance();
		maturityCal.setTime(maturity);
		
		int months = 0;
		while (maturityCal.compareTo(today) > 0) {
			maturityCal.add(Calendar.MONTH, -1);
			months++;
		}
		return months;
	}
	
}
