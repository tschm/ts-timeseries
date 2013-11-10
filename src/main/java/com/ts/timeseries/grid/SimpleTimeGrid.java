package com.ts.timeseries.grid;

import java.util.SortedSet;

final class SimpleTimeGrid implements Grid {

    private final SortedSet<Long> grid;
    private final Long tolerance;

    SimpleTimeGrid(SortedSet<Long> grid, Long tolerance)
    {
        this.grid = grid;
        this.tolerance = tolerance;
    }

    @Override
    public SortedSet<Long> timegrid() {
        return grid;
    }

    @Override
    public Long tolerance() {
        return tolerance;
    }
}
