package com.ts.timeseries.csv;

import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.TimeSeries;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class CsvInputDataTest {
    @Test
    public void testTimeSeries() throws Exception {
        File file = new File("src//test//resources//data//tsData.csv");
        InputData x = new CsvInputData(file, "yyyyMMdd");
        Assert.assertEquals(x.names(), ImmutableSet.of("A", "B"));
        TimeSeries y = x.timeSeries("A");
        Assert.assertEquals(y.points().size(),3);

    }
}
