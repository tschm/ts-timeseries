package com.ts.timeseries.csv;

import java.io.*;
import java.util.*;

public final class Csv {

    private Csv()
    {
        throw new AssertionError();
    }

    public static List<List<String>> read(File file) throws IOException
    {
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        try
        {
            List<List<String>> values = new ArrayList<>();

            String thisLine;
            while ((thisLine = reader.readLine()) != null)
            {
                values.add(new ArrayList<>(Arrays.asList(thisLine.split(","))));
            }
            return values;
        }
        finally
        {
            reader.close();
        }
    }

    public static Map<String, List<String>> readColumns(File file) throws IOException
    {
        List<List<String>> values = read(file);

        Map<String, List<String>> columns = new HashMap<>();
        if (values.size() > 0)
        {
            for (int column = 0; column < values.get(0).size(); column++)
            {
                List<String> data = new ArrayList<>();
                for (List<String> value : values)
                    data.add(value.get(column));

                columns.put(data.get(0), data.subList(1, data.size()));
            }
        }
        return columns;
    }

    public static List<Map<String, String>> readRows(File file) throws IOException
    {
        List<List<String>> values = read(file);

        List<String> keys = values.get(0);

        List<Map<String, String>> xx = new ArrayList<>();
        for (int row = 1; row < values.size(); ++row)
        {
            Map<String, String> map = new HashMap<>();
            int column = 0;
            for (String key : keys)
            {
                map.put(key, values.get(row).get(column));
                column++;
            }
            xx.add(map);

        }
        return xx;
    }

    public static void write(List<List<String>> data, File file) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (List<String> x : data) {
                writeRow(writer, x);
            }
        }
    }

    public static void writeColumns(Map<String, List<String>> data, File file) throws IOException
    {
        List<List<String>> x = new ArrayList<>();

        List<String> keys = new ArrayList<>();
        for (String key : data.keySet())
            keys.add(key);

        x.add(keys);

        int n = data.get(keys.get(0)).size();
        for (int row = 0; row < n; ++row)
        {
            List<String> w = new ArrayList<>();

            for (String key : keys)
                w.add(data.get(key).get(row));

            x.add(w);
        }
        write(x, file);
    }

    public static void writeRows(List<Map<String, String>> data, File file) throws IOException
    {
        List<List<String>> x = new ArrayList<>();

        List<String> keys = new ArrayList<>();
        for (String key : data.get(0).keySet())
            keys.add(key);

        x.add(keys);

        for (Map<String, String> aData : data)
        {
            List<String> w = new ArrayList<>();

            for (String key : keys)
                w.add(aData.get(key));

            x.add(w);
        }
        write(x, file);
    }

    private static void writeRow(BufferedWriter writer, List<String> data) throws IOException
    {
        int j = 0;

        // writeColumns keys
        for (String x : data)
        {
            if (j > 0)
                writer.write(",");

            writer.write(x);
            j++;
        }
        writer.newLine();
    }
}
