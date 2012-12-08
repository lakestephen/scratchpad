package com.concurrentperformance.keithextractor;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParameterList;

import javax.print.attribute.Size2DSyntax;

/**
 * TODO
 *
 * @author Stephen
 */
public class ExtractedData {

	private Integer numberOfOnStates;
	private Integer totalPacketsReceived;
	private Integer minDelay;
	private Integer averageDelay;
	private Integer maxDelay;
	private Integer port0PacketsSent;
	private Integer port1PacketsSent;
	private ParameterList totalPacketsSent;
	private Size2DSyntax packetLoss;

	public Integer getNumberOfOnStates() {
		return numberOfOnStates;
	}

	public void setNumberOfOnStates(Integer numberOfOnStates) {
		this.numberOfOnStates = numberOfOnStates;
	}

	public Integer getTotalPacketsReceived() {
		return totalPacketsReceived;
	}

	public void setTotalPacketsReceived(Integer totalPacketsReceived) {
		this.totalPacketsReceived = totalPacketsReceived;
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

	public Integer getPort0PacketsSent() {
		return port0PacketsSent;
	}

	public void setPort0PacketsSent(Integer port0PacketsSent) {
		this.port0PacketsSent = port0PacketsSent;
	}

	public Integer getPort1PacketsSent() {
		return port1PacketsSent;
	}

	public void setPort1PacketsSent(Integer port1PacketsSent) {
		this.port1PacketsSent = port1PacketsSent;
	}

	public Integer getTotalPacketsSent() {
		return port0PacketsSent.intValue() + port1PacketsSent.intValue();
	}

	public Integer getPacketLoss() {
		return getTotalPacketsSent().intValue() - getTotalPacketsReceived().intValue();
	}

	public Double getMeasuredPLP() {
		int packetloss = getPacketLoss().intValue();
		int totalPacketsSent = getTotalPacketsSent().intValue();
		return (double)packetloss/(double)totalPacketsSent;
	}

	@Override
	public String toString() {
		return "ExtractedData{" +
				"numberOfOnStates=" + numberOfOnStates +
				", totalPacketsReceived=" + totalPacketsReceived +
				", minDelay=" + minDelay +
				", averageDelay=" + averageDelay +
				", maxDelay=" + maxDelay +
				", port0PacketsSent=" + port0PacketsSent +
				", port1PacketsSent=" + port1PacketsSent +
				'}';
	}
}
