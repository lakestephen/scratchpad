package com.lake.threadLocal;

import java.lang.ref.WeakReference;

public class MyWidget {
 	

	private WeakReference<MyWidget> myWidget = 
		new WeakReference<MyWidget>(new MyWidget());
	
  private static ThreadLocal<MyWidget> myWidgetThreadLocal = 
  	new ThreadLocal<MyWidget>() {
      protected MyWidget initialValue() {
          return new MyWidget();
      }
  };

  /** Gets a thread local instance of MyWidget */
  public static MyWidget getThreadLocalInstance() {
  	return myWidgetThreadLocal.get();
  }
  
  /** private constructor */
  private MyWidget() {

  } 
}




