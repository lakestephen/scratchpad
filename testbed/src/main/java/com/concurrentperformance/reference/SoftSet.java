package com.concurrentperformance.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A Set implementation that holds soft references to all but the 
 * most recent items  
 * 
 * @author Stephen Lake
 *
 * @param <E> the type of elements maintained by this set
 */
public class SoftSet<E> implements Set<E> {

	final Set<SoftReference<E>> _data = new HashSet<SoftReference<E>>();
	final ReferenceQueue<E> _refQueue = new ReferenceQueue<E>(); 
	final BoundedQueue<E> _hardRefs;
	
	public SoftSet(int hardRefCount) {
		_hardRefs = new BoundedQueue<E>(hardRefCount);
		startCleanupThread();
	}
	
	public boolean add(E e) {
		SoftReference<E> softRef = new SoftReference<E>(e, _refQueue);
		_hardRefs.add(e);
		return _data.add(softRef);		
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E e : c) {
			add(e);
		}
		return false;
	}

	public void clear() {
		_data.clear();		
	}

	public boolean contains(Object o) {
		return _data.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return _data.containsAll(c);
	}

	public boolean isEmpty() {
		return _data.isEmpty();
	}

	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void startCleanupThread() {
		Runnable r = new Runnable() {
			
			public void run() {
				while (true) {
					
					Reference<? extends E> ref = null;
					try {
						ref = _refQueue.remove();
					} catch (InterruptedException e) {
						System.out.println("Interupted");
					}
					
					if (ref != null) {
						_data.remove(ref);
					}
				}
			}
		};
		
		Thread t = new Thread(r,"SoftSet.Cleanup");
		t.setDaemon(true);
		t.start();		
	}
	
	


}
