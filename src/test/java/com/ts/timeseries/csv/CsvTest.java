package com.ts.timeseries.csv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.ts.timeseries.unit.LangAssert;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvTest {

    @Test
    public void testConstructor() throws Exception {
        LangAssert.assertUtilityClass(Csv.class);
    }

    @Test
    public void testColumns() throws Exception {
        File tmp = File.createTempFile("test",".csv");
        tmp.deleteOnExit();

        Map<String, List<String>> data = new HashMap<String, List<String>>();
        data.put("A",ImmutableList.of("1","2","3"));
        data.put("B",ImmutableList.of("4","5","6"));

        Csv.writeColumns(data, tmp);

        Assert.assertEquals(Csv.readColumns(tmp), data);
    }

    @Test
    public void testRows() throws Exception {
        File tmp = File.createTempFile("test",".csv");
        tmp.deleteOnExit();

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        data.add(ImmutableMap.of("A","1","B","2","C","3"));
        data.add(ImmutableMap.of("A","4","B","5","C","6"));

        Csv.writeRows(data, tmp);

        Assert.assertEquals(Csv.readRows(tmp), data);
    }
}
