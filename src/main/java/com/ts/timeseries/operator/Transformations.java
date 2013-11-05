package com.ts.timeseries.operator;

import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;

import java.util.HashMap;
import java.util.Map;

// todo: add interpolation

public class Transformations {

    private Transformations() {
        throw new AssertionError();
    }

    public static TimeSeries ewma(TimeSeries ts, int n)
    {
        TimeSeries r = dropna(ts);
        double weights = 0;
        double lambda = 1.0 - 1.0/n;
        double sum = 0;
        Map<Long, Double> x = new HashMap<Long, Double>();
        for (Long t: r.points().keySet())
        {
            weights = 1 + weights * lambda;
            sum = r.points().get(t) + sum * lambda;
            x.put(t, sum/weights);
        }
        return new SimpleTimeSeries(x);
    }

    public static TimeSeries ewmstd(TimeSeries ts, int n)
    {
        TimeSeries r = dropna(ts);
        TimeSeries mean = ewma(ts, n);

        Map<Long, Double> x = new HashMap<Long, Double>();
        for (Long t: r.points().keySet())
        {
            double diff = r.points().get(t) - mean.points().get(t);
            x.put(t, diff*diff);
        }
        TimeSeries meanVariance = ewma(new SimpleTimeSeries(x), n);
        return sqrt(meanVariance);
    }

    public static TimeSeries dropna(TimeSeries ts)
    {
        Map<Long, Double> x = new HashMap<Long, Double>();
        for (Long t : ts.points().keySet())
        {
            Double z = ts.points().get(t);
            if (!Double.isNaN(z)) {
                x.put(t, z);
            }
        }

        return new SimpleTimeSeries(x);
    }

    public static TimeSeries cumsum(TimeSeries ts)
    {
        Map<Long, Double> x = new HashMap<Long, Double>();
        double sum = 0;
        TimeSeries r = dropna(ts);
        for (Long t : r.points().keySet())
        {
            sum += r.points().get(t);
            x.put(t, sum);
        }

        return new SimpleTimeSeries(x);
    }

    public static TimeSeries sqrt(TimeSeries ts)
    {
        Map<Long, Double> x = new HashMap<Long, Double>();
        TimeSeries r = dropna(ts);

        for (Long t : r.points().keySet())
        {
            x.put(t, StrictMath.sqrt(r.points().get(t)));
        }

        return new SimpleTimeSeries(x);
    }

    public static TimeSeries abs(TimeSeries ts) {
        Map<Long, Double> x = new HashMap<Long, Double>();
        TimeSeries r = dropna(ts);

        for (Long t : r.points().keySet())
        {
            x.put(t, StrictMath.abs(r.points().get(t)));
        }

        return new SimpleTimeSeries(x);
    }

    public static TimeSeries pow(TimeSeries ts, double exponent) {
        Map<Long, Double> x = new HashMap<Long, Double>();
        TimeSeries r = dropna(ts);

        for (Long t : r.points().keySet())
        {
            x.put(t, StrictMath.pow(r.points().get(t), exponent));
        }

        return new SimpleTimeSeries(x);
    }

    public static double sum(TimeSeries ts) {
        TimeSeries r = dropna(ts);
        double x = 0.0;

        for (Long t : r.points().keySet())
        {
            x += r.points().get(t);
        }

        return x;
    }

    public static double mean(TimeSeries ts) {
        TimeSeries r = dropna(ts);
        return sum(r)/r.points().size();
    }

    public static TimeSeries centre(TimeSeries ts) {
        Map<Long, Double> x = new HashMap<Long, Double>();
        TimeSeries r = dropna(ts);
        double mean = mean(r);

        for (Long t : r.points().keySet())
        {
            x.put(t, r.points().get(t) - mean);
        }

        return new SimpleTimeSeries(x);
    }

    public static double variance(TimeSeries ts) {
        return mean(pow(centre(ts),2));
    }

    public static double std(TimeSeries ts) {
        return StrictMath.sqrt(variance(ts));
    }

    public static double SharpeRatio(TimeSeries ts) {
        return mean(ts)/std(ts);
    }
}
