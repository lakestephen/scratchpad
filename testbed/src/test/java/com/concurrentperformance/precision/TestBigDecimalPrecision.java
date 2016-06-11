package com.concurrentperformance.precision;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TestBigDecimalPrecision {

	@Test
	@Ignore
	public void precision() {
        float f = 0.03f; 
        BigDecimal bdf = new BigDecimal(f);
        System.out.println("from float  = " + bdf);
        
        double d = 0.03f;
        BigDecimal bdd = new BigDecimal(d);
        System.out.println("from double = " + bdd);

        String s = "0.03";
        BigDecimal bds = new BigDecimal(s);
        System.out.println("from String = " + bds);
        
        BigDecimal bdsd = new BigDecimal(bds.doubleValue());
        System.out.println("from doubleValue = " + bdsd);

	}

	public static final BigDecimal YIELD_FACTOR_FOR_GAL = new BigDecimal("10000.0");
	public static final BigDecimal PRICE_FACTOR_FOR_GAL = new BigDecimal("100");

	@Test
	public void divide() {
		
		BigDecimal numerator = new BigDecimal("203.1234567890");
		
		BigDecimal result = numerator.divide(YIELD_FACTOR_FOR_GAL, 10, BigDecimal.ROUND_HALF_EVEN);
		
		System.out.print(result);
		
	}
}
