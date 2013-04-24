package com.concurrentperformance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestArray {

	
	@Test
	public void test() {
		Integer i1 = 234;
		Integer i2 = 234;
		
		if (i1 == i2) {
			
		}
	}
	
	@Test
	public void testListComp() {
		List l1 = new ArrayList();
		l1.add(l1);
		List l2 = new ArrayList();
		l2.add(l2);
		
		if (l1.equals(l2)) {
			System.out.println("equal");
		}
	}
	
	@Test
	public void testIntMin() {
		int i = Math.abs(Integer.MIN_VALUE);
		System.out.println(i);
	}
}
