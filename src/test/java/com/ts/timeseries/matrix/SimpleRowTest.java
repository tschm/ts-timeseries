package com.ts.timeseries.matrix;

import org.junit.Assert;
import org.junit.Test;

public class SimpleRowTest {
    @Test
    public void testGet() throws Exception {
        SimpleRow row = new SimpleRow(5);
        row.values[2] = 3.0;
        Assert.assertEquals(row.values[2], 3.0, 1e-10);
        Assert.assertTrue(Double.isNaN(row.values[3]));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOutOfBound() throws Exception {
        SimpleRow row = new SimpleRow(5);
        System.out.println(row.values[10]);
    }
}
