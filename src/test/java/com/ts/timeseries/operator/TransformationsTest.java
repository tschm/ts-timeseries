package com.ts.timeseries.operator;

import com.google.common.collect.ImmutableSortedMap;
import com.ts.timeseries.data.TimeSeries;
import org.junit.Assert;
import org.junit.Test;

import java.util.SortedMap;


public class TransformationsTest {

    @Test
    public void testEwma() throws Exception {
        TimeSeries x = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(0L, 2.0, 1L, Double.NaN, 2L, 3.0);
            }
        };

        TimeSeries r = Transformations.ewma(x,2);
        // 2.0   and  (0.5 * 2.0 + 3.0)/(0.5 * 1.0 + 1.0)
        Assert.assertEquals(r.points(), ImmutableSortedMap.of(0L, 2.0, 2L, 4.0/1.5));
    }

    @Test
    public void testEwmstd() throws Exception {
        TimeSeries x = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(0L, 2.0, 1L, Double.NaN, 2L, 3.0);
            }
        };

        TimeSeries r = Transformations.ewmstd(x,2);
        Assert.assertEquals(r.points(), ImmutableSortedMap.of(0L, 0.0, 2L, 0.2721655269759088));
    }

    @Test
    public void testDropna() throws Exception {
        TimeSeries x = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(0L, 2.0, 1L, Double.NaN, 2L, 3.0);
            }
        };

        TimeSeries r = Transformations.dropna(x);
        Assert.assertEquals(r.points(), ImmutableSortedMap.of(0L, 2.0, 2L, 3.0));
    }

    @Test
    public void testCumsum() throws Exception {
        TimeSeries x = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(0L, 2.0, 1L, Double.NaN, 2L, 3.0);
            }
        };

        TimeSeries r = Transformations.cumsum(x);
        Assert.assertEquals(r.points(), ImmutableSortedMap.of(0L, 2.0, 2L, 5.0));
    }

    @Test
    public void testSqrt() throws Exception {
        TimeSeries x = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(0L, 4.0, 1L, Double.NaN, 2L, 9.0);
            }
        };

        TimeSeries r = Transformations.sqrt(x);
        Assert.assertEquals(r.points(), ImmutableSortedMap.of(0L, 2.0, 2L, 3.0));
    }
}
