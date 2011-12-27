package com.lake.hash;

public class BadHash {

	String name;
	int size;
	
	public BadHash(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 34;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadHash other = (BadHash) obj;
		return name.equals(other.name);
	}
	
}
