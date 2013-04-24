package com.concurrentperformance.precision;

import java.math.BigDecimal;

import org.junit.Test;

public class PrecisionTest {

	
	@Test
	public void precision() {
		
		
		float f = 0.3f;
		BigDecimal bdf = new BigDecimal(f);
		System.out.println("from float  = " + bdf);
		
		float d = 0.3f;
		BigDecimal bdd = new BigDecimal(d);
		System.out.println("from double = " + bdd);

		String s = "0.3";
		BigDecimal bds = new BigDecimal(s);
		System.out.println("from String = " + bds);
		
		BigDecimal bdsd = new BigDecimal(bds.doubleValue());
		System.out.println("from doubleValue = " + bdsd);

	}
}
