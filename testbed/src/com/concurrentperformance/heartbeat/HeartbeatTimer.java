package com.concurrentperformance.heartbeat;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO
 * User: Stephen
 */
public class HeartbeatTimer {

	private final Time time;
	private final long heartbeatIntervalMs;
	private final long jitterToleranceMs;
	private final long heartbeatIntervalIncludingJitterToleranceMs;
	private volatile long lastBeatMs;

	public HeartbeatTimer(Time time, long heartbeatIntervalMs) {
		this(time, heartbeatIntervalMs, 0);
	}

	public HeartbeatTimer(Time time, long heartbeatIntervalMs, long jitterToleranceMs) {
		this.time = checkNotNull(time, "time must not be null.");
		checkArgument(heartbeatIntervalMs > 0, "heartbeatIntervalMs must be greater than 0");
		this.heartbeatIntervalMs = heartbeatIntervalMs;
		checkArgument(jitterToleranceMs >= 0, "jitterToleranceMs must be greater than or equal to 0");
		this.jitterToleranceMs = jitterToleranceMs;
		this.heartbeatIntervalIncludingJitterToleranceMs = heartbeatIntervalMs + jitterToleranceMs;
	}

	public long getHeartbeatIntervalMs() {
		return heartbeatIntervalMs;
	}

	public void beat() {
		lastBeatMs = time.getTimeInMs();
	}

	public boolean isOverdue() {
		long timeSinceLastBeat = getTimeSinceLastBeat();
		return timeSinceLastBeat > heartbeatIntervalIncludingJitterToleranceMs;
	}

	public boolean isOverdue(int heartbeatMultiples) {
		long timeSinceLastBeat = getTimeSinceLastBeat();
		return timeSinceLastBeat > (heartbeatIntervalMs * heartbeatMultiples) + jitterToleranceMs;
	}

	public long getTimeSinceLastBeat() {
		long timeNowMs = time.getTimeInMs();
		return timeNowMs - lastBeatMs ;
	}
}
