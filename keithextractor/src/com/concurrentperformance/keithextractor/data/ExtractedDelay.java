package com.concurrentperformance.keithextractor.data;

/**
 * TODO
 * User: Stephen
 */
public class ExtractedDelay {

	private Integer delaySID;
	private Integer minDelay;
	private Integer averageDelay;
	private Integer maxDelay;

	public Integer getDelaySID() {
		return delaySID;
	}

	public void setDelaySID(Integer delaySID) {
		this.delaySID = delaySID;
	}

	public Integer getMinDelay() {
		return minDelay;
	}

	public void setMinDelay(Integer minDelay) {
		this.minDelay = minDelay;
	}

	public Integer getAverageDelay() {
		return averageDelay;
	}

	public void setAverageDelay(Integer averageDelay) {
		this.averageDelay = averageDelay;
	}

	public Integer getMaxDelay() {
		return maxDelay;
	}

	public void setMaxDelay(Integer maxDelay) {
		this.maxDelay = maxDelay;
	}

	@Override
	public String toString() {
		return "ExtractedDelay{" +
				"delaySID=" + delaySID +
				", minDelay=" + minDelay +
				", averageDelay=" + averageDelay +
				", maxDelay=" + maxDelay +
				'}';
	}
}
