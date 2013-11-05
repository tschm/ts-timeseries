package com.ts.timeseries.matrix;

import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;

import java.util.*;

public final class MatrixUtil {
    private MatrixUtil() {
        throw new AssertionError();
    }

    private static boolean atLeastOneReal(Collection<Double> x)
    {
        for (Double a : x)
            if (!(Double.isNaN(a)))
                return true;

        return false;
    }

    private static List<Double> noNaNSubset(Collection<Double> x)
    {
        List<Double> list = new ArrayList<Double>();
        for (Double a : x)
            if (!(Double.isNaN(a)))
                list.add(a);

        return list;
    }

    public static TimeSeries rowSum(Matrix A)
    {
        final Map<Long, Double> rowSum = new HashMap<Long, Double>();

        for (Long time : A.timegrid())
        {
            Collection<Double> entries = A.readVector(time).values();

            if (atLeastOneReal(entries))
            {
                List<Double> list = noNaNSubset(entries);
                Double sum = 0.0;
                for (Double e : list)
                    sum += e;

                rowSum.put(time, sum);
            }
            else
                rowSum.put(time, Double.NaN);
        }

        return new SimpleTimeSeries(rowSum);
    }
}
