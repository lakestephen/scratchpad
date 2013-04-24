package com.concurrentperformance.precision;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TestInfinityFloat {

	@Test
	public void infinateFloatTest() { 
		double discountRate = Float.NEGATIVE_INFINITY;
		
    	if (discountRate < -1000.0 || discountRate > 1000.0) {
    		discountRate = 0.0;
    	}
		System.out.print(discountRate);
	}
}
