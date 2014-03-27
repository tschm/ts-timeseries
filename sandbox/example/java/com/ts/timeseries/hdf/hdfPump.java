package com.ts.timeseries.hdf;

import com.google.common.collect.ImmutableMap;
import com.ts.timeseries.data.SimpleTimeSeries;
import ncsa.hdf.hdf5lib.exceptions.HDF5JavaException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class hdfPump {

    public static void main(String[] args) throws IOException, ParseException, HDF5JavaException {
        DataWriter writer = new DataWriter(new File("PeterMaffay.h"));
        writer.createGroup("A");
        writer.createGroup("B");

        writer.writeSeries("A","s1",new SimpleTimeSeries(ImmutableMap.of(0L, 100.0, 5L, 120.0)));
        System.out.println(writer.readSeries("A/s1"));

        // this won't work. You can not predate or modify existing data!
        writer.writeSeries("A", "s1", new SimpleTimeSeries(ImmutableMap.of(3L, 200.0)));
        System.out.println(writer.readSeries("A/s1"));


        writer.close();
    }
}
