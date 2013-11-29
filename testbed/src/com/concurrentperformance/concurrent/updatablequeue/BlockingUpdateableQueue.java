package com.concurrentperformance.concurrent.updatablequeue;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO comments???
 * @author Stephen Lake
 */
public class BlockingUpdateableQueue<K,V> implements UpdateableQueue<K,V> {

	private final ConcurrentMap<K,Holder<K,V>> map = new ConcurrentHashMap<>();
	private final BlockingQueue<Holder<K,V>> queue = new LinkedBlockingDeque<>();

	@Override
	public void add(K key, V value) {
		checkNotNull(key);
		checkNotNull(value);

		while (true) {
			Holder<K, V> existingHolder = map.get(key);
			if (existingHolder != null) {
				synchronized (existingHolder) {
					// if not removed yet, then we are safe to exchange the value.
					if (!existingHolder.removed) {
						existingHolder.setValue(value);
						return;
					}
				}
			}
			// At this point we either do not have an existing holder,
			// or we have one that is in the process of being removed.
			// Either way we need to create a new holder.
			Holder<K, V> holder = new Holder<>(key, value);

			Holder<K, V> previousHolder = map.putIfAbsent(key, holder);
			if (previousHolder != null) {
				// If we get a previousHolder, then someone preempted us with adding the
				// same key, or the one being removed is still being removed,
				// so we throw away our new holder and go around the loop again.
			}
			else {
				// Succeeded in adding our new holder.
				queue.add(holder);
				return;
			}
		}
	}

	@Override
	public V remove() throws NoSuchElementException {
		Holder<K, V> holder = queue.remove();
		return handleRemovedItem(holder);
	}

	@Override
	public V take() throws InterruptedException {
		Holder<K, V> holder = queue.take();
		return handleRemovedItem(holder);
	}

	private V handleRemovedItem(Holder<K, V> holder) {
		// holder must be non null if exception is not thrown.
		synchronized (holder) {
			holder.removed = true;
			map.remove(holder.key);
			return holder.value;
		}
	}

	private class Holder<K, V> {
		private final K key;
		private V value;
		private boolean removed = false;

		public Holder(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
