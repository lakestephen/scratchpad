package com.concurrentperformance.keithextractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.concurrentperformance.keithextractor.data.ExtractedData;
import com.concurrentperformance.keithextractor.data.ExtractedDelay;

/**
 * TODO
 * User: Stephen
 */
public class DataWriter {

	private String lineSep = System.getProperty("line.separator");

	public void writeData(ExtractedData[] data, String fileName) throws IOException {
		FileWriter fw = getFile(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		List<Integer> delaySIDs = getDelaySIDSuperSet(data);
		writeHeaders(delaySIDs, bw);
		writeBody(data, bw, delaySIDs);

		bw.close();
		fw.close();
	}

	private void writeBody(ExtractedData[] data, BufferedWriter bw, List<Integer> delaySIDs) throws IOException {
		for (ExtractedData extractedData : data) {
			writeNullSafeValueAndSeparator(bw, extractedData.getPort0PacketsSent());    // #1
			writeNullSafeValueAndSeparator(bw, extractedData.getPort1PacketsSent());    // #2
			writeNullSafeValueAndSeparator(bw, extractedData.getTotalPacketsSent());    // #3 = #1 + #2
			writeNullSafeValueAndSeparator(bw, extractedData.getTotalPacketsReceived());// #4
			writeNullSafeValueAndSeparator(bw, extractedData.getPacketLoss());          // #5 = #3 - #4
			writeNullSafeValueAndSeparator(bw, extractedData.getMeasuredPLP());         // #6 = #5 / #3
			for (Integer delaySID : delaySIDs) {
				ExtractedDelay delayDetails = extractedData.getExtractedDelays().get(delaySID);
				writeNullSafeValueAndSeparator(bw, delayDetails.getMinDelay());            // #7
				writeNullSafeValueAndSeparator(bw, delayDetails.getAverageDelay());        // #8
				writeNullSafeValueAndSeparator(bw, delayDetails.getMaxDelay());            // #9
			}

			writeNullSafeValueAndSeparator(bw, extractedData.getNumberOfOnStates());    // #10
			bw.write(lineSep);
		}
	}

	private void writeHeaders(List<Integer> delaySIDs, BufferedWriter bw) throws IOException {
		writeNullSafeValueAndSeparator(bw, "Port 0 Packets Sent");     // #1
		writeNullSafeValueAndSeparator(bw, "Port 1 Packets Sent");     // #2
		writeNullSafeValueAndSeparator(bw, "Total Packets Sent");      // #3 = #1 + #2
		writeNullSafeValueAndSeparator(bw, "Total Packets Received");  // #4
		writeNullSafeValueAndSeparator(bw, "Packet Loss");             // #5 = #3 - #4
		writeNullSafeValueAndSeparator(bw, "Measured PLP");            // #6 = #5 / #3
		for (Integer delaySID : delaySIDs) {
			writeNullSafeValueAndSeparator(bw, "Min Delay (" + delaySID + ")");               // #7
			writeNullSafeValueAndSeparator(bw, "Average Delay (" + delaySID + ")");           // #8
			writeNullSafeValueAndSeparator(bw, "Max Delay (" + delaySID + ")");               // #9
		}
		writeNullSafeValueAndSeparator(bw, "Number of On States");     // #10
		bw.write(lineSep);
	}

	private FileWriter getFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file);
		System.out.println("Writing results to [" + file.getCanonicalPath() + "]");
		return fw;
	}

	private List<Integer> getDelaySIDSuperSet(ExtractedData[] data) {
		Set<Integer> delaySIDs = new TreeSet<Integer>();

		for (ExtractedData extractedData : data) {
			Set<Integer> delaySIDForData = extractedData.getExtractedDelays().keySet();
			for (Integer delaySID : delaySIDForData) {
				delaySIDs.add(delaySID);
			}
		}

		return new ArrayList<Integer>(delaySIDs);
	}

	private void writeNullSafeValueAndSeparator(BufferedWriter bw, Object value) throws IOException {
		bw.write((value== null)?"":value.toString());
		bw.write(",");
	}

}
