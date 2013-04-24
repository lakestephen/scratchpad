package com.concurrentperformance.concurrent;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * TODO
 * User: Stephen
 */

public class FuturetaskTest {


	@Test
	public void FutureTaskTest() {
		Callable<String> c = new Callable<String>() {

			public String call() throws Exception {
				return "Stephen";
			}
		};



		FutureTask<String> task = new FutureTask<String>(c);

		Executor executor = Executors.newCachedThreadPool();

		executor.execute(task);

		try {
			String s = task.get();

			System.out.print(s);
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (ExecutionException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}


	}


}
