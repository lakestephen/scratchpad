package com.concurrentperformance.memModelDemo;

public class MemModelDemo {

	private static final int NUM_CYCLES = 100;

	public static void main(String[] args) {
		new MemModelDemo().flipFlop();
	}
	
	private boolean a = false;
	private boolean b = true;

	private void flipFlop() {
		
		Thread threadA = new Thread("threadA") {
			public void run() {
				
				for (int i=0; i<NUM_CYCLES; i++) {
					long l = 0;
					while (a) {
						l++; // spin
					}
					System.out.println(Thread.currentThread().
							getName()+": loop "+i+", spin count = "+l);
					a = true;
					b = false;
				}
				
			}
		};
		
		Thread threadB = new Thread("threadB") {
			public void run() {
				
				for (int i=0; i<NUM_CYCLES; i++) {
					long l = 0;
					while (b) {
						l++; // spin
					}
					System.out.println(Thread.currentThread().
							getName()+": loop "+i+", spin count = "+l);
					b = true;
					a = false;
				}
				
			}
		};

		threadA.start();
		threadB.start();
	}
}
