package com.concurrentperformance.lazyload;

public final class SafeLazyLoad {

	private static class Initializer {
		// Put method break point here to prove that this is only initialised on reference
		static SafeLazyLoad instance = new SafeLazyLoad();

	}
	
	private SafeLazyLoad(){
		// Put method break point here to prove that this is only initialised on reference
	}

	public static SafeLazyLoad getInstance() {
		return Initializer.instance;
	}	
}
