package com.lake.exception;

import java.io.IOException;

public interface MyInterface {

	void throwsException() throws Exception; 

	void throwsIOException() throws IOException; 
	
	void noThrow(); 
	
}
