package com.concurrentperformance.innerclass;

import com.concurrentperformance.innerclass.WithInner.Inner;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TestWithInner {

	WithInner wi = new WithInner();
	
	Inner i = wi.new Inner();
	
}
