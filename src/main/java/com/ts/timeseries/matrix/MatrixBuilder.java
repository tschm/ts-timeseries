package com.ts.timeseries.matrix;

import com.ts.timeseries.data.TimeSeries;

import java.io.File;
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
        SortedSet<String> columns = new TreeSet<>();
        columns.addAll(assets);
        return new SimpleMatrix(grid, columns);
    }


    public static Matrix build(Set<String> symbols) {
        return new SimpleMatrix(symbols);

    }
}
