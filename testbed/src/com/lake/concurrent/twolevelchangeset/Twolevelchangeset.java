package com.lake.concurrent.twolevelchangeset;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Stephen Lake
 *
 */
public class Twolevelchangeset {

	private final ConcurrentMap<Key, String> map = new ConcurrentHashMap<Key, String>(); 
	
	static final class Key {
		final Object obj;
		final String str;
		int hash; //defaults to 0

		public Key(Object obj, String str) {
			super();
			this.obj = obj;
			this.str = str;			
		}

		@Override
		public int hashCode() {
			int h = hash;
			if (h == 0) {
				h = calculateHashCode();
				hash = h;
			}
			return h;
		}

		private int calculateHashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((obj == null) ? 0 : obj.hashCode());
			result = prime * result + ((str == null) ? 0 : str.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (this.obj == null) {
				if (other.obj != null)
					return false;
			} else if (!this.obj.equals(other.obj))
				return false;
			if (str == null) {
				if (other.str != null)
					return false;
			} else if (!str.equals(other.str))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Key [obj=" + obj + ", str=" + str + "]";
		}		
	}
	
	public void listen(Object obj, String sym, String value) {
		final Key key = new Key(obj, sym);
		map.put(key, value);
	}
	
	public Map<Object, Map<String, String>> getUpdated(){
		final Map<Object, Map<String, String>> result = new HashMap<Object, Map<String, String>>();
		
		final Set<Entry<Key, String>> e = map.entrySet();
		final Iterator<Entry<Key, String>> it = e.iterator();
		
		while (it.hasNext()) {
			final Entry<Key, String> entry = it.next();
			
			final Key key = entry.getKey();
			final Object obj = key.obj;
			final String str = key.str;
			final String val = entry.getValue();
			
			//get the mapping for the object
			Map<String,String> mappingsForObj = result.get(obj);
			if (mappingsForObj == null) {
				mappingsForObj = new HashMap<String, String>();
				result.put(obj, mappingsForObj);
			}
			
			mappingsForObj.put(str, val);
			
			// only removes if the value has not changed, so if it has been updated, 
			// then leave in the map for next time. 
			map.remove(key, val);			
		}
		
		return result;
	}

}
