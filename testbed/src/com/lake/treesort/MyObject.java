package com.lake.treesort;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class MyObject  {

	int value;
	int id;
	
	public MyObject(int id, int value) {
		this.id = id;
		this.value = value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id;
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
		MyObject other = (MyObject) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
