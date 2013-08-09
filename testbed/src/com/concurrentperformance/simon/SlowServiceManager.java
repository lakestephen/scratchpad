package com.concurrentperformance.simon;

import com.google.common.util.concurrent.SettableFuture;

/**
 * User: Stephen
 */
public interface SlowServiceManager {

	void manageRequest(String canonicalUrl, SettableFuture<Result> result);

}
