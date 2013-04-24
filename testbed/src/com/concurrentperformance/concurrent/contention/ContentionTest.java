package com.concurrentperformance.concurrent.contention;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class ContentionTest {

	private static final int LOOP_AMOUNT = 500 * 1000 * 1000 ;

	@Test
	public void singleThread() {
		
		long start = System.currentTimeMillis();
		
		long var = 0;
		
		for (int i=0;i<LOOP_AMOUNT;i++) {
			var++;
		}

		long end = System.currentTimeMillis();
		
		System.out.println("Single Thread :          " + (end-start) + "ms " + var + "  ");
		
	}

	volatile long volVar = 0;

	@Test
	public void singleThreadWithVolatile() {
		
		long start = System.currentTimeMillis();
		
		
		for (int i=0;i<LOOP_AMOUNT;i++) {
			volVar++;
		}

		long end = System.currentTimeMillis();
		
		System.out.println("Single Thread Volatile:  " + (end-start) + "ms " + volVar + "  ");
		
	}
	

	@Test
	public void singleThreadWithLock() {
		Object LOCK = new Object();
		long start = System.currentTimeMillis();
		
		long var = 0;
		
		for (int i=0;i<LOOP_AMOUNT;i++) {
			synchronized (LOCK) {
				var++;
			}
		}

		long end = System.currentTimeMillis();
		
		System.out.println("Single Thread With Lock: " + (end-start) + "ms " + var + "  ");
		
	}
}
