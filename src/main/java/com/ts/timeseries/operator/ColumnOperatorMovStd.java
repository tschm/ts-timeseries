package com.ts.timeseries.operator;

import com.ts.timeseries.data.TimeSeries;

import java.util.Map;

public class ColumnOperatorMovStd extends ColumnOperator {

    private final Map<String, Integer> map;

    public ColumnOperatorMovStd(Map<String, Integer> map)
    {
        this.map = map;
    }

    @Override
    public TimeSeries transform(TimeSeries ts, String symbol) {
        return Transformations.ewmstd(ts, map.get(symbol));
    }
}
