package com.lake.regex;

import org.junit.Test;

public class RegexTest {

	
	@Test
	public void matchingTest() {
		
		String name = "MachineName!1";
		boolean matched = name.matches(".*[ ].*");
		
		System.out.print("Matched : " + matched);
		
	}
}
