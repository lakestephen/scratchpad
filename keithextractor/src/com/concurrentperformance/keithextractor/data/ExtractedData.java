package com.concurrentperformance.keithextractor.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Stephen
 */
public class ExtractedData {

	private Map<Integer,ExtractedDelay> extractedDelays = new HashMap<Integer, ExtractedDelay>();
	private Integer numberOfOnStates;
	private Integer totalPacketsReceived;
	private Integer port0PacketsSent;
	private Integer port1PacketsSent;

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

	public void addDelay(List<ExtractedDelay> delays) {
		for (ExtractedDelay delay : delays) {
			extractedDelays.put(delay.getDelaySID(),delay);
		}
	}

	public Map<Integer, ExtractedDelay> getExtractedDelays() {
		return extractedDelays;
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
		if (port0PacketsSent == null || port1PacketsSent == null) {
			return null;
		}
		return port0PacketsSent.intValue() + port1PacketsSent.intValue();
	}

	public Integer getPacketLoss() {
		if (getTotalPacketsSent() == null || getTotalPacketsReceived() == null) {
			return null;
		}
		return getTotalPacketsSent().intValue() - getTotalPacketsReceived().intValue();
	}

	public Double getMeasuredPLP() {
		if (getPacketLoss() == null || getTotalPacketsSent() == null) {
			return null;
		}
		int packetloss = getPacketLoss().intValue();
		int totalPacketsSent = getTotalPacketsSent().intValue();
		return (double)packetloss/(double)totalPacketsSent;
	}

	@Override
	public String toString() {
		return "ExtractedData{" +
				"numberOfOnStates=" + numberOfOnStates +
				", totalPacketsReceived=" + totalPacketsReceived +
				", extractedDelays=" + extractedDelays +
				", port0PacketsSent=" + port0PacketsSent +
				", port1PacketsSent=" + port1PacketsSent +
				'}';
	}
}
