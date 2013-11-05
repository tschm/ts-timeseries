package com.ts.timeseries.util;

import org.junit.Test;

public class PreconditionsTest {

    @Test
    public void testCheckArgument() throws Exception {
        Preconditions.checkArgument(true, "Nothing happens");
    }

    @Test
    public void testCheckNotNull() throws Exception {
        Preconditions.checkNotNull(new Integer(5), "Nothing happens");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgumentAlarm() throws Exception {
        Preconditions.checkArgument(false, "Nothing happens");
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotNullAlarm() throws Exception {
        Preconditions.checkNotNull(null, "Nothing happens");
    }
}
