package com.ts.timeseries.matlab;



public class matlabDemo {
    public static void main(String[] args)
    {
        double[][] date = {{2010,1,1,14,10,0},{2012,2,4,14,30,0}};
        double[] data = {5.0,2.0};
        MatlabTimeSeries x = new MatlabTimeSeries(date, data);
        System.out.println(x);
        System.out.println(x.points());
        System.out.println(x.getData()[0]);
    }
}
