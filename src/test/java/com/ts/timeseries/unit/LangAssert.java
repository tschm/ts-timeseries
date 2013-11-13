package com.ts.timeseries.unit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertFalse;

/**
 * Assert for UtilityClasses, e.g. classes with no public constructor
 */
public final class LangAssert {
    private LangAssert() {
        throw new AssertionError();
    }

    /**
     * Check is standard constructor is private and whether it throws an error if trying to create an instance.
     * Very useful for classes with public static methods only.
     *
     * @param clazz Class that has to be checked
     * @throws NoSuchMethodException exception
     * @throws IllegalAccessException exception
     * @throws InstantiationException exception
     */
    public static void assertUtilityClass(Class<? > clazz)
        throws NoSuchMethodException, IllegalAccessException, 
            InstantiationException {
        final Constructor constructor = clazz.getDeclaredConstructor();
        assertFalse("Constructor is expected to be private.",
            constructor.isAccessible());
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
            throw new AssertionError(
                "Constructor was expected to throw AssertionError");
        } catch (InvocationTargetException e) {
            if (!(e.getCause() instanceof AssertionError)) {
                throw new AssertionError("Expected AssertionError, received " +
                    e.getCause());
            }
        } finally {
            constructor.setAccessible(false);
        }
    }
}
