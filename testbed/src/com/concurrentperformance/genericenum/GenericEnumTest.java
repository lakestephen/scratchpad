package com.concurrentperformance.genericenum;

import org.junit.Test;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class GenericEnumTest {

	@Test
	void testGenericEnum() {
	
		GenericEnum<TestEnum> gen = new GenericEnum<TestEnum>();
		gen.setState(TestEnum.A);
		
		
	}
}
