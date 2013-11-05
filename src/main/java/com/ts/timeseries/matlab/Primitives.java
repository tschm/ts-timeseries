package com.ts.timeseries.matlab;

import java.util.Collection;

/**
 * Convert collections into trivial arrays
 */
final class Primitives {
    private Primitives() {
        throw new AssertionError();
    }

    /**
     * @param collection Number collection
     * @return double array
     */
    public static double[] convertDouble(Collection<Double> collection) {
        double[] result = new double[collection.size()];

        int i = 0;

        for (Double number : collection) {
            result[i++] = number;
        }

        return result;
    }

    /**
     * @param collection Long collection
     * @return long array
     */
    public static long[] convertLong(Collection<Long> collection) {
        long[] result = new long[collection.size()];

        int i = 0;

        for (Long number : collection) {
            result[i++] = number;
        }

        return result;
    }
}
