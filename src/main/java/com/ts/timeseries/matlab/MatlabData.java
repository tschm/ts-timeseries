package com.ts.timeseries.matlab;

/**
 * MatlabData
 */
public interface MatlabData {

    /**
     * @return matrix of time stamps, same format as in Matlab's datevec
     */
    double[][] getTimeMatrix();

    /**
     * @return data vector
     */
    double[] getData();
}
