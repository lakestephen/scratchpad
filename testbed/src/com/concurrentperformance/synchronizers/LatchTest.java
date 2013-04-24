package com.concurrentperformance.synchronizers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LatchTest {

  ReentrantLock lock = new ReentrantLock();
	Condition c = lock.newCondition();


	void doGetInfo() {
		
		
	}
}
