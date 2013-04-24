package com.lake.treesort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TreesortTest {

	@Test
	public void hashSize() {
		testSetSize( new HashSet<MyObject>());		
	}


	@Test
	public void treeSize() {
		
		Comparator<MyObject> comp = new Comparator<MyObject>() {

			@Override
			public int compare(MyObject o1, MyObject o2) {
				int val = o1.value - o2.value;
				return val;
			}			
		};
		
		testSetSize( new TreeSet<MyObject>(comp));		
	}

	private void testSetSize(Set<MyObject> s) {
		Assert.assertTrue(s.size() == 0);
		
		s.add(new MyObject(1,10));
		s.add(new MyObject(2,10));
		
		Assert.assertTrue(s.size() == 2);
	}	
}
