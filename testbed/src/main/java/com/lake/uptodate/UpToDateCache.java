package com.lake.uptodate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Cache that will return 
 *
 * @author Stephen Lake
 *
 */
public class UpToDateCache<K, V> {

	private static final long TIMEOUT_LENGTH_MS = 2 * 60 * 1000;

	private final ConcurrentMap<K,TimestampedValue> cache = new ConcurrentHashMap<K, TimestampedValue>();
	
	public V get(K key) {
		TimestampedValue timestampedValue = cache.get(key);
		
		if (timestampedValue == null || !timestampedValue.isValid()) {
			V value = getFromSource(key);
			timestampedValue = new TimestampedValue(value);
			cache.put(key, timestampedValue);
		}
		
		V value = null; 
		if (timestampedValue != null) {
			value = timestampedValue.value;
		}
		
		return value;
	}

	private V getFromSource(K key){
		// TODO 
		return null;
	}
	
	private final class TimestampedValue {
		final V value;
		final long expiresTimestamp;
		
		TimestampedValue(V value) {
			this.value = value;
			this.expiresTimestamp = System.currentTimeMillis() + TIMEOUT_LENGTH_MS;
		}

		public boolean isValid() {
			boolean valid = (System.currentTimeMillis() < this.expiresTimestamp);
			return valid;
		}
	}
}