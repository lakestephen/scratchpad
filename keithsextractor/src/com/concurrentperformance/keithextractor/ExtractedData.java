package com.concurrentperformance.keithextractor;

/**
 * TODO
 *
 * @author Stephen
 */
public class ExtractedData {

	private Integer numberOfOnStates;

	public Integer getNumberOfOnStates() {
		return numberOfOnStates;
	}

	public void setNumberOfOnStates(Integer numberOfOnStates) {
		this.numberOfOnStates = numberOfOnStates;
	}

	@Override
	public String toString() {
		return "ExtractedData{" +
				"numberOfOnStates=" + numberOfOnStates +
				'}';
	}
}
