package com.ts.timeseries.data;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class SimpleTimeSeries implements TimeSeries {
    private SortedMap<Long, Double> series = new TreeMap<Long, Double>();

    public SimpleTimeSeries(Map<Long, Double> data) {
        this.series.putAll(data);
    }

    public SimpleTimeSeries(List<Long> dates, List<Double> values) {
        SortedMap<Long, Double> mapTimeSeries = new TreeMap<Long, Double>();
        for (int i = 0; i < values.size(); ++i)
        {
            mapTimeSeries.put(dates.get(i), values.get(i));
        }

        this.series = mapTimeSeries;
    }

    @Override
    public SortedMap<Long, Double> points()
    {
        return this.series;
    }

    @Override
    public String toString() {
        String s="";
        for (Long key : this.series.keySet())
        {
            s += new DateTime(key).toString("yyyyMMdd-HHmmss") + ": " + this.series.get(key).toString() + "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTimeSeries that = (SimpleTimeSeries) o;

        return !(series != null ? !series.equals(that.series) : that.series != null);

    }

    @Override
    public int hashCode() {
        return series != null ? series.hashCode() : 0;
    }
}
