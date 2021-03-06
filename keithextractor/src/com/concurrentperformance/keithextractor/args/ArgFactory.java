package com.concurrentperformance.keithextractor.args;

/**
 * Created with IntelliJ IDEA.
 * User: Stephen
 * Date: 04/12/12
 * Time: 08:14
 * To change this template use File | Settings | File Templates.
 */
public class ArgFactory {

	private static final int EXPECTED_MIN_ARG_COUNT = 1;
	private static final int EXPECTED_MAX_ARG_COUNT = 2;
	private static final String HELP_REQUIRED_OPTION = "\\?";


	public Args buildArgs(final String[] args) {

		if (args.length < EXPECTED_MIN_ARG_COUNT || args.length > EXPECTED_MAX_ARG_COUNT) {
			String msg = "Expecting a between [" + EXPECTED_MIN_ARG_COUNT + "] and [" + EXPECTED_MAX_ARG_COUNT + "] " +
					"arguments to passed. Got [" + args.length + "]";
			throw new IllegalArgumentException(msg);
		}

		boolean helpRequired = false;

		String sourceFileName = args[0];
		String destinationFileName = (args.length > 1) ? args[1] : null;

		for (String arg : args) {
			if (HELP_REQUIRED_OPTION.equals(arg)) {
				helpRequired = true;
			}
		}


		Args extractedArgs = new Args(sourceFileName, destinationFileName, helpRequired);
		return extractedArgs;
	}

}
