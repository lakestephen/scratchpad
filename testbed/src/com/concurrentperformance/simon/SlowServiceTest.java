package com.concurrentperformance.simon;

import org.junit.Test;

/**
 * TODO comments???
 * User: Stephen
 */
public class SlowServiceTest {
	
	@Test
	public void runTest() throws InterruptedException {

		SlowService service = new StubSlowService();
		Processor p = new Processor(service);

		for(int j=0;j<10;j++) {
			for(int i=0;i<100;i++) {
				p.process("" + i + "&" + j);
				Thread.sleep(10);
			}

		}

		Thread.sleep(1000000);

	}
}
