package com.ts.timeseries.unit;

import org.junit.Test;


public class LangAssertTest {
    @Test(expected = AssertionError.class)
    public void publicConstructor()
        throws NoSuchMethodException, InstantiationException, 
            IllegalAccessException {
        LangAssert.assertUtilityClass(PublicConstructor.class);
    }

    @Test(expected = AssertionError.class)
    public void wrongException()
        throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        LangAssert.assertUtilityClass(WrongException.class);
    }

    @Test
    public void utilityConstructor()
        throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        LangAssert.assertUtilityClass(LangAssert.class);
    }

    @Test(expected = AssertionError.class)
    public void notUtilityClass()
        throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        LangAssert.assertUtilityClass(String.class);
    }

    private static final class PublicConstructor {
        public PublicConstructor() {
        }
    }

    private static final class WrongException {
        private WrongException() {
            throw new IllegalStateException();
        }
    }
}
