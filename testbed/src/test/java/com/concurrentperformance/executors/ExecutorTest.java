package com.concurrentperformance.executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * User: Stephen
 */
public class ExecutorTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() throws InterruptedException {

//		ExecutorService executorService = createAndStartExecutor();
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Runnable r = new Runnable() {
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}
		};

		for(int i=0;i<100000;i++) {
			executorService.submit(r);
		}

		for(int i=0;i<20;i++) {
			Thread.sleep(500);
			log.info(executorService.toString());
		}
	}

	private ExecutorService createAndStartExecutor() {
		//TODO make these values configurable
		int corePoolSize = 10; //Never less that this number of threads
		int maximumPoolSize = 100000; //Never more than this number of threads
		long keepAliveTimeMs = TimeUnit.MILLISECONDS.convert(10L, TimeUnit.SECONDS); //Timeout before a thread is allowed to die.
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();

		ExecutorService executor =  new ThreadPoolExecutor(
				corePoolSize,
				maximumPoolSize,
				keepAliveTimeMs,
				TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(),
				threadFactory);
		return executor;
	}

}
