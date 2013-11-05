package com.ts.timeseries.hdf;

import com.google.common.collect.ImmutableMap;
import com.ts.timeseries.data.SimpleTimeSeries;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DataWriterTest {

    private File tmp;
    private DataWriter writer;

    @Before
    public void setUp() throws Exception {
        tmp = File.createTempFile("testDataWriter",".h5");
        writer = new DataWriter(tmp);
        writer.createGroup("Maffay");
        writer.createGroup("Peter");
        writer.writeSeries("Peter","A", new SimpleTimeSeries(ImmutableMap.of(0L, 10.0, 20L, 30.0, 40L, 15.0)));
    }

    @After
    public void tearDown() throws Exception {
        writer.close();
        tmp.delete();
    }

    @Test
    public void testOverwrite1() throws Exception {
        writer.writeSeries("Peter", "A", new SimpleTimeSeries(ImmutableMap.of(20L, 60.0)));
        Assert.assertEquals(writer.readSeries("Peter/A").points().get(20L), 30.0, 1e-10);
    }

    @Test
    public void testOverwrite2() throws Exception {
        writer.writeSeries("Peter", "A", new SimpleTimeSeries(ImmutableMap.of(20L, 60.0, 50L, 100.0)));
        Assert.assertEquals(writer.readSeries("Peter/A").points().get(20L), 30.0, 1e-10);
        Assert.assertEquals(writer.readSeries("Peter/A").points().get(50L), 100.0, 1e-10);
    }
}
