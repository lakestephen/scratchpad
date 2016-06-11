package com.concurrentperformance.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class BigDecimalPresisionTest {

	@Test
	public void testPrecision() {
		//BigDecimal bd = new BigDecimal("1.4923999999999992");
		BigDecimal bd = new BigDecimal("500");
//		BigDecimal bd = new BigDecimal("500.0000000000008");
//		BigDecimal bd = new BigDecimal(  "1.4923000000000002");
		System.out.println("Value = " + bd.doubleValue() + ", Scale = " + bd.scale() + ", unscaled = " + bd.unscaledValue().longValue());

		bd = bd.setScale(10, RoundingMode.HALF_UP);
		System.out.println("Value = " + bd.doubleValue() + ", Scale = " + bd.scale() + ", unscaled = " + bd.unscaledValue().longValue());

		bd = bd.stripTrailingZeros();
		System.out.println("Value = " + bd.doubleValue() + ", Scale = " + bd.scale() + ", unscaled = " + bd.unscaledValue().longValue());

	}
	
}
