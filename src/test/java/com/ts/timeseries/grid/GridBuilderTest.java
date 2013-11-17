package com.ts.timeseries.grid;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.unit.LangAssert;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.SortedMap;
import java.util.SortedSet;


public class GridBuilderTest {

    @Test
    public void testConstructor() throws Exception {
        LangAssert.assertUtilityClass(GridBuilder.class);
    }

    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = GridBuilder.buildGrid("18h", new DateTime(2010,2,1,14,0,0), new DateTime(2012,2,1,14,0,0));
    }

    @Test
    public void testTolerance() throws Exception {
        Assert.assertEquals(grid.tolerance().longValue(), 64800000L);
    }

    @Test
    public void testTimegrid() throws Exception {
        Assert.assertEquals(grid.timegrid().size(), 521);
    }

    @Test
    public void testFit() throws Exception {

        TimeSeries t = new TimeSeries() {
            @Override
            public SortedMap<Long, Double> points() {
                return ImmutableSortedMap.of(5L, -5.0, 20L, 30.0, 40L, 20.0);
            }
        };

        Grid g = new Grid() {


            @Override
            public SortedSet<Long> timegrid() {
                return ImmutableSortedSet.of(0L, 8L, 16L, 24L, 42L);
            }

            @Override
            public Long tolerance() {
                return 4L;
            }
        };

        Assert.assertEquals(GridBuilder.fitToGrid(t, g).points().get(24L), 30.0, 1e-10);


    }
}
