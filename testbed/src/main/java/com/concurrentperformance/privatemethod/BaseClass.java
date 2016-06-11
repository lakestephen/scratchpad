package com.concurrentperformance.privatemethod;

public class BaseClass {

	public void publicMethod() {
		privateMethod();
	}
	
	private void privateMethod() {
		System.out.print(getClass());
	}
}
