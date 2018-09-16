package com.wbcsd.fileGenerator.excel;

import java.io.FileReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Reads the data from a given csv file path.
 * The class provides API to
 * 1. Read data line by line
 * 2. Read whole file in one go.
 * @author A.Jassal
 * @version $Id: CSVDataReader.java 296 2011-10-11 14:42:33Z ajassal $
 */
public class CSVDataReader {

    /**
     * Reads complete data from a file and returns a List<String[]>.
     * @param filePath the file path
     * @return List<String[]>
     */
    public List<String[]> readFileData(final String filePath) {
        List<String[]> strData = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            strData = reader.readAll();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }
}
