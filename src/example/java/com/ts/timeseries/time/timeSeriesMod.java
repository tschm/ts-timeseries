package com.ts.timeseries.time;

import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.operator.Transformations;

import java.util.HashMap;
import java.util.Random;

public class timeSeriesMod {
    public static void main(String[] args)
    {
        Random x = new Random();
        HashMap<Long, Double> ts = new HashMap<Long, Double>();

        for (long l = 1000; l < 1000000; l=l+1000)
            ts.put(l, x.nextGaussian());

        SimpleTimeSeries s = new SimpleTimeSeries(ts);
        System.out.println(s);

        System.out.println(Transformations.cumsum(s));
        System.out.println(Transformations.ewmstd(s,20));
        System.out.println(Transformations.ewma(Transformations.cumsum(s),20));
        System.out.println(Transformations.sqrt(Transformations.abs(s)));
    }
}
