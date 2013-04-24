package com.lake.innerclass;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author lakes
 *
 */
public class TestbedMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestbedMain().run();
	}

	/**
	 * 
	 */
	public void run() {
		int i = 2147483647;
		float f = i;
		System.out.print(i);
		float g = f- 64;
		float h = f- 65;
		
		System.out.print(i + " " + f + " " + g + " " + h);
		
		
	}

}
