package com.wbcsd.fileGenerator.excel;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Reads the data from a given csv file path.
 * The class provides API to
 * 1. Read data line by line
 * 2. Read whole file in one go.
 * @author A.Jassal
 * @version $Id: CSVDataReader.java 296 2011-10-11 14:42:33Z ajassal $
 */
public class GroundWaterQualityDataReader {

    private final CSVDataReader csvDataReader;

    /**
     * Instantiates a new dam data reader.
     * @param csvDataReader the csv data reader
     */
    public GroundWaterQualityDataReader(CSVDataReader csvDataReader) {
        this.csvDataReader = csvDataReader;
    }

    /**
     * Reads complete data from a file and returns a List<String[]>.
     * @param groundWaterQualityDataFolderPath the dam data folder path
     * @return List<String[]>
     */
    public Map<String, List<String[]>> readGroundWaterQualityData(final String groundWaterQualityDataFolderPath) {
        Map<String, List<String[]>> groundWaterQualityMap = new LinkedHashMap<String, List<String[]>>();
        File damDataFolder = new File(groundWaterQualityDataFolderPath);
        File[] files = damDataFolder.listFiles();
        for (File file : files) {
            String category = StringUtils.remove(file.getName(), ".csv");
            List<String[]> data = csvDataReader.readFileData(file.getAbsolutePath());
            // First line is header here, remove it.
            data.remove(0);
            groundWaterQualityMap.put(category, data);
        }
        return groundWaterQualityMap;
    }
}
