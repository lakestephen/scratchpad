package com.concurrentperformance.concurrent.updatablequeue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO comments???
 * User: Stephen
 */
public class BlockingUpdateableQueueTest {

	private final Log log = LogFactory.getLog(this.getClass());

	public static final String VALUE = "VALUE";
	public static final String VALUE_NEW = "VALUE_NEW";
	public static final String KEY = "Key";

	@Test
	public void canAddAndRemoveItem() {
		UpdateableQueue<String, String> queue = new BlockingUpdateableQueue();
		queue.add(KEY, VALUE);
		String value = queue.remove();
		assertEquals(VALUE, value);
	}
	
	@Test
	public void canUpdateItemStillInQueue() {
		UpdateableQueue<String, String> queue = new BlockingUpdateableQueue();
		queue.add(KEY, VALUE);
		queue.add(KEY, VALUE_NEW);
		String value = queue.remove();
		assertEquals(VALUE_NEW, value);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removingWhenNoElementThrows() {
		UpdateableQueue<String, String> queue = new BlockingUpdateableQueue();
		queue.remove();
	}

	@Test
	public void takingWillWaitUntilAvailableElement() throws InterruptedException {
		final UpdateableQueue<String, String> queue = new BlockingUpdateableQueue();
		final AtomicBoolean taken = new AtomicBoolean(false);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String take = queue.take();
					taken.set(true);

				} catch (InterruptedException e) {
					log.error("TODO", e);
				}
			}
		}).start();

		assertFalse(taken.get());
		Thread.sleep(500);
		queue.add(KEY, VALUE);
		Thread.sleep(50);
		assertTrue(taken.get());
	}

}
