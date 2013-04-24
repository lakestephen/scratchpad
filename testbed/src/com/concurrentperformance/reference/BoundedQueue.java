package com.concurrentperformance.reference;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class BoundedQueue<E> implements Queue<E> {

	private final E[] buf;

	public BoundedQueue(int capacity) {
		this.buf = (E[]) new Object[capacity];
	}
	
	
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
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

}
