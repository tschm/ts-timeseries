package com.ts.timeseries.operator;

import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.matrix.Matrix;
import com.ts.timeseries.matrix.MatrixBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ColumnOperatorTest {

    private class TestOperator extends ColumnOperator
    {
        @Override
        public TimeSeries transform(TimeSeries ts, String symbol) {
            return Transformations.abs(ts);
        }
    }

    @Test
    public void testTransform() throws Exception {
        TestOperator operator = new TestOperator();

        Matrix matrix = MatrixBuilder.build(ImmutableSet.of("A", "B"));
        matrix.writeScalar("A",1L,-2.9);
        matrix.writeScalar("B",2L,-3.0);
        matrix.writeScalar("B",4L, 3.0);
        matrix.writeScalar("A",4L,-2.0);
        Matrix A = operator.transform(matrix);

        Matrix matrix2 = MatrixBuilder.build(ImmutableSet.of("A","B"));
        matrix2.writeScalar("A",1L, 2.9);
        matrix2.writeScalar("B",2L, 3.0);
        matrix2.writeScalar("B",4L, 3.0);
        matrix2.writeScalar("A",4L, 2.0);

        Assert.assertEquals(A, matrix2);
    }

}
