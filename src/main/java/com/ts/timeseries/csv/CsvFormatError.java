package com.ts.timeseries.csv;

/**
* RuntimeException for problems with *.csv files
*/
public final class CsvFormatError extends RuntimeException {
    private static final long serialVersionUID = 1688285902893920643L;

    /**
     * @param message message for logging purpose, etc.
     */
    public CsvFormatError(String message) {
        super(message);
    }
}
