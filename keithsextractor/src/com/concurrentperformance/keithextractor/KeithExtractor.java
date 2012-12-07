package com.concurrentperformance.keithextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeithExtractor {

    private ArgFactory argFactory = new ArgFactory();
    private String lineSep = System.getProperty("line.separator");

	private static final Pattern NUMBER_OF_ON_STATES = Pattern.compile("P_RECEIVESYNC  IN_SYNC(.*)PR_TOTAL.*");


	public void run(String[] rawArgs) {
        try {
            ExtractedArgs args = argFactory.buildArgs(rawArgs);
            if (args.isHelpRequired()) {
                writeUsage();
            }
            String contents = extractFileContents(args.getFileName());
            String[] records = splitIntoRecords(contents);
            System.out.println("Found [" + records.length + "] records");
            extractData(records);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
            System.out.println(Arrays.toString(rawArgs));
            writeUsage();
        }
    }

    private void extractData(String[] records) {
        for (int i=0;i<records.length;i++) {
			ExtractedData extractedData = new ExtractedData();
			Integer numberOfOnStates = extractIntegerFromGroup1(NUMBER_OF_ON_STATES,records[i]);
			extractedData.setNumberOfOnStates(numberOfOnStates);
			System.out.println(extractedData);
        }
    }

	private Integer extractIntegerFromGroup1(Pattern pattern, String record) {
		Integer value = null;
		Matcher matcher = pattern.matcher(record);
		if (matcher.matches()) {
			String extractedGroup1 = matcher.group(1);
			if (extractedGroup1 != null && !extractedGroup1.isEmpty()) {
				value = Integer.parseInt(extractedGroup1);
			}
		}

		return value;
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
            modifiedRecords = new String[records.length -1];
            for (int i=1;i<records.length;i++) {
                modifiedRecords[i-1] = "P_RECEIVESYNC" +
                        records[i].replace("\r", "").replace("\n", "");
            }
        }
        return modifiedRecords;
    }

    private void writeUsage() {
        System.out.println("Extracts Keith's Files");
        System.out.println("KeithExtractorApp <source>");
    }
}
