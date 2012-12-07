package com.concurrentperformance.keithextractor;

/**
 * Created with IntelliJ IDEA.
 * User: Stephen
 * Date: 04/12/12
 * Time: 08:12
 * To change this template use File | Settings | File Templates.
 */
public class ExtractedArgs {

    private final String fileName;
    private final boolean helpRequired;

    public ExtractedArgs(String fileName, boolean helpRequired) {
        this.fileName = fileName;
        this.helpRequired = helpRequired;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isHelpRequired() {
        return helpRequired;
    }


}
