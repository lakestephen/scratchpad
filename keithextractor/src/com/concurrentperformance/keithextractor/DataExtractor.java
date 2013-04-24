package com.concurrentperformance.keithextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.concurrentperformance.keithextractor.data.ExtractedData;
import com.concurrentperformance.keithextractor.data.ExtractedDelay;

public class DataExtractor {

	private static String lineSep = System.getProperty("line.separator");

	private static final Pattern NUMBER_OF_ON_STATES = Pattern.compile("P_RECEIVESYNC  IN_SYNC(.*)PR_TOTAL");
	private static final Pattern TOTAL_PACKETS_RECEIVED = Pattern.compile("PR_TOTAL  \\d* \\d* \\d* (\\d*)");
	private static final Pattern DELAY = Pattern.compile("PR_TPLDLATENCY  \\[(\\d*)\\]  (\\d*) (\\d*) (\\d*)");
	private static final Pattern PACKETS_SENT = Pattern.compile("PT_STREAM  \\[0\\]  0 0 \\d* (\\d*)");

	public ExtractedData[] extractData(String fileName) throws IOException {

		String contents = extractFileContents(fileName);
		String[] records = splitIntoRecords(contents);
		ExtractedData[] extractedData = extractDataFromRecords(records);

		return extractedData;
	}

	private ExtractedData[] extractDataFromRecords(String[] records) {
		ExtractedData[] extractedData = new ExtractedData[records.length];
		for (int i = 0; i < records.length; i++) {
			extractedData[i] = new ExtractedData();

			Integer numberOfOnStates = extractIntegerFromGroup(NUMBER_OF_ON_STATES, records[i], 1, 1);
			extractedData[i].setNumberOfOnStates(numberOfOnStates);

			Integer totalPacketsReceived = extractIntegerFromGroup(TOTAL_PACKETS_RECEIVED, records[i], 1, 1);
			extractedData[i].setTotalPacketsReceived(totalPacketsReceived);

			List<ExtractedDelay> extractedDelays = getExtractedDelays(records[i]);
			extractedData[i].addDelay(extractedDelays);

			Integer port0PacketsSent = extractIntegerFromGroup(PACKETS_SENT, records[i], 1, 1);
			extractedData[i].setPort0PacketsSent(port0PacketsSent);

			Integer port1PacketsSent = extractIntegerFromGroup(PACKETS_SENT, records[i], 2, 1);
			extractedData[i].setPort1PacketsSent(port1PacketsSent);

			System.out.print("[" + i + "] = ");
			System.out.println(extractedData[i]);
		}
		return extractedData;
	}

	private String extractFileContents(String fileName) throws IOException {
		File file = new File(fileName);
		System.out.println("Extracting from [" + file.getCanonicalPath() + "]");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			builder.append(line);
			builder.append(lineSep);
		}

		return builder.toString();
	}

	private String[] splitIntoRecords(String contents) {
		String[] records = contents.split("P_RECEIVESYNC");
		String[] modifiedRecords = null;
		System.out.println("Found [" + records.length + "] records");

		if (records.length > 0) {
			modifiedRecords = new String[records.length - 1];
			for (int i = 1; i < records.length; i++) {
				modifiedRecords[i - 1] = "P_RECEIVESYNC" +
						records[i].replace("\r", "").replace("\n", "");
			}
		}

		return modifiedRecords;
	}

	private List<ExtractedDelay> getExtractedDelays(String record) {

		List<ExtractedDelay> delays = new ArrayList<ExtractedDelay>();

		int iteration = 1;
		while (true) {
			Integer delaySID = extractIntegerFromGroup(DELAY, record, iteration, 1);
			if (delaySID == null) {
				break;
			}
			ExtractedDelay delay = new ExtractedDelay();

			delay.setDelaySID(delaySID);

			Integer minDelay = extractIntegerFromGroup(DELAY, record, iteration, 2);
			delay.setMinDelay(minDelay);

			Integer averageDelay = extractIntegerFromGroup(DELAY, record, iteration, 3);
			delay.setAverageDelay(averageDelay);

			Integer maxDelay = extractIntegerFromGroup(DELAY, record, iteration, 4);
			delay.setMaxDelay(maxDelay);

			delays.add(delay);

			iteration++;
		}
		return delays;
	}

	private Integer extractIntegerFromGroup(Pattern pattern, String record, int findIteration, int group) {
		Integer value = null;
		Matcher matcher = pattern.matcher(record);
		while (matcher.find()) {
			if (findIteration > 1) {
				findIteration--;
			} else {
				String extractedGroup = matcher.group(group);
				if (extractedGroup != null && !extractedGroup.isEmpty()) {
					value = Integer.parseInt(extractedGroup);
				}
				break;
			}
		}

		return value;
	}
}