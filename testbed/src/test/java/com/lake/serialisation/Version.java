package com.lake.serialisation;

import java.io.Serializable;

final public class Version implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 119769252047945911L;
	
	final int version;

	public Version(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return version;
	}
}
