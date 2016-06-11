package com.concurrentperformance.serialisation;

import java.io.Serializable;

public final class MyOtherObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2728922804286380368L;
	
	private int i;
	private int j;
	private int k;

	MyOtherObject() {
		
	}
	
	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}


//version specific demarshalling co
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyOtherObject [i=" + i + ", j=" + j + "]";
	}


}
