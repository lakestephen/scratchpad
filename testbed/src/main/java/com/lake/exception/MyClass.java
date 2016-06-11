package com.lake.exception;

import java.io.IOException;

public class MyClass implements MyInterface {

	@Override
	public void throwsException() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void throwsIOException() throws IOException {
		throw new IOException();
		
	}

	@Override
	public void noThrow() {
		// TODO Auto-generated method stub
		
	}

}
