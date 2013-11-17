package com.ts.timeseries.hdf;

import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.TimeSeries;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class hdfInputDataTest {
    private static final File testDir = new File("src//test//resources//data//");

    @Test
    public void testTimeSeries() throws Exception {
        InputData data = new HdfInputData(new File(testDir, "matrix.h5"), "data");
        Assert.assertEquals(data.names().size(), 57);
        TimeSeries ts = data.timeSeries("FTSE-price adjusted");
        Assert.assertEquals(ts.points().size(), 2263);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeSeriesWrongName() throws Exception {
        InputData data = new HdfInputData(new File(testDir, "matrix.h5"), "data");
        TimeSeries ts = data.timeSeries("PeterMaffay");
    }
}
