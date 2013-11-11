package com.ts.timeseries.data;

import java.util.Set;

public interface InputData {
    /**
     * readColumns time series
     *
     * @param name name of the time series
     * @return the time series
     */
    TimeSeries timeSeries(String name);

    /**
     * readColumns set of names
     *
     * @return Set of all feasible names
     */
    Set<String> names();
}
