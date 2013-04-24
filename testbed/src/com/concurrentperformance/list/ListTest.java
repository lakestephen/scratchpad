package com.concurrentperformance.list;

import org.junit.Test;

import java.util.ArrayList;

/**
 * TODO
 * User: Stephen
 */
public class ListTest {

	@Test
	public void testList() {
		ArrayList<String> al = new ArrayList<>();
		al.ensureCapacity(20);
		al.set(10, "Ten");
	}

}
