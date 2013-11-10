package com.ts.timeseries.hdf;

import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.hdf.DataReader;
import ncsa.hdf.hdf5lib.exceptions.HDF5JavaException;

import java.io.File;
import java.util.Set;


public final class HdfInputData implements InputData {

    private final DataReader reader;

    public static final String DATA_HDF = "data.series.hdf";
    private final String group;

    public HdfInputData(File file, String group)
    {
        reader = new DataReader(file);
        this.group = group;
    }

    @Override
    public TimeSeries timeSeries(String name) {
        try {
            return reader.readSeries(group + "/" + name);
        } catch (HDF5JavaException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> names() {
        return reader.readSeriesNames(this.group);
    }
}