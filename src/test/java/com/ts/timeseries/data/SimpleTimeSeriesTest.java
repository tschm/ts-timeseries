package com.ts.timeseries.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.ts.timeseries.data.SimpleTimeSeries;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleTimeSeriesTest {
    private SimpleTimeSeries ts;

    @Before
    public void setUp() throws Exception {
        ts = new SimpleTimeSeries(ImmutableMap.of(0L,10.0,10L,Double.NaN,5L,20.0));
        System.out.println(ts);
    }

    @Test
    public void testPoints() throws Exception {
        Assert.assertEquals(ts.points(), ImmutableSortedMap.of(0L, 10.0, 5L, 20.0, 10L, Double.NaN));
    }


    @Test
    public void testHash() throws Exception {
        Assert.assertEquals(ts.points().hashCode(), ts.hashCode());
    }
}
