package com.ts.timeseries.csv;

import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.TimeSeries;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class CsvInputDataTest {

    private static final File testDir = new File("src//test//resources//data//");

    @Test
    public void testTimeSeries1() throws Exception {
        File file = new File("src//test//resources//data//tsData.csv");
        InputData x = new CsvInputData(file, "yyyyMMdd");
        Assert.assertEquals(x.names(), ImmutableSet.of("A", "B"));
        TimeSeries y = x.timeSeries("A");
        Assert.assertEquals(y.points().size(),3);

    }

    @Test
    public void testTimeSeries2() throws Exception {
        InputData data = new CsvInputData(new File(testDir, "matrix.csv"), "yyyyMMddHHmmss");
        Assert.assertEquals(data.names().size(), 57);
        TimeSeries ts = data.timeSeries("FTSE-price adjusted");
        Assert.assertEquals(ts.points().size(), 2263);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeSeriesWrongName() throws Exception {
        InputData data = new CsvInputData(new File(testDir, "matrix.csv"), "yyyyMMddHHmmss");
        TimeSeries ts = data.timeSeries("Peter Maffay");
    }
}
