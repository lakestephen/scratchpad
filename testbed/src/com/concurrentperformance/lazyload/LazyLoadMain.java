package com.concurrentperformance.lazyload;

public class LazyLoadMain {

	public static void main(String[] args) {

		// first ref of SafeLazyLoad, so classloaded
		SafeLazyLoad safeLazyLoad = null;
		
		//Only now do we create the instance
		safeLazyLoad = safeLazyLoad.getInstance(); 
		
	}
}
