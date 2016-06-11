package com.concurrentperformance.heartbeat;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO
 * User: Stephen
 */
public class HeartbeatTimerTest {

	@Test
	public void canConstructTimerWithSetTime() {
		TimeStub time = new TimeStub();
		HeartbeatTimer heartbeatTimer =
				new HeartbeatTimer(time, TimeUnit.SECONDS.toMillis(1));
		assertEquals(1000, heartbeatTimer.getHeartbeatIntervalMs());
	}

	@Test
	public void timerWithZeroJitterToleranceWillTimeOutAfterSetInterval() {
		TimeStub timeStub = new TimeStub();
		HeartbeatTimer heartbeatTimer =
				new HeartbeatTimer(timeStub, 1000);

		timeStub.setTimeInMs(0);
		heartbeatTimer.beat();
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(999);
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(1000);
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(1001);
		assertTrue(heartbeatTimer.isOverdue());

	}

	@Test
	public void timerWithNonZeroJitterToleranceWillTimeOutAfterSetInterval() {
		TimeStub timeStub = new TimeStub();
		HeartbeatTimer heartbeatTimer = new HeartbeatTimer(timeStub, 1000, 200);

		timeStub.setTimeInMs(0);
		heartbeatTimer.beat();
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(1199);
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(1200);
		assertFalse(heartbeatTimer.isOverdue());

		timeStub.setTimeInMs(1201);
		assertTrue(heartbeatTimer.isOverdue());
	}


	@Test
	public void secondHeartbeatOverdue() {
		TimeStub timeStub = new TimeStub();
		HeartbeatTimer heartbeatTimer = new HeartbeatTimer(timeStub, 1000);

		timeStub.setTimeInMs(0);
		heartbeatTimer.beat();
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(2999);
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(3000);
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(3001);
		assertTrue(heartbeatTimer.isOverdue(3));
	}

	public void secondHeartbeatOverdueWithJitterTolerance() {
		TimeStub timeStub = new TimeStub();
		HeartbeatTimer heartbeatTimer = new HeartbeatTimer(timeStub, 1000, 200);

		timeStub.setTimeInMs(0);
		heartbeatTimer.beat();
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(3199);
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(3200);
		assertFalse(heartbeatTimer.isOverdue(3));

		timeStub.setTimeInMs(3201);
		assertTrue(heartbeatTimer.isOverdue(3));
	}

}
