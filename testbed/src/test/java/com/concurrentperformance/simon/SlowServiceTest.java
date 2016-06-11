package com.concurrentperformance.simon;

import org.junit.Test;

import java.util.Random;

/**
 * TODO comments???
 * User: Stephen
 */
public class SlowServiceTest {
	
	@Test
	public void runTest() throws InterruptedException {

		SlowService service = new StubSlowService();
		Processor p = new Processor(service);

		Random r = new Random();

		for (int i=0;i<1000;i++) {
			int urlId = Math.abs(r.nextInt() % 1000);
			int extensionId = Math.abs(r.nextInt() % 3);
			p.process("" + urlId + "&" + extensionId);
			int delay = Math.abs(r.nextInt() % 100);
			Thread.sleep(delay);
		}

		Thread.sleep(1000000);

	}
}
