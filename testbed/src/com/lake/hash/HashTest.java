package com.lake.hash;

import java.util.HashSet;
import java.util.Set;


import org.junit.Test;

import junit.framework.Assert;

public class HashTest {

	@Test
	public void testBadHash() {
		
		Set<BadHash> c = new HashSet<BadHash>();
		
		c.add(new BadHash("TEST", 1));
		
		boolean contains = c.contains(new BadHash("TEST", 20000));
		
		Assert.assertTrue(contains);		
	}	
}
