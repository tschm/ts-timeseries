package com.ts.timeseries.operator;

import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.matrix.Matrix;
import com.ts.timeseries.matrix.MatrixBuilder;


public abstract class ColumnOperator {
    public abstract TimeSeries transform(TimeSeries ts, String symbol);

    public final Matrix transform(Matrix matrix)
    {
        Matrix A = MatrixBuilder.build(matrix.columns(), matrix.timegrid());
        for (String column : matrix.columns())
        {
            A.setTimeSeries(column, this.transform(matrix.getTimeSeries(column), column));
        }
        return A;
    }
}

