package com.ts.timeseries.matlab;


import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * MatlabTimeSeries
 */
public final class MatlabTimeSeries implements TimeSeries, MatlabData {
    private final TimeSeries timeSeries;

    /**
     * Constructor using existing time series
     *
     * @param timeSeries
     */
    public MatlabTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

    /**
     * Constructor using arrays
     *
     * @param time time vector
     * @param data data vector
     */
    public MatlabTimeSeries(double[][] time, double[] data) {
        if (!(time.length==data.length))
            throw new IllegalArgumentException("Data and Time mismatch in length");

        final SortedMap<Long,Double> map = new TreeMap<Long,Double>();

        for (int i = 0; i < time.length; ++i) {
            map.put(MatlabUtils.getDateStamp(time[i]), data[i]);
        }

        this.timeSeries = new SimpleTimeSeries(map);
    }

    @Override
    public double[] getData() {
        return Primitives.convertDouble(timeSeries.points().values());
    }

    @Override
    public double[][] getTimeMatrix() {
        return MatlabUtils.getDateArray(timeSeries.points().keySet());
    }

    @Override
    public SortedMap<Long,Double> points() {
        return this.timeSeries.points();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatlabTimeSeries that = (MatlabTimeSeries) o;

        if (timeSeries != null ? !timeSeries.equals(that.timeSeries) : that.timeSeries != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return timeSeries != null ? timeSeries.hashCode() : 0;
    }

    @Override
    public String toString() {
        String s = "";
        for (Long time : timeSeries.points().keySet())
        {
            s += Arrays.toString(MatlabUtils.getDateArray(time)) + " " + timeSeries.points().get(time).toString() + "\n";
        }
        return s;
    }
}
