package com.concurrentperformance.heartbeat;

/**
 * TODO
 * User: Stephen
 */
public class TimeStub implements Time {

	private long timeInMs = 0;

	public long getTimeInMs() {
		return timeInMs;
	}

	public void setTimeInMs(long timeInMs) {
		this.timeInMs = timeInMs;
	}
}
