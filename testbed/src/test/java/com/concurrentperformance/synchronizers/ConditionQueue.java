package com.concurrentperformance.synchronizers;

public class ConditionQueue {

	Object lock = new Object();
	
	
	void doAction() throws InterruptedException {
		synchronized (lock) {
			while (!conditionPredicate()) {
					lock.wait();
			}	
		  // Take Action Here ...
		}
	}

	void changePredicate() throws InterruptedException {
		synchronized (lock) {
			// Change predicate Here ... 
			lock.notifyAll();
		}		
	}

	public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(
            new EventListener() {
                public void onEvent(Event e) {
                  //  doSomething(e);
                }
            });
    }
}

	
	
	private void takeAction() {
		// TODO Auto-generated method stub
		
	}


	private boolean conditionPredicate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
