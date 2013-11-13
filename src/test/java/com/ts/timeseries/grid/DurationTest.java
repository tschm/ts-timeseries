package com.ts.timeseries.grid;

import org.junit.Test;

public class DurationTest {
    @Test(expected = IllegalArgumentException.class)
    public void testTimegrid() throws Exception {
        new Duration("20hhhh");

    }
}
