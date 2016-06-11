package com.concurrentperformance.boundedbuffer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ReplaceableArrayListParameterizedTest {

	private final int capacity;
	private final int itemCount;
	
	public ReplaceableArrayListParameterizedTest (int capacity, int itemCount) {
		this.capacity = capacity;
		this.itemCount = itemCount;
	}
	
	@Parameters
	 public static Collection<Object[]> params() {
		return Arrays.asList(new Object[][] {
			   								 {10, 0},
			   								 {10, 1},
			   								 {10, 2},
			   								 {10, 5},
			   								 {10, 9},
											   {10, 10},
											   {10, 11},
											   {10, 15},
											   {10, 19},
											   {10, 20},
											   {10, 21},
											   {10, 212}} );
	  
	}		
	
	@Test
	public void testCapacityAndItemRange() {

		final int expectedItemCount = Math.min(capacity, itemCount);
		
		ReplaceableBoundedBuffer<Integer> e = 
			new ReplaceableLinkedListBoundedBuffer<Integer>(capacity);
		
		for(int i=0;i<itemCount;i++) {
			e.add(Integer.valueOf(i));
		}

		Assert.assertEquals(expectedItemCount, e.getSize());
		
		List<Integer> data = e.asList();
		
		Assert.assertEquals(expectedItemCount, data.size());
		
		for(int i=0;i<expectedItemCount;i++) {
			int expectedItemValue = itemCount - expectedItemCount + i;  
			Integer item = data.get(i);
			Assert.assertEquals(expectedItemValue, item.intValue());
		}		
	}	
}
