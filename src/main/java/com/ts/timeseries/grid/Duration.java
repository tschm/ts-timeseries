package com.ts.timeseries.grid;

import com.google.common.collect.ImmutableBiMap;
import com.ts.timeseries.util.Preconditions;

import java.util.concurrent.TimeUnit;

final class Duration {
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

            Preconditions.checkNotNull(unit, "Wrong format, should be number + any of " + identifiers.keySet());

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
