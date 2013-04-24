package com.lake.boundedbuffer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A thread safe LinkedList backed implementation of the ReplaceableBoundedBuffer<E> 
 * 
 * This implementation provides constant time add() operations, and a cheap  
 * get...(). The operations that get views of the structure do so by returning 
 * a sub list view. 
 *  
 * @author David Smith
 *
 * @param <E> the type the structure contains.
 */
public class ReplaceableLinkedListBoundedBuffer<E> implements ReplaceableBoundedBuffer<E> {

	/** The fixed capacity of the List*/
	private final int capacity; 

	/** The List that holds our items. This will bee sized to capacity and will never grow*/
	private final LinkedList<E> items;
	
	/**
	 * Constructs a new ReplaceableLinkedListBoundedBuffer with the passed capacity.
	 * 
	 * @param capacity, must be a positive integer
	 */
	public ReplaceableLinkedListBoundedBuffer(final int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		
		this.capacity = capacity;

		// Construct a new list with appropriate capacity.
		items = new LinkedList<E>();
	}
	
	@Override
	public void add(E e) {
		items.addLast(e);
		
		while (items.size() > capacity) {
			items.removeFirst();
		}		
	}

	@Override
	public List<E> asList() {
		return Collections.unmodifiableList(items);
	}

	@Override
	public List<E> asList(int topCount) {
		topCount = Math.min(topCount, capacity);
		topCount = Math.min(topCount, items.size());

		if (topCount < 1) {
			return Collections.emptyList();
		}
		
		// Although this is a copy, the subList view holds a 
		// reference back to its parent to maintain the 'view'.   
		List<E> subCopy = items.subList(items.size()-topCount, items.size());
		return Collections.unmodifiableList(subCopy);
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getSize() {
		return items.size();
	}

}
