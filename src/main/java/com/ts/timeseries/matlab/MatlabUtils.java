package com.ts.timeseries.matlab;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;


@SuppressWarnings({"WeakerAccess"})
final class MatlabUtils {
    private static final ThreadLocal<Calendar> calendar = new ThreadLocal<Calendar>() {
            @Override
            protected Calendar initialValue() {
                return new GregorianCalendar();
            }
        };

    private MatlabUtils() {
        throw new AssertionError();
    }

    static double[][] getDateArray(Collection<Long> time) {
        return getDateArray(Primitives.convertLong(time));
    }

    static double[] getDateArray(long time) {
        double[] data = new double[6];
        Calendar cal = calendar.get();
        cal.setTimeInMillis(time);
        data[0] = cal.get(Calendar.YEAR);
        data[1] = cal.get(Calendar.MONTH) + 1;
        data[2] = cal.get(Calendar.DAY_OF_MONTH);
        data[3] = cal.get(Calendar.HOUR_OF_DAY);
        data[4] = cal.get(Calendar.MINUTE);
        data[5] = cal.get(Calendar.SECOND);
        return data;
    }

    static double[][] getDateArray(long[] time) {
        int n = time.length;
        double[][] data = new double[n][7];
        int i = 0;

        for (Long t : time) {
            data[i] = getDateArray(t);
            i++;
        }

        return data;
    }

    static long getDateStamp(double[] dateArray) {
        Calendar cal = calendar.get();
        cal.set(Calendar.YEAR, (int) dateArray[0]);
        cal.set(Calendar.MONTH, (int) dateArray[1]-1);
        cal.set(Calendar.DAY_OF_MONTH, (int) dateArray[2]);
        cal.set(Calendar.HOUR_OF_DAY, (int) dateArray[3]);
        cal.set(Calendar.MINUTE, (int) dateArray[4]);
        cal.set(Calendar.SECOND, (int) dateArray[5]);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
