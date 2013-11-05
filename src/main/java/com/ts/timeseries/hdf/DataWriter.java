package com.ts.timeseries.hdf;

import ch.systemsx.cisd.hdf5.HDF5Factory;
import ch.systemsx.cisd.hdf5.IHDF5Writer;
import ch.systemsx.cisd.hdf5.IHDF5WriterConfigurator;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.util.Preconditions;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;

/**
 * DataWriter class for creating and writing to h5 files
 */
public final class DataWriter {

    private final IHDF5Writer writer;

    public DataWriter(File file)
    {
        IHDF5WriterConfigurator configuration = HDF5Factory.configure(file);
        writer = configuration.writer();
    }

    public void close()
    {
        writer.close();
    }

    public void createGroup(String groupName) {
        if (!(this.readGroupNames().contains(groupName)))
            writer.object().createGroup(groupName);
    }

    private Set<String> readGroupNames()
    {
        return new HashSet<String>(writer.getGroupMembers("/"));
    }

    private Set<String> readSeriesNames(String groupName)
    {
        Preconditions.checkArgument(this.readGroupNames().contains(groupName), "Group not in file");
        return new HashSet<String>(writer.getGroupMembers("/" + groupName));
    }

    public void writeSeries(String group, String name, TimeSeries ts) throws ncsa.hdf.hdf5lib.exceptions.HDF5JavaException
    {
        Preconditions.checkArgument(this.readGroupNames().contains(group), "Group not in file");
        final String table = "/" + group + "/" + name;

        Measurement[] m;
        if (!this.readSeriesNames(group).contains(name))
        {
            this.createSeriesInGroup(group, name);
            m = new SimpleHDFSeries(ts.points()).getMeasurementArray();
            writer.compound().writeArray(table, m);
        }
        else
        {
            // this is reading all rows from a series in a group, expensive!
            SortedMap<Long, Double> data = readSeries(group + "/" + name).points();
            // i am only interested in the last row (e.g. the row with the largest key (key = time)
            long max = data.lastKey();
            // I take only those points from the time series that are strictly after the largest existing key
            // Those points are added to the existing data!
            data.putAll(ts.points().tailMap(max + 1));
            // all this data is rewritten into the hdfFile! Very expensive
            m = new SimpleHDFSeries(data).getMeasurementArray();
            writer.writeCompoundArray(table, m);
            // So two main problems here
            // 1. How do I extract the largest key or last row without extracting all data!
            //    I have no idea here...
            //
            // 2. How do I strictly append data rather than flush and rewrite data?
            //    I am exploring options along those lines, but I had no luck yet...
            //    IHDF5WriterConfigurator configuration = HDF5Factory.configure(file);
            //    writer = configuration.writer();
            //    when setting keepDataSets... and doing nothing else the system will complain
            //    java.ang.NoSuchMethodError: <init> at ch.systemsx.cisd.hdf5.hdf5lib.H5.H5Dopen(Native Method)
            //    I am using your battery stuff.
        }
    }

    private void createSeriesInGroup(String group, String name) {
        Preconditions.checkArgument(this.readGroupNames().contains(group), "Group not in file");
        writer.object().createGroup("/" + group + "/" + name);
    }

    public TimeSeries readSeries(String name) throws ncsa.hdf.hdf5lib.exceptions.HDF5JavaException
    {
        //Preconditions.checkArgument(this.readSeriesNames(group).contains(name), "readSeriesNames not in group");
        return new SimpleHDFSeries(writer.readCompoundArray(name, Measurement.class));
    }
}
