package com.concurrentperformance.javatest;

import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.Assert.assertEquals;

/**
 * TODO comments ???
 *
 * @author Lake
 */
public class WorkingStackTest {


	WorkingStack stack = new WorkingStack();

	@Test
	public void pushItemGrowsStackSize() {
		stack.push(123);

		assertEquals(1, stack.size());
	}

	@Test
	public void popItemShrinksStackSize() {
		stack.push(123);
		stack.pop();

		assertEquals(0, stack.size());
	}

	@Test
	public void peakReturnsTopOfStack() {
		stack.push(111);
		stack.push(222);

		assertEquals(222, stack.pop());
	}

	@Test
	public void peakItemDoesNotAffectStackSize() {
		stack.push(123);
		assertEquals(1, stack.size());

		stack.peek();
		assertEquals(1, stack.size());
	}

	@Test(expected = EmptyStackException.class)
	public void popItemOnEmptyStackThrows() {
		stack.pop();
	}

	@Test(expected = EmptyStackException.class)
	public void peakItemOnEmptyStackThrows() {
		stack.peek();
	}

	@Test
	public void pushItemOnFullStackReallocates() {

		for (int i=0;i<10;i++) {
			stack.push(i);
		}

		assertEquals(10, stack.size());
		assertEquals(10, stack.capacity());

		stack.push(11);

		assertEquals(11, stack.size());
		assertEquals(20, stack.capacity());
	}

	@Test
	public void returnTheCorrectItems() {

		for (int i=0;i<1000;i++) {
			stack.push(i);
		}

		for (int i=999;i>=0;i--) {
			long value = stack.pop();
			assertEquals(i,value);
		}

	}

}
