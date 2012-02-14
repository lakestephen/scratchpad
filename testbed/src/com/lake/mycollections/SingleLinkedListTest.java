package com.lake.mycollections;

import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;


public class SingleLinkedListTest {


	@Test
	public void addingFiveItemsWeHaveFiveItems() {
		final int SIZE = 5;
		
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		
		for(int i=0;i<SIZE;i++) {
			linkedList.add(i);
		}
		Assert.assertEquals(SIZE, linkedList.size());
		
	}
	
	@Test 
	public void addingAndRemovingAnItemLeavesEmptyList() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		
		Integer item = new Integer(1000);
		
		linkedList.add(item);
		Assert.assertEquals(1, linkedList.size());

		linkedList.remove(item);	
		Assert.assertEquals(0, linkedList.size());
	}

	@Test 
	public void addingAndRemovingAnItemTwiceLeavesEmptyList() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		
		Integer item = new Integer(1000);
		
		linkedList.add(item);
		linkedList.add(item);
		Assert.assertEquals(2, linkedList.size());

		linkedList.remove(item);	
		linkedList.remove(item);	
		Assert.assertEquals(0, linkedList.size());
	}

	@Test 
	public void getReturnsCorrectItem() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		
		for (int i=0;i<5;i++) {
			linkedList.add(new Integer(i));			
		}
		
		for (int i=0;i<5;i++) {
			Integer item = linkedList.get(i);
			Assert.assertEquals(Integer.valueOf(i), item);
		}				
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getIndexOutOfBoundsOnZeroLengthListThrowsException() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		linkedList.get(0);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void getIndexOutOfBoundsThrowsException() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();
		linkedList.add(new Integer(0));
		linkedList.get(1);
	}

	@Test 
	public void iteratingEmptyListDoesNotGoIntoForBlock() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();

		for (Integer item:linkedList) {
			Assert.fail();			
		}
	}

	@Test 
	public void iteratingSingleItemGoesIntoForBlockOnce() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();

		linkedList.add(1);
		int count = 0;
		for (Integer item : linkedList) {
			count++;
		}
		Assert.assertEquals(1, count);
	}
	
	@Test 
	public void iteratingFiveItemsGoesIntoForBlockFiveTimes() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();

		final int SIZE = 5;
		for (int i=0;i<SIZE;i++) {
			linkedList.add(i);
		}
		
    int count = 0;
		for (Integer item : linkedList) {
			count++;
		}
		Assert.assertEquals(SIZE, count);
	}
	
	@Test 
	public void iteratorRemovesSingleItem() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();

		linkedList.add(1);
		
    Iterator<Integer> it = linkedList.iterator();
		Assert.assertEquals(1, linkedList.size());

		it.remove();
		Assert.assertEquals(0, linkedList.size());
	}
	
	@Test(expected=IllegalStateException.class)
	public void iteratorRemovingNodeOnEmptyList() {
		List<Integer> linkedList = new SingleLinkedList<Integer>();

    Iterator<Integer> it = linkedList.iterator();

		it.remove();
	}
}
