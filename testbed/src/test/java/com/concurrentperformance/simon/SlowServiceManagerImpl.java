package com.concurrentperformance.simon;

import com.google.common.util.concurrent.SettableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO comments???
 * User: Stephen
 */
public class SlowServiceManagerImpl implements SlowServiceManager {

	public static final int THREADS = 3;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final SlowService slowService;
	private final DelayQueue<Wrapper> queue = new DelayQueue<>();
	private final Executor executor = Executors.newFixedThreadPool(THREADS);

	public SlowServiceManagerImpl(SlowService slowService) {
		this.slowService = slowService;
		startExecutor();
	}

	private void startExecutor() {
		for(int i=0;i<THREADS;i++) {
			executor.execute(new QueuePoller());
		}
	}

	@Override
	public void manageRequest(String canonicalUrl, SettableFuture<Result> settableFuture) {
		Wrapper wrapper = new Wrapper(canonicalUrl, settableFuture);
		wrapper.setDelay(0, TimeUnit.SECONDS);
//		log.info("Queuing " + wrapper);
		queue.add(wrapper);
	}

	private static class Wrapper implements Delayed {
		private final String url;
		private final SettableFuture<Result> settableFuture;

		private long nextTimeoutMs;

		private Wrapper(String url, SettableFuture<Result> settableFuture) {
			this.url = url;
			this.settableFuture = settableFuture;
		}

		private String getUrl() {
			return url;
		}

		private SettableFuture<Result> getSettableFuture() {
			return settableFuture;
		}

		public void setDelay(int count, TimeUnit unit) {
			nextTimeoutMs = System.currentTimeMillis() + unit.toMillis(count);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return nextTimeoutMs - System.currentTimeMillis();
		}

		@Override
		public int compareTo(Delayed o) {
			return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public String toString() {
			return "W{" + url + '}';
		}
	}

	private class QueuePoller implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Wrapper wrapper = queue.take();
					Result result = slowService.getResult(wrapper.getUrl());
					if (result != null) {
						log.info("*" + wrapper + " Success" );
						wrapper.getSettableFuture().set(result);
					}
					else {
						wrapper.setDelay(10, TimeUnit.SECONDS);
						log.info(wrapper + " Fail - Re-Queuing" );
						queue.add(wrapper);
					}
				} catch (Exception e) {
					log.error("TODO", e);
				}
			}
		}
	}
}
