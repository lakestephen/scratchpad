package com.lake.locale;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * SJL comment
 * 
 * @author Stephen Lake
 * 
 */
public class LocaleTest {

	@Test
	public void localeTest() {

		DateFormat df = DateFormat.getDateInstance();
		
		Locale locale = Locale.getDefault();
		System.out.println("Before setting, Locale is = " + locale + " Date: " + df.format(new Date()));

		locale = Locale.US;
		Locale.setDefault(locale);

		df = DateFormat.getDateInstance();
		locale = Locale.getDefault();
		System.out.println("After setting, Locale is = " + locale + "  Date: " + df.format(new Date()));
	}
}
