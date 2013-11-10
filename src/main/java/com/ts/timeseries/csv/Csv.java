package com.ts.timeseries.csv;

import java.io.*;
import java.util.*;

public final class Csv {

    private Csv() {
        throw new AssertionError();
    }

    public static Map<String, List<String>> read(File file) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        try
        {
            String[][] values = new CsvParser(reader).parse();

            Map<String, List<String>> columns = new HashMap<String, List<String>>();
            if (values.length > 0)
            {
                for (int column = 0; column < values[0].length; column++)
                {
                    List<String> data = new ArrayList<String>();
                    for (String[] value : values)
                        data.add(value[column]);

                    columns.put(values[0][column], data.subList(1, data.size()));
                }
            }
            return columns;
        }
        finally
        {
            reader.close();
        }
    }

    public static void write(Map<String, List<String>> data, File file) throws IOException {

        final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try
        {
            List<String> keys = new ArrayList<String>();

            for (String key : data.keySet())
            {
                keys.add(key);
            }
            // order of keys is crucial!
            writeRow(writer, keys);

            int n = data.get(keys.get(0)).size();
            for (int row = 0; row < n; ++row)
            {
                List<String> x = new ArrayList<String>();
                for (String key : keys)
                    x.add(data.get(key).get(row));

                writeRow(writer, x);
            }

        }
        finally
        {
            writer.close();
        }
    }

    private static void writeRow(BufferedWriter writer, List<String> data) throws IOException {

        int j = 0;

        // write keys
        for (String x : data)
        {
            if (j > 0)
                writer.write(CsvParser.VALUE_SEPARATOR);

            writer.write(x);
            j++;
        }
        writer.write(CsvParser.LINE_FEED);
    }
}
