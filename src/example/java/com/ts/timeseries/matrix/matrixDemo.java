package com.ts.timeseries.matrix;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

public class matrixDemo {
    public static void main(String[] args)
    {
        Matrix A = MatrixBuilder.build(ImmutableList.of("A", "B", "C"), ImmutableSortedSet.of(10L, 20L, 30L, 40L, 50L));
        System.out.println(A.columns());
        System.out.println(A.timegrid());
        System.out.println(A.toString());
        A.writeScalar("A", 10L, 2.0);
        A.writeScalar("A", 20L, 3.0);
        System.out.println(A.readScalar("A",20L));
        System.out.println(A.getTimeSeries("A"));
    }
}
