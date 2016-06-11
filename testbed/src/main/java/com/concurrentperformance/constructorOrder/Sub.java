package com.concurrentperformance.constructorOrder;

import java.util.Date;

public class Sub extends Super {

	private final Date date;
	
	public Sub() {
		super();
		date = new Date();
	}
	
	protected void overrideMe() {
		System.out.println(date);
		
	}
	
	public static void main(String[] args) {
		new Sub().overrideMe();
	}
}
