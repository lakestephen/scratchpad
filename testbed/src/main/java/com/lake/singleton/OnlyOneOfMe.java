package com.lake.singleton;

public enum OnlyOneOfMe implements Valueable {

	INSTANCE;

	private int value;

	public static OnlyOneOfMe getInstance() {
		return INSTANCE;
	}	
	
	public int getValue()
	{
		return this.value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
