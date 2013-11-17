package com.ts.timeseries.matlab;

import com.google.common.collect.ImmutableSortedMap;
import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;
import org.junit.Assert;
import org.junit.Test;

public class MatlabTimeSeriesTest {

    private static TimeSeries x = new SimpleTimeSeries(ImmutableSortedMap.of(0L, 1.0, 1000L, 2.0, 2000L, 5.0));

    @Test
    public void testGetData() throws Exception {
        Assert.assertArrayEquals(new MatlabTimeSeries(x).getData(), new double[]{1.0, 2.0, 5.0}, 1e-10);
    }

    @Test
    public void testPoints() throws Exception {
        Assert.assertEquals(new MatlabTimeSeries(x).points(), ImmutableSortedMap.of(0L, 1.0, 1000L, 2.0, 2000L, 5.0));
    }

    @Test
    public void testConstruct() throws Exception {
        MatlabTimeSeries x2 = new MatlabTimeSeries(new MatlabTimeSeries(x).getTimeMatrix(), new MatlabTimeSeries(x).getData());
        System.out.println(x2);
        Assert.assertEquals(new MatlabTimeSeries(x),x2);
        Assert.assertEquals(x2.hashCode(), x2.points().hashCode());

    }
}
