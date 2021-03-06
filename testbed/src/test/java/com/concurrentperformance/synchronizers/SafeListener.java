package com.concurrentperformance.synchronizers;

@SuppressWarnings("unused")


public class SafeListener {
	private final EventListener listener;

	private SafeListener() {
		listener = new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}
		};
	}

	public static SafeListener newInstance(EventSource source) {
		SafeListener safe = new SafeListener();
		source.registerListener(safe.listener);
		return safe;
	}

	private void doSomething(Event e) {
		// TODO Auto-generated method stub

	}
	
 

  
  
}
