package com.lake.boundedbuffer;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)	
public class ReplacableArrayListParameterizedTimeTests {

	private final int capacity;
	private final int iterationCount;

	public ReplacableArrayListParameterizedTimeTests (int capacity, int iterationCount) {
		this.capacity = capacity;
		this.iterationCount = iterationCount;
	}
	
	@Parameters
	 public static Collection<Object[]> params() {
		return Arrays.asList(new Object[][] {
			   								 {10, 1},
			   								 {10, 10},
			   								 {10, 100},
			   								 {10, 1000},
			   								 {10, 10000},
			   								 {10, 100000},
			   								 {100, 1},
			   								 {100, 10},
			   								 {100, 100},
			   								 {100, 1000},
			   								 {100, 10000},
			   								 {100, 100000}
			   								 } );
	  
	}		

	@Test
	public void timeArrayListVersion() {
		timeAdds(new ReplaceableArrayListBoundedBuffer<Integer>(capacity));		
	}
	
	@Test
	public void timeLinkedListVersion() {
		timeAdds(new ReplaceableLinkedListBoundedBuffer<Integer>(capacity));		
	}

	private void timeAdds(ReplaceableBoundedBuffer<Integer> fixture) {
		final long startTime = System.nanoTime();
		
		for(int i=0;i<iterationCount;i++) {
			fixture.add(Integer.MAX_VALUE); 
		}

		final long endTime = System.nanoTime();
		
		System.out.format("Class: %58s, Capacity: %3d Iterations:, %6d, Time: %10d nS\r\n", 
				fixture.getClass().getName(), fixture.getCapacity(), iterationCount, endTime - startTime);		
		
	}
	
}
