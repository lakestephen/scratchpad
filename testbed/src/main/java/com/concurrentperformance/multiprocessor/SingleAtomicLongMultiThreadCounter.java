package com.concurrentperformance.multiprocessor;

import java.util.concurrent.atomic.AtomicLong;

public class SingleAtomicLongMultiThreadCounter {
	
	static AtomicLong count  = new AtomicLong(0);

	public SingleAtomicLongMultiThreadCounter() {
		super();
	}

	void start(int threadCount, long milliseconds) throws InterruptedException { 
		count.set(0);
		
		for (int i=0;i<threadCount;i++) {
			
			Thread t = createThread(i);
			t.start();
		}
		
		Thread.sleep(milliseconds);
		
		long tickPerMillisecond = count.get() / milliseconds;
		
		System.out.println("[" + getClass().getSimpleName() + "] with [" + threadCount + "] threads, " +
				"tick per millisecond [" + tickPerMillisecond + "]");
	}

	public Thread createThread(int i) {
		
		Thread t = new Thread () {

			@Override
			public void run() {
				
				while (true) { 
					count.incrementAndGet();
				}
			}			
		};
		
		return t;
	}
}