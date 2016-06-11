package com.lake.synchronizers;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class AsynchProxy {

	// PROTECTED BY: dataTypeLock
  MyDataType dataType;

  //PROTECTING: dataType 
  ReentrantLock dataTypeLock = new ReentrantLock();
  
  // CONDITION'S PREDICATE: the dataTypeLock is ready to be returned.
	Condition dataTypeLockReady = dataTypeLock.newCondition(); 


	MyDataType doGetMyDataType() throws InterruptedException {
		
		dataTypeLock.lock();
		try {
			long nanosTimeout = calculateNanosTimeout();
			
			triggerAsynchrononousProcess();
						
			while (dataType == null && nanosTimeout > 0L) {
				nanosTimeout = dataTypeLockReady.awaitNanos(nanosTimeout);
				System.out.print("TEST");
			}
			
			return dataType;			
		}
		finally {
			dataTypeLock.unlock();
		}
	}
	
	private long calculateNanosTimeout() {
		return 300L /*Seconds*/ * 1000L /*milliseconds*/ * 1000L /*microseconds*/ * 1000L/*nannoseconds*/;
	}

	void callbackMethod(MyDataType populatedDataType)  {
		
		dataTypeLock.lock();
		try {
			// change the predicate state and signal;
			dataType = populatedDataType;			
			dataTypeLockReady.signalAll();			
		}
		finally {
			dataTypeLock.unlock();
		}
	}
	
	private void triggerAsynchrononousProcess() {
		
		Runnable r = new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(1000); // 1 seconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
				
				callbackMethod(new MyDataType());
			}
			
		};
		Thread t = new Thread(r);
		t.start();	
		
	}
	
	@Test
	public void testAsynchService() {
		AsynchProxy proxy = new AsynchProxy();
		
		try {
			MyDataType myDataType = proxy.doGetMyDataType();

			assertNotNull(myDataType);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
