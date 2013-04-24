package com.concurrentperformance.sumation;

import org.junit.Test;

public class StringTest {

	private static class CompObj {
		private int i;
		CompObj(int i) {
			this.i=i;
		}
		
	}
	
	
	@Test
	public void testStringSumation(){
		
		String B = "B";
		String c = "c";
		String a = B + c;
	}
	
	@Test
	public void testObjectSumation(){
		CompObj co1 = new CompObj(1);
		CompObj co2 = new CompObj(2);
		//cant add two objects 
	//	co1 + co2;
		
	}
	
}
