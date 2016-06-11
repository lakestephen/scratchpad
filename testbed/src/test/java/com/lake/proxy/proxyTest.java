package com.lake.proxy;

import java.lang.reflect.InvocationHandler;

import org.junit.Test;

public class proxyTest {

	
	@Test
	public void doProxyTest() {
		InvocationHandler i = new MyInvocationHandler();
	}
	
}
