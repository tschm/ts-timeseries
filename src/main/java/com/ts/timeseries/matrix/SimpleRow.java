package com.ts.timeseries.matrix;

import java.util.Arrays;

final class SimpleRow {
    public final double[] values;

    SimpleRow(int size) {
        values = new double[size];
        Arrays.fill(values, Double.NaN);
    }
}
