package com.lake.scala.java;

import org.junit.Test;

import com.lake.scala.Prime;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TestScalaFromJava {
	@Test
	public void test() {
		boolean result = Prime.isPrime(5);
		System.out.println(result);
	}
}
