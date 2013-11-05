package com.ts.timeseries.matrix;

import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.hdf.DataReader;
import ncsa.hdf.hdf5lib.exceptions.HDF5JavaException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * MatrixBuilder
 */
public final class MatrixBuilder {

    private MatrixBuilder() {
        throw new AssertionError();
    }

    /**
     * Builder for DataControl
     *
     * @param assets List of assets
     * @param grid Grid
     * @return DataControl
     */
    public static Matrix build(Collection<String> assets, SortedSet<Long> grid) {
        SortedSet<String> columns = new TreeSet<String>();
        columns.addAll(assets);
        return new SimpleMatrix(grid, columns);
    }

    public static Matrix importHdfMatrix(File file, String group) throws HDF5JavaException {
        DataReader reader = new DataReader(file);

        Set<String> columns = reader.readSeriesNames(group);

        SimpleMatrix matrix = new SimpleMatrix(columns);

        for (String column : columns)
        {
            TimeSeries ts = reader.readSeries(group + "/" + column);
            matrix.setTimeSeries(column, ts);
        }

        return matrix;
    }


    public static Matrix build(Set<String> symbols) {
        return new SimpleMatrix(symbols);

    }
}
