package com.ts.timeseries.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

final class CsvParser {
    private static final String[][] EMPTY_VALUES = new String[][]{};
    static final char VALUE_SEPARATOR = ',';
    static final char CARRIAGE_RETURN = '\r';
    static final char LINE_FEED = '\n';

    private final Reader reader;
    private List<List<String>> values = new ArrayList<List<String>>();
    private List<String> lastRow = new ArrayList<String>();
    private final StringBuilder currentToken = new StringBuilder();

    CsvParser(Reader reader) {
        this.reader = reader;
    }

    String[][] parse() throws IOException {
        char ch;

        final char[] buffer = new char[1];

        while ((reader.read(buffer, 0, 1)) != -1)
        {
            ch = buffer[0];
            switch (ch) {
                case CARRIAGE_RETURN:
                    break;
                case LINE_FEED:
                    flushLine();
                    break;
                case VALUE_SEPARATOR:
                    flushValue();
                    break;
                default:
                    currentToken.append(ch);
                    break;
            }
        }

        if (currentToken.length() > 0 || !lastRow.isEmpty()) {
            flushLine();
        }

        if (values.isEmpty()) {
            return EMPTY_VALUES;
        }

        final int rowSize = values.get(0).size();
        final String[][] result = new String[values.size()][];

        for (int i = 0; i < values.size(); i++) {
            List row = values.get(i);
            result[i] = new String[rowSize];
            for (int j = 0;j < row.size();j++) {
                result[i][j] = (String)row.get(j);
            }
        }
        return result;
    }

    private void flushValue() {
        lastRow.add(currentToken.toString());
        currentToken.setLength(0);
    }

    private void flushLine() {
        flushValue();

        if (!(lastRow.size() == 1 && lastRow.get(0).equals("\u001a")))
        {
            if (!values.isEmpty() && values.get(0).size() != lastRow.size()) {
                throw new CsvFormatError("Received a row [" + lastRow
                    + "] with wrong number of values. Expected " + values.get(0).size());
            }

            values.add(new ArrayList<String>(lastRow));
            lastRow.clear();
        }
    }
}
