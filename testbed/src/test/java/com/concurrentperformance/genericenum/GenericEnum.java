package com.concurrentperformance.genericenum;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class GenericEnum<E extends Enum<E> & ImageName> {

	E state;
	
	public void setState(E state) {
		this.state = state;
	}
	
}
