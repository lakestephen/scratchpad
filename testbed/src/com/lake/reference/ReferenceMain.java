package com.lake.reference;

import java.util.IdentityHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import javax.management.relation.RoleUnresolvedList;

public class ReferenceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Object s1 = new Object();
		String s2 = new String("");
		
		if ( s1 == s2 )  {
			System.out.println("never entered");
		}
		
		String sA = "";
		String sB = "";
		if ( sA == sB ) {
			System.out.println("will enter");
		}
		
		
		ConcurrentHashMap<String, String> s = null;
		s.putIfAbsent("key", "value");
		
		
		
		SoftSet<String> set = new SoftSet<String>(10);
		
		System.out.println("Starting");

		for (int i=0;i<100000000;i++) {
			String ss = new String("Str " + i);
			set.add(ss);
		}
		
		System.out.println("Finished");
	}

}
