package com.concurrentperformance.boundedbuffer;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ReplaceableArrayListTest {

	private ReplaceableBoundedBuffer<Integer> createFixture(int capacity) {
		return new ReplaceableLinkedListBoundedBuffer<Integer>(capacity);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorZeroCapacity() {
	
		createFixture(0);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegativeCapacity() {
		createFixture(-10);
	}


	@Test
	public void testConstructorCapacity() {
		final int capacity = 10; 
		ReplaceableBoundedBuffer<Integer> e = createFixture(capacity);	
		Assert.assertEquals(e.getCapacity(), capacity);
	}

	@Test
	public void testAsListCapacity() {
		final int capacity = 10; 
		ReplaceableBoundedBuffer<Integer> e = createFixture(capacity);	
		List<Integer> list = e.asList(2);
		Assert.assertEquals(0, list.size());		

		e.add(0);
		e.add(1);
		
		list = e.asList(2);
		Assert.assertEquals(2, list.size());		
		Assert.assertEquals(0, list.get(0).intValue());
		Assert.assertEquals(1, list.get(1).intValue());

		e.add(2);

		list = e.asList(2);
		Assert.assertEquals(2, list.size());		
		Assert.assertEquals(1, list.get(0).intValue());
		Assert.assertEquals(2, list.get(1).intValue());
	}

}
