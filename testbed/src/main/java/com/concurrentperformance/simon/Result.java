package com.concurrentperformance.simon;

/**
 * TODO comments???
 * User: Stephen
 */
public class Result {

	private final String url;

	public Result(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "R{" + url + '}';
	}
}
