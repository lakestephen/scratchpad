package com.lake.concurrent.twolevelchangeset;

import java.util.Map;

import org.junit.Test;


/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class TwolevelchangesetTest {

	Twolevelchangeset tlcs = new Twolevelchangeset();
	
	@Test
	public void doTest() {
		
		Object o1 = "Obj 1";
		Object o2 = "Obj 2";
		
		String s1 = "Str 1";
		String s2 = "Str 2";
		String s3 = "Str 3";
		
		String v1 = "Val 1";
		String v2 = "Val 2";
		String v3 = "Val 3";
		
		tlcs.listen(o1, s1, v1);
		tlcs.listen(o1, s2, v2);
		tlcs.listen(o1, s1, v3);
		
		tlcs.listen(o2, s1, v1);
		tlcs.listen(o2, s2, v2);
		tlcs.listen(o2, s3, v3);
		
		Map<Object, Map<String, String>> a = tlcs.getUpdated();
		
	}
}
