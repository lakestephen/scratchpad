package com.concurrentperformance.psudoSingleton;

public class PsudoSingletonMain {

	public static void main(String[] args) {
		PsudoSingletonMain psm = new PsudoSingletonMain();
		psm.doStuff();
		
		synchronized (psm) {
		
		}
	}

	private synchronized void doStuff() {
		
		Thread t = new Thread("T") {
			public void run() {
				
				synchronized (PsudoSingletonMain.this) {
					for (int i=0;i<10;i++) {
						System.out.println(i);
					}
				}				
			}			
		};		
		
		t.start();
	}


}
