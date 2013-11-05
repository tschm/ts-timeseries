package com.ts.timeseries.hdf;

import com.ts.timeseries.data.TimeSeries;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


final class SimpleHDFSeries implements TimeSeries {

    private final SortedMap<Long, Double> map = new TreeMap<Long, Double>();

    SimpleHDFSeries(Measurement[] measurements)
    {
        for (Measurement m : measurements)
            map.put(m.time, m.data);
    }

    SimpleHDFSeries(Map<Long, Double> ts)
    {
        map.putAll(ts);
    }

    Measurement[] getMeasurementArray()
    {
        Measurement[] m = new Measurement[this.points().size()];
        int i = 0;
        for (Long time : this.points().keySet())
        {
            m[i++] = new Measurement(time, this.points().get(time));
        }

        return m;
    }

    @Override
    public SortedMap<Long, Double> points() {
        return map;
    }
}
