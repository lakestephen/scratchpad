package com.lake.string;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class InternTest {

	private static final String STATIC_STR = "TEST";
	
	@Test 
	public void testINtern() {
		String STATIC_STR = "TEST";
		 
		String concat = testPrefix("INT");
		String concat2 = testPrefix("INT");
		
		
	}

	/**
	 * @param string
	 */
	private String  testPrefix(String pre) {
		String concat = pre + STATIC_STR;
		
		String intern = concat.intern();
		return concat;
	}
	
}
