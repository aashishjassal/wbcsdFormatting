package com.wbcsd.fileGenerator.excel;

import java.io.File;
import java.util.LinkedHashMap;
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
public class DamDataReader {

    private final CSVDataReader csvDataReader;

    /**
     * Instantiates a new dam data reader.
     * @param csvDataReader the csv data reader
     */
    public DamDataReader(CSVDataReader csvDataReader) {
        this.csvDataReader = csvDataReader;
    }

    /**
     * Reads complete data from a file and returns a List<String[]>.
     * @param damDataFolderPath the dam data folder path
     * @return List<String[]>
     */
    public Map<State, List<String[]>> readDamData(final String damDataFolderPath) {
        Map<State, List<String[]>> damData = new LinkedHashMap<State, List<String[]>>();
        File damDataFolder = new File(damDataFolderPath);
        File[] files = damDataFolder.listFiles();
        for (File file : files) {
            String[] token = StringUtils.split(file.getName(), "_");
            State state = State.valueByCode(token[0]);
            if (state == null) {
                throw new RuntimeException("Invalid state: " + file.getName());
            }
            List<String[]> data = csvDataReader.readFileData(file.getAbsolutePath());
            // First line is header here, remove it.
            data.remove(0);
            if (damData.containsKey(state)) {
                damData.get(state).addAll(data);
            } else {
                damData.put(state, data);
            }
        }
        return damData;
    }
}
