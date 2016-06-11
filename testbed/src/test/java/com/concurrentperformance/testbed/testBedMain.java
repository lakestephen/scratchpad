package com.concurrentperformance.testbed;

import java.util.concurrent.atomic.AtomicInteger;


public class testBedMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AtomicInteger ai = new AtomicInteger(Integer.MAX_VALUE);
		ai.incrementAndGet();

		
	}

	
}
