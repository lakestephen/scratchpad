package com.concurrentperformance.multiprocessor;

import org.junit.Test;

public class TestMultiProcessor {

	
	@Test
	public void testMultiProcessor() throws InterruptedException {
		long milliseconds = 5000;
		new SingleAtomicLongMultiThreadCounter().start(1, milliseconds);
		new SingleAtomicLongMultiThreadCounter().start(10, milliseconds);
	}
	
}
