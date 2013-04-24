package com.concurrentperformance.keithextractor.args;

/**
 * Created with IntelliJ IDEA.
 * User: Stephen
 * Date: 04/12/12
 * Time: 08:12
 * To change this template use File | Settings | File Templates.
 */
public class Args {

    private final String sourceFileName;
	private final String destinationFileName;
    private final boolean helpRequired;

    public Args(String sourceFileName, String destinationFileName, boolean helpRequired) {
        this.sourceFileName = sourceFileName;
		this.destinationFileName = destinationFileName;
        this.helpRequired = helpRequired;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public String getDestinationFileName() {
        return destinationFileName;
    }

    public boolean isHelpRequired() {
        return helpRequired;
    }
}
