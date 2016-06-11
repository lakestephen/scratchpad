package com.concurrentperformance.simon;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Stephen
 */
public class CacheImpl implements Cache {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final SlowServiceManager slowServiceManager;
	private final ConcurrentHashMap<String, ListenableFuture<Result>> cache = new ConcurrentHashMap<>();

	public CacheImpl(SlowServiceManager slowServiceManager) {
		this.slowServiceManager = slowServiceManager;
	}


	@Override
	public ListenableFuture<Result> get(String canonicalURL) {
		ListenableFuture<Result> future = cache.get(canonicalURL);

		if (future != null) {
			log.info("Cache hit for " + canonicalURL);
			return future;
		}
		else {
//			log.info("Cache miss for " + canonicalURL);
			return handleCacheMiss(canonicalURL);
		}
	}

	private ListenableFuture<Result> handleCacheMiss(String canonicalURL) {
		ListenableFuture<Result> future;
		SettableFuture<Result> newResult = SettableFuture.create();
		future = cache.putIfAbsent(canonicalURL, newResult);
		if (future == null) {
			future = newResult;
			slowServiceManager.manageRequest(canonicalURL, newResult);
		}

		return future;
	}
}
