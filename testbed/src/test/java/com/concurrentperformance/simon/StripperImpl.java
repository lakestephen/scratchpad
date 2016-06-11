package com.concurrentperformance.simon;

/**
 * TODO comments???
 * User: Stephen
 */
public class StripperImpl implements Stripper {
	@Override
	public String strip(String url)  {
		int index = url.indexOf("&");
		if (index >= 0 ) {
			url = url.substring(0, index);
		}
		return url;
	}
}
