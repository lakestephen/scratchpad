package com.concurrentperformance.keithextractor;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeithExtractor {

	private ArgFactory argFactory = new ArgFactory();
	private String lineSep = System.getProperty("line.separator");

	private static final Pattern NUMBER_OF_ON_STATES = Pattern.compile("P_RECEIVESYNC  IN_SYNC(.*)PR_TOTAL");
	private static final Pattern TOTAL_PACKETS_RECEIVED = Pattern.compile("PR_TOTAL  \\d \\d \\d* (\\d*)");
	private static final Pattern DELAY = Pattern.compile("PR_TPLDLATENCY  \\[3\\]  (\\d*) (\\d*) (\\d*)");
	private static final Pattern PACKETS_SENT = Pattern.compile("PT_STREAM  \\[0\\]  0 0 \\d* (\\d*)");


	public void run(String[] rawArgs) {
		try {
			ExtractedArgs args = argFactory.buildArgs(rawArgs);
			if (args.isHelpRequired()) {
				writeUsage();
			}
			String contents = extractFileContents(args.getSourceFileName());
			String[] records = splitIntoRecords(contents);
			System.out.println("Found [" + records.length + "] records");
			ExtractedData[] data = extractData(records);
			String destFileName = getDestinationFileName(args);
			writeData(data, destFileName);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
			System.out.println(Arrays.toString(rawArgs));
			writeUsage();
		}
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

		if (records.length > 0) {
			modifiedRecords = new String[records.length - 1];
			for (int i = 1; i < records.length; i++) {
				modifiedRecords[i - 1] = "P_RECEIVESYNC" +
						records[i].replace("\r", "").replace("\n", "");
			}
		}
		return modifiedRecords;
	}

	private ExtractedData[] extractData(String[] records) {
		ExtractedData[] extractedData = new ExtractedData[records.length];
		for (int i = 0; i < records.length; i++) {
			extractedData[i] = new ExtractedData();

			Integer numberOfOnStates = extractIntegerFromGroup(NUMBER_OF_ON_STATES, records[i], 1, 1);
			extractedData[i].setNumberOfOnStates(numberOfOnStates);

			Integer totalPacketsReceived = extractIntegerFromGroup(TOTAL_PACKETS_RECEIVED, records[i], 1, 1);
			extractedData[i].setTotalPacketsReceived(totalPacketsReceived);

			Integer minDelay = extractIntegerFromGroup(DELAY, records[i], 1, 1);
			extractedData[i].setMinDelay(minDelay);

			Integer averageDelay = extractIntegerFromGroup(DELAY, records[i], 1, 2);
			extractedData[i].setAverageDelay(averageDelay);

			Integer maxDelay = extractIntegerFromGroup(DELAY, records[i], 1, 3);
			extractedData[i].setMaxDelay(maxDelay);

			Integer port0PacketsSent = extractIntegerFromGroup(PACKETS_SENT, records[i], 1, 1);
			extractedData[i].setPort0PacketsSent(port0PacketsSent);

			Integer port1PacketsSent = extractIntegerFromGroup(PACKETS_SENT, records[i], 2, 1);
			extractedData[i].setPort1PacketsSent(port1PacketsSent);

			System.out.print("[" + i + "] = ");
			System.out.println(extractedData[i]);
		}

		return extractedData;
	}

	private String getDestinationFileName(ExtractedArgs extractedArgs) {
		String destinationFileName = extractedArgs.getDestinationFileName();
		if (destinationFileName == null || destinationFileName.isEmpty()) {
			destinationFileName = extractedArgs.getSourceFileName() + ".extracted.csv";
		}
		return destinationFileName;
	}

	private void writeData(ExtractedData[] data, String fileName) throws IOException {
		File file = new File(fileName);
		System.out.println("Writing results to [" + file.getCanonicalPath() + "]");

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Port 0 Packets Sent,");	// #1
		bw.write("Port 1 Packets Sent,");	// #2
		bw.write("Total Packets Sent,");	// #3 = #1 + #2
		bw.write("Total Packets Received,");// #4
		bw.write("Packet Loss,");			// #5 = #3 - #4
		bw.write("Measured PLP,");			// #6 = #5 / #3
		bw.write("Min Delay,");				// #7
		bw.write("Average Delay,");			// #8
		bw.write("Max Delay,");				// #9
		bw.write("Number of On States,");	// #10
		bw.write(lineSep);

		for (ExtractedData extractedData : data) {
			bw.write(extractedData.getPort0PacketsSent().toString());		// #1
			bw.write(",");
			bw.write(extractedData.getPort1PacketsSent().toString());		// #2
			bw.write(",");
			bw.write(extractedData.getTotalPacketsSent().toString());		// #3 = #1 + #2
			bw.write(",");
			bw.write(extractedData.getTotalPacketsReceived().toString());	// #4
			bw.write(",");
			bw.write(extractedData.getPacketLoss().toString());				// #5 = #3 - #4
			bw.write(",");
			bw.write(extractedData.getMeasuredPLP().toString());			// #6 = #5 / #3
			bw.write(",");
			bw.write(extractedData.getMinDelay().toString());				// #7
			bw.write(",");
			bw.write(extractedData.getAverageDelay().toString());			// #8
			bw.write(",");
			bw.write(extractedData.getMaxDelay().toString());				// #9
			bw.write(",");
			bw.write((extractedData.getNumberOfOnStates()==null)?"":extractedData.getNumberOfOnStates().toString());		// #10
			bw.write(lineSep);
		}

		bw.close();
		fw.close();
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

	private void writeUsage() {
		System.out.println("Extracts Keith's Files");
		System.out.println("KeithExtractorApp <source> <destination>");
	}
}
