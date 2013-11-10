package com.ts.timeseries.grid;


import com.google.common.collect.ImmutableBiMap;
import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;
import org.joda.time.DateTime;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public final class GridBuilder {

    public static final String SIMULATION_TIME = "trade.end.date";
    public static final String TOLERANCEPRICE = "tolerance.price";
    public static final String START_DATE = "trade.start.date";

    GridBuilder() {
        throw new AssertionError();
    }

    public static Grid buildGrid(final String duration, final DateTime start, final DateTime end)
    {
        Long tolerance = new Duration(duration).getDurationInMs();

        DateTime s = start;
        SortedSet<Long> schedule = new TreeSet<Long>();

        while (s.getMillis() < end.getMillis())
        {
            s = s.plusDays(1);
            if (!(s.getDayOfWeek() >= 6))
                schedule.add(s.getMillis());
        }

        SortedSet<Long> grid = schedule.headSet(schedule.last());
        return new SimpleTimeGrid(grid, tolerance);
    }

    public static SimpleTimeSeries fitToGrid(TimeSeries run, Grid grid)
    {
        final SortedMap<Long, Double> data = new TreeMap<Long, Double>();
        for (long timeStamp : grid.timegrid()) {
            final double value;
            final SortedMap<Long, Double> headMap = run.points().headMap(timeStamp + 1L);
            final Long closestStamp = headMap.isEmpty() ? null : headMap.lastKey();
            if (closestStamp == null) {
                value = Double.NaN;
            } else if (timeStamp - closestStamp > grid.tolerance()) {
                value = Double.NaN;
            } else {
                value = run.points().get(closestStamp);
            }
            data.put(timeStamp, value);
        }

        return new SimpleTimeSeries(data);
    }

    static final class Duration {
        @SuppressWarnings({"WeakerAccess"})
        private static final ImmutableBiMap<String, TimeUnit> identifiers
                = ImmutableBiMap.of(
                "ms", TimeUnit.MILLISECONDS
                , "s", TimeUnit.SECONDS
                , "min", TimeUnit.MINUTES
                , "h", TimeUnit.HOURS
                , "d", TimeUnit.DAYS
        );

        private final TimeUnit timeUnit;
        private final long value;

        Duration(String serialized) {
            TimeUnit unit = null;
            long val = 0;
            for (String identifier : identifiers.keySet()) {
                if (serialized.endsWith(identifier)) {
                    unit = identifiers.get(identifier);
                    val = Long.valueOf(serialized.substring(0, serialized.length() - identifier.length()));
                    break;
                }
            }

            if (unit == null) {
                throw new IllegalArgumentException("Wrong format, should be number + any of " + identifiers.keySet());
            }

            timeUnit = unit;
            value = val;
        }

        /**
         * Duration in Milliseconds
         *
         * @return Length of Duration in Milliseconds
         */
        long getDurationInMs() {
            return timeUnit.toMillis(value);
        }
    }
}

