package com.ts.timeseries.unit;

import org.junit.Test;


public class ArrayAssertTest {
    @Test
    public void utilityConstructor()
        throws NoSuchMethodException, InstantiationException, 
            IllegalAccessException {
        LangAssert.assertUtilityClass(ArrayAssert.class);
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsMatrices() {
        ArrayAssert.assertMatrix(new double[][]{
                {0.2}
        }, new double[][]{
                {0.1},
                {0.2}
        }, 1e-10);
    }

    @Test
    public void assertEqualsMatrices() {
        ArrayAssert.assertMatrix(new double[][]{
                {0.2}
        }, new double[][]{
                {0.2}
        }, 1e-10);
    }

    @Test
    public void assertEqualsStringArrays() {
        ArrayAssert.assertArrayEquals(new String[]{"0.1", "0.2"},
                new String[]{"0.1", "0.2"});
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsStringArrays() {
        ArrayAssert.assertArrayEquals(new String[]{"0.2"},
                new String[]{"0.1", "0.2"});
    }

    @Test(expected = AssertionError.class)
    public void assertNotEqualsStringMatrices() {
        ArrayAssert.assertMatrix(new String[][]{
                {"0.2"}
        }, new String[][]{
                {"0.1"},
                {"0.2"}
        });
    }

    @Test
    public void assertEqualsStringMatrices() {
        ArrayAssert.assertMatrix(new String[][]{
                {"0.2"}
        }, new String[][]{
                {"0.2"}
        });
    }
}
