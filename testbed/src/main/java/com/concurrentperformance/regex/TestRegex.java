package com.concurrentperformance.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;



public class TestRegex {
	

	@Test
	public void split() {

		String forSplitting = "String 1 , String 2,String 3 ,String 4, String 5";

		String s = "\\s*,\\s*";
		
		String[] arr = forSplitting.split(s);
		
		
		
	}
	
	@Test
	public void matchesNovatedTrade() {

		final String comment = "Novated from K2461183UK, Customer BARC_LDN";
		
		// Find the word novated
		Pattern novatedPattern = Pattern.compile("novated", Pattern.CASE_INSENSITIVE);
		Matcher novatedMatcher = novatedPattern.matcher(comment);
		boolean foundNovated = novatedMatcher.find();
		System.out.println("found novated = " + foundNovated);
		
		
		// find the original number from the string 
		
		Pattern numberPattern = Pattern.compile("[\\d|L|N|T|A|V]\\d{4,}(F|UK)");
		Matcher numberMatcher = numberPattern.matcher(comment);
		boolean foundNumber = numberMatcher.find();
		
		System.out.println("foundNumber = " + foundNumber);
		if (foundNumber) {
			String matchedNumber= numberMatcher.group();
			System.out.println(matchedNumber);
		}
		
		boolean foundNumberAgain = numberMatcher.find();

		System.out.println("foundNumberAgain = " + foundNumberAgain);

	}	
}
