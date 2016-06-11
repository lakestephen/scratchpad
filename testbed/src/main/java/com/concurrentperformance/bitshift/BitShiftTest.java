package com.concurrentperformance.bitshift;

import org.junit.Test;

/**
 * TODO comments.
 *
 * @author stephen
 */
public class BitShiftTest {

	@Test
	public void bitshift() {


		for (byte b = Byte.MIN_VALUE;b<=Byte.MAX_VALUE;b++) {
			int shifted = b & 0xFF;
			byte shiftedBack = (byte)(shifted & 0xFF);
			System.out.println(b + " = " + shifted + " = " + shiftedBack );
		}
	}
}
