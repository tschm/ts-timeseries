package com.ts.timeseries.unit;

import org.junit.Assert;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Assert for matrices and arrays
 */
public final class ArrayAssert {
    private ArrayAssert() {
        throw new AssertionError();
    }

    private static String errorMessage(String expected, String actuals) {
        return "Arrays are not equal.\nExpected: " + expected + "\nActual: " +
        actuals + "\n";
    }

    /**
     * Assert for two String arrays; compares element by element
     * @param expected expected String array
     * @param actual actual String array
     */
    public static void assertArrayEquals(String[] expected, String[] actual) {
        boolean equal = Arrays.equals(expected, actual);

        if (!equal) {
            assertTrue(errorMessage(Arrays.toString(expected),
                    Arrays.toString(actual)), equal);
        }
    }

    /**
     * Assert for two double matrices; compares element by element
     *
     * @param m1 matrix a
     * @param m2 matrix b
     * @param epsilon tolerance
     */
    public static void assertMatrix(double[][] m1, double[][] m2, double epsilon) {
        assertEquals(m1.length, m2.length);

        for (int i = 0; i < m1.length; i++) {
            Assert.assertArrayEquals(m1[i], m2[i], epsilon);
        }
    }

    /**
     * Assert for two String arrays; compares element by element
     * @param expected expected String matrix
     * @param actual actual String matrix
     */
    public static void assertMatrix(String[][] expected, String[][] actual) {
        assertEquals(expected.length, actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }
}
