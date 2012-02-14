package com.lake.dateparse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateParseTest {

	
	@Test
	public void dateParseIsAccurate() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
		Date d = simpleDateFormat.parse("39/4/2010");
		System.out.println(d);
	}
}
