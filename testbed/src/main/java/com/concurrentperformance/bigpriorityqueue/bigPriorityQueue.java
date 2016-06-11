package com.concurrentperformance.bigpriorityqueue;

import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Ignore;
import org.junit.Test;

public class bigPriorityQueue {


	private static final int COUNT = 100000;

	@Test
	@Ignore	
	public void doPriorityBlockingQueue() throws InterruptedException {

		PriorityBlockingQueue<My86ByteStruct> e = new PriorityBlockingQueue<My86ByteStruct>();
		
		long startTime = System.nanoTime();
		
  	Random r = new Random();
		
		for (int i=0;i<COUNT;i++) {
			if (i%10000 == 0) {
				System.out.println(i);
			}
			Integer nextInt = r.nextInt();
			e.add(new My86ByteStruct(nextInt));
		}		
		My86ByteStruct s;
		
		while ((s = e.poll()) != null) {
	//		System.out.println(s);
		}

		long processTime = System.nanoTime() - startTime;
		
		System.out.print("doPriorityBlockingQueue: " + processTime);
	
	}
	
	@Test

	public void doConcurrentSkipListMap() {

		ConcurrentSkipListMap<Integer, My86ByteStruct> e = new ConcurrentSkipListMap<Integer, My86ByteStruct>();
		
		long startTime = System.nanoTime();
		
  	Random r = new Random();
		
		for (int i=0;i<COUNT;i++) {
			if (i%10000 == 0) {
				System.out.println(i);
			}
			Integer nextInt = r.nextInt();
			e.put(nextInt, new My86ByteStruct(nextInt));
		}
		Entry<Integer, My86ByteStruct> s;
		
		while ((s = e.pollFirstEntry()) != null) {
			//		System.out.println(s.getValue());

		}
		
		long processTime = System.nanoTime() - startTime;
		
		System.out.print("doConcurrentSkipListMap: " + processTime);
	
	}
	
	public static class My86ByteStruct implements Comparable<My86ByteStruct> {

		private Integer i;
		private String data;
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "My86ByteStruct [i=" + i + "]";
		}

		public My86ByteStruct(Integer i) {
			this.i = i;
			data = "01234567890123456789012345678901234567890123456789012345678901234567890123456789" + i;
		}
		
		@Override
		public int compareTo(My86ByteStruct o) {
			
			return i.compareTo(o.i);
		}	
		
	}
	
}
