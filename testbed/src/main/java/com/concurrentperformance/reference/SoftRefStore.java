package com.concurrentperformance.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

public class SoftRefStore {

	final ReferenceQueue<MyDummyObject> _discardedRefQueue = new ReferenceQueue<MyDummyObject>(); 
	final List<Reference<MyDummyObject>> _refList = new LinkedList<Reference<MyDummyObject>>(); 
	
	public SoftRefStore(){
		startCleanupThread();
		makeRefs();
		createMemChurn();
	}	
	
	private void createMemChurn() {
		List<Object[]> keep = new LinkedList<Object[]>(); 
		for(;;) {
			Object[] arr = new Object[10000];
			//keep.add(arr);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
	}

	private void makeRefs() {
		for (int i=0;i<10000;i++) {
			MyDummyObject mdo = new MyDummyObject(i);
			SoftReference<MyDummyObject> mdoRef = new SoftReference<MyDummyObject>(mdo, _discardedRefQueue);
			_refList.add(mdoRef);
		}
		System.out.println("Finished Creating Refs");
		
	}

	private void startCleanupThread() {
		Runnable r = new Runnable() {
			
			public void run() {
				for (;;) {
					
					try {
						Reference<? extends MyDummyObject> ref = _discardedRefQueue.remove(10000);
						if (ref != null) {
							System.out.println("Removed Ref" + ref.toString());
						}						
					} catch (InterruptedException e) {
						System.out.println("Interupted");
					}
				}
			}
		};
		
		Thread t = new Thread(r, "SoftRefStore.Cleanup");
		t.setDaemon(true);
		t.start();		
	}
	
	public class MyDummyObject {
		int index;
		Object[] o = new Object[1000];
		
		MyDummyObject(int index) {
		   this.index = index;
		}
	}
}
