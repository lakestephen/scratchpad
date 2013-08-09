package com.concurrentperformance.simon;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO comments???
 * User: Stephen
 */
public class StubSlowService implements SlowService {

	private final AtomicInteger callCount = new AtomicInteger(0);

	@Override
	public Result getResult(String canonicalURL) {
		if (callCount.getAndIncrement() % 3 == 0) {
			return null;
		}
		return new Result(canonicalURL);
	}
}
