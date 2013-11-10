package com.ts.timeseries.hdf;

import ch.systemsx.cisd.hdf5.HDF5Factory;
import ch.systemsx.cisd.hdf5.IHDF5Reader;
import com.ts.timeseries.data.TimeSeries;
import com.ts.timeseries.util.Preconditions;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

class DataReader {
    private final IHDF5Reader reader;

    /**
     * construct a reader based on a file
     * @param file file to read from
     */
    public DataReader(File file)
    {
        reader = HDF5Factory.openForReading(file);
    }

    /**
     * @return Set of group names
     */
    public Set<String> readGroupNames()
    {
        return new HashSet<String>(reader.getGroupMembers("/"));
    }

    /**
     * @param groupName name of a group
     * @return Set of names within a group
     */
    public Set<String> readSeriesNames(String groupName)
    {
        Preconditions.checkArgument(this.readGroupNames().contains(groupName), "Group not in file");
        return new HashSet<String>(reader.getGroupMembers("/" + groupName));
    }

    /**
     * @return Set of all names
     */
    public Set<String> readNames()
    {
        Set<String> names = new HashSet<String>();

        for (String group : readGroupNames())
        {
            for (String name : readSeriesNames(group))
                names.add(group + "/" + name);
        }
        return names;
    }

    /**
     * close the reader
     */
    public void close()
    {
        reader.close();
    }

    /**
     * read a series
     *
     * @param name name of the series within this group
     * @return TimeSeries (or null)
     */
    public TimeSeries readSeries(String name) throws ncsa.hdf.hdf5lib.exceptions.HDF5JavaException
    {
        return new SimpleHDFSeries(reader.readCompoundArray(name, Measurement.class));
    }
}
