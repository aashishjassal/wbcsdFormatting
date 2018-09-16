package com.wbcsd.fileGenerator.application.util;

import java.io.IOException;

public class CSVWriterFactory {

    public CSVWriterWrapper create(String fileLocation, String fileName) throws IOException {
        return new CSVWriterWrapper(fileLocation, fileName);
    }
}
