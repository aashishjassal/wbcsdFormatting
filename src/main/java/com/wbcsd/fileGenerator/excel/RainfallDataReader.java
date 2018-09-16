package com.wbcsd.fileGenerator.excel;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.State;

/**
 * Reads the data from a given csv file path.
 * The class provides API to
 * 1. Read data line by line
 * 2. Read whole file in one go.
 * @author A.Jassal
 * @version $Id: CSVDataReader.java 296 2011-10-11 14:42:33Z ajassal $
 */
public class RainfallDataReader {

    private final CSVDataReader csvDataReader;

    private final TextDataReader textDataReader;

    /**
     * Instantiates a new dam data reader.
     * @param csvDataReader the csv data reader
     */
    public RainfallDataReader(CSVDataReader csvDataReader, final TextDataReader textDataReader) {
        this.csvDataReader = csvDataReader;
        this.textDataReader = textDataReader;
    }

    /**
     * Reads complete data from a file and returns a List<String[]>.
     * @param damDataFolderPath the dam data folder path
     * @return List<String[]>
     */
    public Map<State, List<String[]>> readRainfallData(final String rainfallDataFolderPath) {
        Map<State, List<String[]>> rainfallData = new LinkedHashMap<State, List<String[]>>();
        File damDataFolder = new File(rainfallDataFolderPath);
        File[] files = damDataFolder.listFiles();
        for (File file : files) {
            String statecode = StringUtils.remove(file.getName(), ".csv");
            State state = State.valueByCode(statecode);
            if (state == null) {
                throw new RuntimeException("Invalid state: " + file.getName());
            }
            List<String[]> data = csvDataReader.readFileData(file.getAbsolutePath());
            // First line is header here, remove it.
            data.remove(0);
            // if (rainfallData.containsKey(state)) {
            // rainfallData.get(state).addAll(data);
            // } else {
            rainfallData.put(state, data);
            // }
        }
        return rainfallData;
    }

    public Map<State, List<List<String>>> readLatestRainfallData(final String rainfallLatestDataFolderPath) {
        Map<State, List<List<String>>> rainfallData = new LinkedHashMap<State, List<List<String>>>();
        File damDataFolder = new File(rainfallLatestDataFolderPath);
        File[] folderFiles = damDataFolder.listFiles();
        for (final File folderFile : folderFiles) {
            if (folderFile.isDirectory()) {
                State state = State.valueByCode(folderFile.getName());
                if (state == null) {
                    throw new RuntimeException("Invalid state: " + folderFile.getName());
                }
                final File[] files = folderFile.listFiles();

                for (File file : files) {
                    List<String> data = textDataReader.readFileData(file.getAbsolutePath());
                    List<String> finalData = new LinkedList<String>();
                    // Just keep following lines, remove all extra lines
                    for (String line : data) {
                        line = line.trim();
                        if (StringUtils.startsWithIgnoreCase(line, "District")) {
                            finalData.add(line);
                        }
                        if (StringUtils.startsWithIgnoreCase(line, "2011")
                                || StringUtils.startsWithIgnoreCase(line, "2012")
                                || StringUtils.startsWithIgnoreCase(line, "2013")) {
                            finalData.add(line);
                        }
                    }
                    if (rainfallData.containsKey(state)) {
                        List<List<String>> stateData = rainfallData.get(state);
                        stateData.add(finalData);
                    } else {
                        List<List<String>> stateData = new LinkedList<List<String>>();
                        stateData.add(finalData);
                        rainfallData.put(state, stateData);
                    }
                }

            }
        }
        return rainfallData;
    }
}
