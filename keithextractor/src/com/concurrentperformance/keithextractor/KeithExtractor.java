package com.concurrentperformance.keithextractor;

import java.util.Arrays;

import com.concurrentperformance.keithextractor.args.ArgFactory;
import com.concurrentperformance.keithextractor.args.Args;
import com.concurrentperformance.keithextractor.data.ExtractedData;

public class KeithExtractor {

	private ArgFactory argFactory = new ArgFactory();

	public void run(String[] rawArgs) {
		try {
			Args args = argFactory.buildArgs(rawArgs);
			if (args.isHelpRequired()) {
				writeUsage();
			}

			String destFileName = getDestinationFileName(args);
			String sourceFileName = args.getSourceFileName();

			ExtractedData[] data = new DataExtractor().extractData(sourceFileName);
			new DataWriter().writeData(data, destFileName);

		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
			System.out.println(Arrays.toString(rawArgs));
			writeUsage();
		}
	}

	private String getDestinationFileName(Args extractedArgs) {
		String destinationFileName = extractedArgs.getDestinationFileName();
		if (destinationFileName == null || destinationFileName.isEmpty()) {
			destinationFileName = extractedArgs.getSourceFileName() + ".extracted.csv";
		}
		return destinationFileName;
	}

	private void writeUsage() {
		System.out.println("Extracts Keith's Files");
		System.out.println("KeithExtractorApp <source> <destination>");
	}
}
