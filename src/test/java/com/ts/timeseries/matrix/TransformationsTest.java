package com.ts.timeseries.matrix;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.operator.Transformations;
import org.junit.Assert;
import org.junit.Test;

public class TransformationsTest {

    @Test
    public void testRowSum() throws Exception {
        SimpleMatrix A = new SimpleMatrix(ImmutableSortedSet.of(1L, 2L, 3L), ImmutableSortedSet.of("A", "B"));
        A.writeScalar("A",1L,1.0);
        A.writeScalar("A",2L,10.0);
        A.writeScalar("A",3L,Double.NaN);

        A.writeScalar("B",1L,3.0);
        A.writeScalar("B",2L,Double.NaN);
        A.writeScalar("B",3L,Double.NaN);

        TimeSeries ts = MatrixUtil.rowSum(A);
        TimeSeries result = new SimpleTimeSeries(ImmutableList.of(1L, 2L, 3L), ImmutableList.of(4.0,10.0,Double.NaN));
        Assert.assertEquals(ts, result);
    }
}
