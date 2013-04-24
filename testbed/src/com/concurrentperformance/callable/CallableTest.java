package com.concurrentperformance.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class CallableTest {


	@Test
	public void callableTest () throws IllegalStateException {
		ExecutorService s = Executors.newSingleThreadExecutor();
		Future<Integer> fut = s.submit(new FailTask());
	
		try {
			try {
				Integer result = fut.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				Throwable t = e.getCause();
				if (t instanceof IllegalStateException)
				throw (IllegalStateException)t;
			}
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	
	public static class FailTask implements Callable<Integer> {
    @Override
    public Integer call() {
      throw new IllegalStateException();
    }
  }
}