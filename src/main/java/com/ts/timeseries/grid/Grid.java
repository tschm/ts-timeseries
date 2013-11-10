package com.ts.timeseries.grid;

import java.util.SortedSet;

public interface Grid {
    SortedSet<Long> timegrid();

    Long tolerance();
}
