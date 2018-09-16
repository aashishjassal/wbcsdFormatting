package com.wbcsd.fileGenerator.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the data from a given file path.
 * The class provides API to
 * 1. Read data line by line
 * 2. Read whole file in one go.
 * @author A.Jassal
 * @version $Id: FileReader.java 469 2012-06-15 08:51:28Z ajassal $
 */
public class TextDataReader {

    /** The br. */
    private LineNumberReader br;

    /**
     * Reads complete data from a file and returns a List<String>.
     * @param filePath the file path
     * @return List<String>
     */
    public List<String> readFileData(final String filePath) {
        List<String> strData = new ArrayList<String>();
        try {
            initializeReader(filePath);
            String s = null;
            while ((s = br.readLine()) != null) {
                strData.add(s);
            }
            closeReader();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return strData;
    }

    /**
     * Initialize reader.
     * @param filePath the file path
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void initializeReader(final String filePath) throws IOException {
        FileInputStream fi;
        try {
            File f = new File(filePath);
            fi = new FileInputStream(f);
            br = new LineNumberReader(new InputStreamReader(fi));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Close reader.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void closeReader() throws IOException {
        try {
            br.close();
            br = null;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
