package com.concurrentperformance.classloader;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class ClassloaderTest {

	@Test
	public void classloaderTest() {
		ClassLoader cl1 = this.getClass().getClassLoader();
		String s = new String("TEST");
		ClassLoader cl2 = s.getClass().getClassLoader();
		Class<? extends String> c = s.getClass();
		
		System.out.print("");
				
	}
	
}
