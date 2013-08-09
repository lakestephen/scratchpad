package com.concurrentperformance.simon;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * TODO comments???
 * User: Stephen
 */
public interface Cache {

	ListenableFuture<Result> get(String canonicalURL);
}
