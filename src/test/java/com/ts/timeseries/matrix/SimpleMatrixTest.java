package com.ts.timeseries.matrix;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.ts.timeseries.data.SimpleTimeSeries;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class SimpleMatrixTest {

    private Matrix A;

    @Before
    public void setUp() throws Exception {
        A = MatrixBuilder.build(ImmutableList.of("A", "B"), ImmutableSortedSet.of(0L, 1L, 5L, 3L));
        A.writeScalar("A",3L,10.0);
    }

    @Test
    public void testReadScalar() throws Exception {
        Assert.assertEquals(A.readScalar("A",3L),10.0,1e-10);
        Assert.assertTrue(Double.isNaN(A.readScalar("A",5L)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadScalarWrongTime() throws Exception {
        Assert.assertEquals(A.readScalar("A",6L),10.0,1e-10);
    }

    @Test
    public void testWriteScalarWrongTime() throws Exception {
        A.writeScalar("A",6L,10.0);
        Assert.assertEquals(A.readScalar("A",6L),10.0,1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadScalarWrongName() throws Exception {
        Assert.assertEquals(A.readScalar("C",3L),10.0,1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteScalarWrongName() throws Exception {
        A.writeScalar("C",3L,10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadVectorWrongTime() throws Exception {
        A.readVector(6L);
    }

    @Test
    public void testReadVector() throws Exception {
        Map<String, Double> x = A.readVector(3L);
        Assert.assertEquals(x, ImmutableMap.of("A", 10.0, "B", Double.NaN));
    }


    @Test
    public void testWriteVector() throws Exception {
        ImmutableMap<String, Double> map = ImmutableMap.of("A", 20.0, "B", 5.0);
        A.writeVector(5L, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteVectorWrongKey() throws Exception {
        ImmutableMap<String, Double> map = ImmutableMap.of("A", 20.0, "C", 5.0);
        A.writeVector(5L, map);
    }

    @Test
    public void testSymbols() throws Exception {
        Assert.assertEquals(A.columns(), ImmutableSet.of("A", "B"));

    }

    @Test
    public void testSetTimeSeries() throws Exception {
        SimpleTimeSeries ts = new SimpleTimeSeries(ImmutableMap.of(0L, 100.0, 1L, 200.0, 3L, 300.0, 5L, 500.0));
        A.setTimeSeries("B",ts);
        Assert.assertEquals(A.readScalar("B",3L), 300.0, 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTimeSeriesWrongName() throws Exception {
        A.getTimeSeries("C");
    }

    @Test
    public void testSchedule() throws Exception {
        Assert.assertEquals(A.timegrid(), ImmutableSortedSet.of(0L,1L,3L,5L));
    }

    @Test
    public void testHashCode() throws Exception {
        Assert.assertNotNull(A.hashCode());
    }

    @Test
    public void testString() throws Exception {
        Assert.assertEquals(A.toString(), "0: A\n1: B\n0 {A=NaN, B=NaN}\n1 {A=NaN, B=NaN}\n3 {A=10.0, B=NaN}\n5 {A=NaN, B=NaN}\n");
    }
}
