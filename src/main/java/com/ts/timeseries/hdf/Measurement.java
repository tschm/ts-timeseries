package com.ts.timeseries.hdf;
/**
 * A Data Transfer Object for a combined temperature / voltage measurement.
 */
final class Measurement
{
    long time;
    double data;

    // Important: needs to have a default constructor, otherwise JHDF5 will bail out on reading.
    Measurement()
    {
    }

    /**
     * Constructor for measurement
     *
     * @param time
     * @param data
     */
    Measurement(long time, double data)
    {
        this.time = time;
        this.data = data;
    }
}
