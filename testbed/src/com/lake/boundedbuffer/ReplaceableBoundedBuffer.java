package com.lake.boundedbuffer;

import java.util.List;

/**
 * A capacity bound List. When the capacity is exceed, the oldest item 
 * is dropped from the list to make room. 
 *  
 * @author David Smith
 *
 * @param <E> the type the structure contains.
 */
public interface ReplaceableBoundedBuffer<E> {
	
	/**
	 * Adds an item to the head of the structure.
	 * 
	 * @param item, item to add to the head of the structure	  
	 */
	public void add(E e);
	
	/**
	 * Get all the items in the structure.
	 *  
	 * @return List<E> the list 'view' of the items in the structure
	 */
	public List<E> asList();

	/**
	 * Get the top items in the structure. 
	 *  
	 * @param topCount the number of items to get. This must be less 
	 * than the capacity.
	 * @return List<E> the list 'view' of the items in the structure
	 */
	public List<E> asList(int topCount);
	
	/**
	 * Gets the capacity of the internal structure. This is a fixed size
	 *  
	 * @return the internal capacity
	 */
	public int getCapacity();
	
	/**
	 * Gets the number of objects in the structure.
	 *  
	 * @return int, the number of objects in the structure. 
	 */
	public int getSize();
}
