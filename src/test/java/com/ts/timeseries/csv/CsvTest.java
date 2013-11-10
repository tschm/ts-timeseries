package com.ts.timeseries.csv;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CsvTest {
    @Test
    public void testWrite() throws Exception {
        File file = new File("src//test//resources//data//testCsv1.csv");
        Map<String, List<String>> x = Csv.read(file);

        File tmp = File.createTempFile("testcsvWriter",".csv");

        Csv.write(x, tmp);
        Map<String, List<String>> y = Csv.read(tmp);
        Assert.assertEquals(x,y);

        tmp.delete();
    }

    @Test
    public void testRead() throws Exception {
        File file = new File("src//test//resources//data//testCsv1.csv");
        Map<String, List<String>> x = Csv.read(file);
        Assert.assertEquals(x.keySet(), ImmutableSet.of("A", "B", "C"));
        Assert.assertEquals(x.get("B"), ImmutableList.of("2", "5"));
    }
}
