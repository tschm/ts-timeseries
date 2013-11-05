package com.ts.timeseries.matlab;


import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Matlab, helps to construct more complicated datatypes
 */
public final class Datatypes {
    private Datatypes() {
        throw new AssertionError();
    }

    public static List<String> getStringList() {
        return new ArrayList<String>();
    }
}
