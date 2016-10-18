package com.ts.timeseries.matrix;

import com.ts.timeseries.data.TimeSeries;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Matrix interface
 */
public interface Matrix {
    /**
     * Read a scalar
     */
    double readScalar(String name, Long time);

    /**
     * Write a scalar
     */
    void writeScalar(String name, Long time, double scalar);

    /**
     * Read a vector
     */
    SortedMap<String, Double> readVector(Long time);

    /**
     * Write a vector
     *
     * @param data the vector
     */
    void writeVector(Long time, Map<String, Double> data);

    /**
     * @return set of columnNames
     */
    Set<String> columns();

    /**
     * set a column in matrix
     *
     * @param columnName name of the column
     * @param timeSeries time series in the column
     */
    void setTimeSeries(String columnName, TimeSeries timeSeries);

    /**
     * get a column in matrix
     *
     * @param columnName name of the column
     * @return time series in column
     */
    TimeSeries getTimeSeries(String columnName);

    /**
     * time grid used by matrix
     *
     * @return time grid
     */
    SortedSet<Long> timegrid();
}
