package com.lake.documentation.structure;

import java.util.List;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class BeanMeta {

	String className;
	List<BeanPropertyMeta> properties;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bean[" + className + ", " + properties + "]";
	}
}