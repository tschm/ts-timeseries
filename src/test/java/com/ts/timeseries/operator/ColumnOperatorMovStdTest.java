package com.ts.timeseries.operator;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ts.timeseries.matrix.Matrix;
import com.ts.timeseries.matrix.MatrixBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ColumnOperatorMovStdTest {
    @Test
    public void testTransform() throws Exception {
        ImmutableMap<String, Integer> map = ImmutableMap.of("A", 2, "B", 3);
        ColumnOperator operator = new ColumnOperatorMovStd(map);

        Matrix matrix = MatrixBuilder.build(ImmutableSet.of("A", "B"));
        matrix.writeScalar("A",1L,-2.9);
        matrix.writeScalar("B",2L,-3.0);
        matrix.writeScalar("B",4L, 3.0);
        matrix.writeScalar("A",4L,-2.0);
        Matrix A = operator.transform(matrix);

        Assert.assertEquals(A.readScalar("A", 4L), 0.24494897427831802, 1e-10);
    }
}
