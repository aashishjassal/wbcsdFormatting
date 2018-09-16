package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVWriterWrapper {

    private final CSVWriter csvWriter;

    public CSVWriterWrapper(final String fileLocation, final String fileName) throws IOException {
        csvWriter = new CSVWriter(new FileWriter(new File(fileLocation, fileName), true));
    }

    public void close() throws IOException {
        csvWriter.close();
    }

    public void writeNext(String[] nextLine) {
        csvWriter.writeNext(nextLine);
    }
}
