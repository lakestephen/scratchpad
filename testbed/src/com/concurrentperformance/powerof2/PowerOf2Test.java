package com.concurrentperformance.powerof2;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class PowerOf2Test {

	@Test
	public void powerOf2Test() {
	
		for (int testValue=-20;testValue<20;testValue++) {
			boolean result =  (testValue != 0 && (testValue & (testValue-1)) == 0);
			if (result) 
				System.out.println(testValue + "=" +  result);
			
		}
	}
	
}
