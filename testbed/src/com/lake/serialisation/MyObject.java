package com.lake.serialisation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public final class MyObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5171204141779408513L;
	
	private static final int VERSION_NUMBER = 2;
	private int i;
	private int j;
	private MyOtherObject k = new MyOtherObject();
	
	private MyObject() {
		
	}
	
	public MyObject(int i) {
		this.i = i;		
	}
	
	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}

	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeInt(VERSION_NUMBER);
		out.writeInt(i);
		out.writeInt(j);
		out.writeObject(k);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		int version = in.readInt( );
		switch(version) {
		case 1:
			readObjectVersion1(in);
			break;
		case 2:
			readObjectVersion2(in);
			break;		
		}
	}
	
	private void readObjectVersion1(ObjectInputStream in) throws IOException {
		i = in.readInt();
	}

	private void readObjectVersion2(ObjectInputStream in) throws IOException, ClassNotFoundException {
		i = in.readInt();
		j = in.readInt();		
		k = (MyOtherObject)in.readObject();		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MyObject [i=" + i + ", j=" + j + ", k=" + k + "]";
	}

}
