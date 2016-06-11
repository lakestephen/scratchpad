package com.concurrentperformance.buff;

import java.util.Iterator;

public interface ConstrainedBuff<E> {
	
	/**
	 * Add an item to the head of the buffer.  
	 * @param t
	 */
	public void add(E element);	
	
	/**
	 * Returns an iterator over the elements that are within the constraint at 
	 * the time the iterator is constructed.    
	 * @return
	 */
	public Iterator<E> iterator();

	/**
	 * Retrieve and remove all the items that have exceeded the constraint, 
	 * blocking if there are none.  
	 * @return
	 */
	public E takeExpired() throws InterruptedException;
}