package com.concurrentperformance.heartbeat;

/**
 * TODO
 * User: Stephen
 */
public class DefaultTime implements Time {

	public long getTimeInMs() {
		return System.currentTimeMillis();
	}
}
