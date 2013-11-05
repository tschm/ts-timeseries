package com.ts.timeseries.matlab;

import org.junit.Assert;
import org.junit.Test;


public class DatatypesTest {

    @Test
    public void testGetStringList() throws Exception {
        Assert.assertTrue(Datatypes.getStringList().isEmpty());
    }
}
