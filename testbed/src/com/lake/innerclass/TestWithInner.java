package com.lake.innerclass;

import com.lake.innerclass.WithInner.Inner;

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
