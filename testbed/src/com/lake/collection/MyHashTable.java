package com.lake.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyHashTable<K,V> implements Map<K, V> {

	private final ArrayList<LinkedList<Entry<K, V>>> buckets = new ArrayList<LinkedList<Entry<K, V>>>();
	private final int size; 
	
	public MyHashTable(int size) {
		this.size = size;
	}

	public void clear() {
		buckets.clear();
		for (int i=0;i<size;i++) {
			buckets.add(new LinkedList<Entry<K, V>>());
		}
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) {
		Entry<K, V> entry = new MyEntry<K, V>(key, value);
		int bucketIndex = getBucketIndex(key);
		LinkedList<Entry<K, V>> bucket = buckets.get(bucketIndex);
		
	//	if (bucket.g contains(entry)) {
			
		//}
		
		return null;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	

	private int getBucketIndex(K key) {
		int hashCode = key.hashCode();
		int bucketIndex = hashCode % size;  
		return bucketIndex;
	}	
	
	
	private static class MyEntry<K, V> implements Entry<K, V>{

		private final K key;
		private V value;
		
		public MyEntry(final K key, final V value) {
			this.key = key;
			this.value = value;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MyEntry other = (MyEntry) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(final V value) {
			V old = this.value;
			this.value = value;
			return old;
		}	
		
	}
	
	
	

		
	
}
