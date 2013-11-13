package com.ts.timeseries.unit;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;


public class CollectionAssertTest {
    @Test
    public void utilityConstructor()
        throws NoSuchMethodException, InstantiationException, 
            IllegalAccessException {
        LangAssert.assertUtilityClass(CollectionAssert.class);
    }

    @Test(expected = AssertionError.class)
    public void epsilonWrongSize() {
        CollectionAssert.assertEquals(ImmutableList.of(1.0, 2.0),
                ImmutableList.of(1.0), 0.0);
    }

    @Test(expected = AssertionError.class)
    public void epsilonWrongSize2() {
        CollectionAssert.assertEquals(ImmutableList.of(1.0),
                ImmutableList.of(1.0, 2.0), 0.0);
    }

    @Test
    public void noEpsilon() {
        CollectionAssert.assertEquals(ImmutableList.of(1.0, 2.0),
                ImmutableSortedSet.of(1.0, 2.0), 1e-10);
    }

    @Test
    public void epsilon() {
        CollectionAssert.assertEquals(ImmutableList.of(1.09, 2.0),
                ImmutableSortedSet.of(1.0, 2.05), 0.1);
    }
}
