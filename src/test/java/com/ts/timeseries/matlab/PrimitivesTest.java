package com.ts.timeseries.matlab;

import com.google.common.collect.ImmutableList;
import com.ts.timeseries.unit.LangAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrimitivesTest {

    @Test
    public void testConstructor() throws Exception {
        LangAssert.assertUtilityClass(Primitives.class);
    }

    @Test
    public void testConvertDouble() throws Exception {
        List<Double> list = ImmutableList.of(2.0, 3.0);
        double[] x = Primitives.convertDouble(list);
        Assert.assertArrayEquals(x, new double[]{2.0,3.0},1e-10);
    }

    @Test
    public void testConvertLong() throws Exception {
        List<Long> list = ImmutableList.of(2L, 3L);
        long[] x = Primitives.convertLong(list);
        Assert.assertArrayEquals(x, new long[]{2L,3L});
    }
}
