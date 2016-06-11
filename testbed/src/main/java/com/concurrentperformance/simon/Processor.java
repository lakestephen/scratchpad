package com.concurrentperformance.simon;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO comments???
 * User: Stephen
 */
public class Processor {

	private final Log log = LogFactory.getLog(this.getClass());

	private final Stripper stripper = new StripperImpl();
	private final Cache cache;
	private final Executor executor = Executors.newFixedThreadPool(3);
	private final AtomicInteger publishCount = new AtomicInteger(0);

	public Processor(SlowService slowService) {
		cache = new CacheImpl(new SlowServiceManagerImpl(slowService));
	}

	public void process(String originalUrl) {
		String canonicalUrl = stripper.strip(originalUrl);
		ListenableFuture<Result> futureResult = cache.get(canonicalUrl);
		Futures.addCallback(futureResult, new Callback(originalUrl), executor);
	}

	private void publish(String originalUrl, Result result) {
		log.info(originalUrl + " Published. (Total published " + publishCount.incrementAndGet() + ")");
	}

	class Callback implements FutureCallback<Result> {

		private final String originalUrl;
		public Callback(String originalUrl) {
			this.originalUrl = originalUrl;
		}

		@Override
		public void onSuccess(Result result) {
			publish(originalUrl, result);
		}

		@Override
		public void onFailure(Throwable t) {

		}
	}
}
