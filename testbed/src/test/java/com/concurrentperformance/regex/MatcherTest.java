package com.concurrentperformance.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class MatcherTest {

	
	@Test
	public void matcherTest()
	{
		String input = "asdig aklsadlfkjh dcd";
		String regex = "dig";

		Pattern p = Pattern.compile(regex);	
		Matcher m = p.matcher(input);
		while (m.find() == true) {
			System.out.print(m.start() + "\r\n");
		}
		
		System.out.print("_______________\r\n");
		
		String[] splits = input.split(regex);
		for (String split : splits) {
			System.out.print(split + "\r\n");
		}
		
	}
}
