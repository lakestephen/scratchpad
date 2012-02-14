package com.lake.synchronizers;
@SuppressWarnings("unused")

public class ThisEscape {
	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}

		});
	}

	private void doSomething(Event e) {
		// TODO Auto-generated method stub
		
	}
}