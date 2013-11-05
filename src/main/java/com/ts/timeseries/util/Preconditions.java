package com.ts.timeseries.util;

public final class Preconditions {

    private Preconditions() {
        throw new AssertionError();
    }

    public static void checkArgument(boolean b, String message) {
        if (!b)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotNull(Object instance, String message) {
        if (instance == null)
        {
            throw new NullPointerException(message);
        }
    }
}
