package com.ts.timeseries.matlab;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MatlabUtilsTest {

    @Test
    public void testGetDateArray() throws Exception {
        long stamp = MatlabUtils.getDateStamp(new double[]{2010,3,17,14,31,20});
        double[] array = MatlabUtils.getDateArray(stamp);
        Assert.assertTrue(Arrays.equals(array, new double[]{2010,3,17,14,31,20}));
    }
}
