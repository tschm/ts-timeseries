package com.ts.timeseries.unit;

import org.junit.Assert;

import java.util.Iterator;


/**
 * Assert for Collection of Double
 */
public final class CollectionAssert {
    private CollectionAssert() {
        throw new AssertionError();
    }

    /**
     * Assert for two Iterable Double instances; check for same length and same content, element by element
     *
     * @param expected expected Iterable
     * @param actual actual Iterable
     * @param epsilon tolerance
     */
    public static void assertEquals(Iterable<Double> expected,
        Iterable<Double> actual, double epsilon) {
        Iterator<Double> e = expected.iterator();
        Iterator<Double> a = actual.iterator();

        while (e.hasNext() && a.hasNext()) {
            Assert.assertEquals(e.next(), a.next(), epsilon);
        }

        if (e.hasNext() || a.hasNext()) {
            throw new AssertionError("Collections of different size.");
        }
    }
}
