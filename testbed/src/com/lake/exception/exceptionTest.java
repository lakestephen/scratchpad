package com.lake.exception;


import java.io.IOException;

import org.junit.Test;

public class exceptionTest {

	@Test(expected = IOException.class)
	public void myExceptionTest() throws Exception
	{
		MyInterface m = new MyClass();
		
			int i=0;
			assert (i==1);
			
			m.throwsIOException();
		
	}
	
}
