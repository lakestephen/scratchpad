package com.lake.overload;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class OverloadTest {

	static void print (Number n) {
		System.out.println("Number: " + n);
	}

	static void print (Double d) {
		System.out.println("Double: " + d);
	}

	static void print (String s) {
		System.out.println("String : " + s);
	}
	
	@Test
	public void test() {
		String s = "1.25";
		Number n = new Double(s);
		print(n);
	}
}
