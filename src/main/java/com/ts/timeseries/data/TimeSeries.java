package com.ts.timeseries.data;

import java.util.SortedMap;

/**
 * TimeSeries
 */
public interface TimeSeries {
    /**
     * @return Map of any values at certain time stamps. Time stamp are Java time stamps in ms.
     */
    SortedMap<Long, Double> points();
}
