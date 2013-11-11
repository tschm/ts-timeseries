package com.ts.timeseries.csv;

import com.ts.timeseries.data.InputData;
import com.ts.timeseries.data.SimpleTimeSeries;
import com.ts.timeseries.data.TimeSeries;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CsvInputData implements InputData {

    private final Map<String, List<String>> columns;
    private final List<Long> time = new ArrayList<Long>();

    public static final String DATE_CSV_FORMAT = "data.series.csv.format";
    public static final String DATA_CSV = "data.series.csv";

    public CsvInputData(File file, String DateFormat) throws IOException, ParseException {
        columns = Csv.readColumns(file);

        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateFormat);

        List<String> dates = columns.get("Date");

        for (String date : dates)
            time.add(formatter.parseDateTime(date).getMillis());
    }

    @Override
    public TimeSeries timeSeries(String name) {
        List<Double> data = new ArrayList<Double>();
        for (String x : columns.get(name))
        {
            data.add(Double.valueOf(x));
        }
        return new SimpleTimeSeries(time, data);
    }

    @Override
    public Set<String> names() {
        Set<String> x = columns.keySet();
        x.remove("Date");
        return x;
    }
}
