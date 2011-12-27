package com.lake.puzzle;

public class TestSynch {

    private Object SYNCH_OBJECT = new Object();
    
    private volatile boolean m_ready = false; 
	
    public void Test1() {
          synchronized (this) {
                doTest();
          }
    }
    
    public String Test2() {
          doTest();
          return "";
    }

    public void Test3() {
        doTest();
        String s = new String("");
        synchronized (SYNCH_OBJECT) {
        	// do stuff
        }

        
        

        Long l = new Long("1");
        
        
        
        
        
        
        
    }
    public void doTest() {
          System.out.print("Stop this being optimised / inlined.ss");         
    }     
}


