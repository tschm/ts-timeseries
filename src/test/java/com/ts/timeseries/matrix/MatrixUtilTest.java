package com.ts.timeseries.matrix;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.unit.LangAssert;
import org.junit.Assert;
import org.junit.Test;

public class MatrixUtilTest {

    @Test
    public void testConstructor() throws Exception {
        LangAssert.assertUtilityClass(MatrixUtil.class);
    }

    @Test
    public void testRowSum() throws Exception {
        SimpleMatrix A = new SimpleMatrix(ImmutableSet.of("A", "B"));
        A.writeScalar("A",0L,1.0);
        A.writeScalar("A",1L, Double.NaN);
        A.writeScalar("B",1L, Double.NaN);
        A.writeScalar("B",2L,2.0);
        A.writeScalar("A",3L,5.0);
        A.writeScalar("B",3L,4.0);
        Assert.assertEquals(MatrixUtil.rowSum(A).points(), ImmutableMap.of(0L,1.0,1L,Double.NaN,2L,2.0,3L,9.0));
    }
}
