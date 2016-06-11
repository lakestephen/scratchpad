package com.lake.doubleNaN;

import org.junit.Test;

public class TestDoubleNaN {
	@Test
	public void testbed() {
	
		Double d = Double.NaN;
		double dn = Double.NaN;
		double dm = Double.NEGATIVE_INFINITY;
		double dp = Double.POSITIVE_INFINITY;
		if (Double.isNaN(d)) {
			System.out.println("equal");
		}
		
	}
}
