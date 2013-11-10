package com.ts.timeseries.data;

import com.ts.timeseries.hdf.hdfInputData;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class hdfInputDataTest {
    private static final File testDir = new File("src//test//resources//data//");

    @Test
    public void testTimeSeries() throws Exception {
        InputData data = new hdfInputData(new File(testDir, "matrix.h5"));
        Assert.assertEquals(data.names().size(), 57);
        TimeSeries ts = data.timeSeries("data/FTSE-price adjusted");
        Assert.assertEquals(ts.points().size(), 2263);
    }
}
