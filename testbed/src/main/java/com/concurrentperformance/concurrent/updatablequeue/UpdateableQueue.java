package com.concurrentperformance.concurrent.updatablequeue;

/**
 * TODO comments???
 * User: Stephen
 */
public interface UpdateableQueue<K, V> {

	void add(K key, V value);

	/**
	 * Retrieves and removes the head of this queue.
	 *
	 * @return the head of this queue
	 * @throws java.util.NoSuchElementException if this queue is empty
	 */
	V remove();

	/**
	 * Retrieves and removes the head of this queue, waiting if necessary
	 * until an element becomes available.
	 *
	 * @return the head of this queue
	 * @throws InterruptedException if interrupted while waiting
	 */
	V take() throws InterruptedException;

}
