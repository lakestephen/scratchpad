package com.concurrentperformance.keithextractor;

import com.google.common.base.Preconditions;

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


    public ExtractedArgs buildArgs(final String[] args) {

        Preconditions.checkArgument((args.length >= EXPECTED_MIN_ARG_COUNT && args.length <= EXPECTED_MAX_ARG_COUNT),
                "Expecting a between [" + EXPECTED_MIN_ARG_COUNT + "] and [" + EXPECTED_MAX_ARG_COUNT + "] " +
                        "arguments to passed. Got [" + args.length + "]");

        boolean helpRequired = false;

        String sourceFileName = args[0];
		String destinationFileName = (args.length > 1)?args[1]:null;

        for (String arg : args) {
            if (HELP_REQUIRED_OPTION.equals(arg)) {
                helpRequired = true;
            }
        }


        ExtractedArgs extractedArgs = new ExtractedArgs(sourceFileName, destinationFileName, helpRequired);
        return extractedArgs;
    }

}
