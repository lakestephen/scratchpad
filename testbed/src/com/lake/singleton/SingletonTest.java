package com.lake.singleton;

import org.junit.Test;

public class SingletonTest {

	Valueable onom = OnlyOneOfMe.getInstance();
	
	@Test    
	public void doTest()
	{
		onom.setValue(1);
		int value = onom.getValue();		
	}
}
