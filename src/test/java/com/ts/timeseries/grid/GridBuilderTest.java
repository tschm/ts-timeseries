package com.ts.timeseries.grid;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class GridBuilderTest {

    private static final File testDir = new File("src//test//resources//");
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
}
