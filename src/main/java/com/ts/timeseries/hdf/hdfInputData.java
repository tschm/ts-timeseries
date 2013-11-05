package com.ts.timeseries.hdf;

import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.hdf.DataReader;
import ncsa.hdf.hdf5lib.exceptions.HDF5JavaException;

import java.io.File;
import java.util.Set;


public final class hdfInputData implements InputData {

    private final DataReader reader;

    public hdfInputData(File file)
    {
        reader = new DataReader(file);
    }

    @Override
    public TimeSeries timeSeries(String name) {
        try {
            return reader.readSeries(name);
        } catch (HDF5JavaException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> names() {
        return reader.readNames();
    }
}
