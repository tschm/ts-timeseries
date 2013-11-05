package com.ts.timeseries.hdf;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.data.TimeSeries;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DataReaderTest {
    private DataReader reader;

    @Before
    public void setUp() throws Exception {
        File file = new File("src//test//resources//data//test.h5");
        reader = new DataReader(file);
    }

    @After
    public void tearDown() throws Exception {
        reader.close();
    }
    @Test
    public void testGroups() throws Exception {
        Assert.assertEquals(reader.readGroupNames(), ImmutableSet.of("Peter"));
    }

    @Test
    public void testSeries() throws Exception {
        Assert.assertEquals(reader.readSeriesNames("Peter"), ImmutableSet.of("meat","wurst"));
    }

    @Test
    public void testNames() throws  Exception {
        Assert.assertEquals(reader.readNames(), ImmutableSet.of("Peter/meat", "Peter/wurst"));
    }

    @Test
    public void testGetSeries() throws Exception {
        TimeSeries x = reader.readSeries("Peter/meat");
        Assert.assertEquals(x.points(), ImmutableMap.of(1L,20.0,2L,80.0,3L,50.0));
    }
}
