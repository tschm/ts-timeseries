package com.ts.timeseries.matlab;

import com.ts.timeseries.unit.LangAssert;
import org.junit.Assert;
import org.junit.Test;


public class DatatypesTest {

    @Test
    public void testConstructor() throws Exception {
        LangAssert.assertUtilityClass(Datatypes.class);
    }

    @Test
    public void testGetStringList() throws Exception {
        Assert.assertTrue(Datatypes.getStringList().isEmpty());
    }
}
