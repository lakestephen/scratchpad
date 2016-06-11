package com.lake.test;

import org.junit.Test;


public class Testbed {

	@Test
	public void test() {
		System.out.println(new B().foo());
	
	}
	
	
	public class A {
		public boolean foo() {
			return getClass() == A.class;
		}
	}
	public class B extends A{
	}

}
