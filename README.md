## Motivation
We provide tools to work with time series data in Java.

## Data import from external sources
Program tools against this interface to load data into the ts-cta framework:

```java
public interface InputData {
    /**
     * read time series
     *
     * @param name name of the time series
     * @return the time series
     */
    TimeSeries timeSeries(String name);

    /**
     * read set of names
     *
     * @return Set of all feasible names
     */
    Set<String> names();
}

public interface TimeSeries
{
    /**
     * @return Map of values at certain time stamps.
     *
     * Time stamp are Java time stamps in ms.
     */
    SortedMap<Long, Double> points();
}

```

We provide such implementations for hdf and csv files

## Matlab support
Matlab is dubious with dates. We provide some help in the Matlab package.

## Matrices
Time series data are the columns of a matrix

```java
package com.ts.timeseries.matrix;

import com.ts.timeseries.data.TimeSeries;

...

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

    /**
     * write matrix to hdf file, use "data" as a group
     *
     * @param file file
     */
    void to_hdf(File file);
}
```

## Operators
We provide various functions to map and analyze time series data and matrices.

## Availability
This package is available in the Maven repository of the author.


   
    
