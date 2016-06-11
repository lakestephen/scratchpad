package com.concurrentperformance.boundedbuffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A thread safe ArrayList backed implementation of the ReplaceableBoundedBuffer<E> 
 * 
 * This implementation provides substantially cheaper add() operations than 
 * get...(). The operations that get views of the structure do so by returning 
 * a new list.  
 *  
 * @author David Smith
 *
 * @param <E> the type the structure contains.
 */
public final class ReplaceableArrayListBoundedBuffer<E> implements ReplaceableBoundedBuffer<E> {
	
	/** The fixed capacity of the List*/
	private final int capacity; 

	/** Flag indicating that the buffer has been filled, and is now overwriting old values. */
	private boolean full = false; 

	/** The List that holds our items. This will bee sized to capacity and will never grow*/
	private final List<E> items;

	/**
	 * The position of the most recently added item. 
	 * 
	 * By setting the newest item position to -1, the first item will 
	 * be added in position 0. 
	 */
	private int newestItemPosition = -1;

	
	/**
	 * Constructs a new ReplaceableArrayListBoundedBuffer with the passed capacity.
	 * 
	 * @param capacity, must be a positive integer
	 */
	public ReplaceableArrayListBoundedBuffer(final int capacity) {
		
		if (capacity <= 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		
		this.capacity = capacity;
		
		// Construct a new list with appropriate capacity.
		items = new ArrayList<E>(capacity);
	}

	/**
	 * Adds an item to the structure 
	 * 
	 * @param item, item to add to the structure
	 * 
	 */
	@Override
	public synchronized void add(E item) {
		incrementNewestItemPosition();
		if (!full) {
			// Add the item, and grow the array. (Does not actually grow the array, 
			// because the capacity is set on construction). 
			items.add(item);
		}
		else {
			// Overwrite an existing item.
			items.set(newestItemPosition, item);
		}
	}
	
	/**
	 * Get as a List<E> by creating a copy of the data structure. 
	 * 
	 * @return List<E> the list 'view' of the items in the structure
	 */
	@Override
	public synchronized List<E> asList() {
		List<E> copy = asListInternal();
		return Collections.unmodifiableList(copy);
	}

	/**
	 * Get the top items in the structure. This method returns a view 
	 * of a List<E> by creating a copy of the data structure.
	 *  
	 * @param topCount the number of items to get. This must be less 
	 * than the capacity.
	 * @return List<E> the list 'view' of the items in the structure. This may contain 
	 * @throws IllegalArgumentException if topCount is greater than the capacity
	 */
	@Override
	public synchronized List<E> asList(int topCount) {
		topCount = Math.min(topCount, capacity);
		topCount = Math.min(topCount, items.size());

		if (topCount < 1) {
			return Collections.emptyList();
		}
		
		// Although this is a copy, the subList view holds a 
		// reference back to its parent to maintain the 'view'.   
		List<E> copy = asListInternal();
		List<E> subCopy = copy.subList(items.size()-topCount, items.size());
		return Collections.unmodifiableList(subCopy);
	}
	
	/**
	 * Copy the main data structure, and correct for the rotation of the buffer
	 * @return
	 */
	private List<E> asListInternal() {
		List<E> copy = new ArrayList<E>(items);
		if (full) {
			Collections.rotate(copy, -(newestItemPosition+1));
		}
		return copy;
	}
	
	/**
	 * Increments the position of the newest item, 
	 * wrapping, and flagging full when full.   
	 */
	private void incrementNewestItemPosition() {
		assert (newestItemPosition >=0 && newestItemPosition < capacity);
		newestItemPosition++;
		if (newestItemPosition >= capacity) {
			newestItemPosition -= capacity;
			full = true;
		}		
	}

	@Override
	public int getSize() {
		return items.size();
	}	

	@Override
	public int getCapacity() {
		return capacity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReplaceableArrayListBoundedBuffer [capacity=" + capacity
				+ ", items=" + asList() + "]";
	}			
	
}
