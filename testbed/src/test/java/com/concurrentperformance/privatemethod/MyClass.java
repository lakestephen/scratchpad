package com.concurrentperformance.privatemethod;

public class MyClass extends BaseClass {

	public void publicMethod() {
		super.publicMethod();
		privateMethod();
	}
	
	private void privateMethod() {
		System.out.print(getClass());
	}
	
}
