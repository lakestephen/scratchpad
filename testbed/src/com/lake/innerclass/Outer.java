package com.lake.innerclass;

class Outer{
	class Inner extends HasStatic{
		static final int y = 4;    		// compile-time error, an inner class
	}
	
}
