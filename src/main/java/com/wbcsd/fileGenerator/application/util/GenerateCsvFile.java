package com.wbcsd.fileGenerator.application.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.BlockCategarizationImpl;
import com.wbcsd.fileGenerator.api.ExploitationStatus;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: GenerateAccountCsvFile.java 1419 2014-04-01 06:45:00Z magarwal $
 */
public class GenerateCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param blockCategorizationData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final List<String[]> allData, final String fileLocation, final String fileName) {
        try {
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final String[] data : allData) {
                try {
                    writer.writeNext(data);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + data);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put quarter values.
     * @param blockCat the stats
     * @param entries the entries
     * @param year the year
     * @return String[]
     */
    private String[] putStatus(final BlockCategarizationImpl blockCat, String[] entries, Integer year) {
        ExploitationStatus status = blockCat.getYearBasedAverage().get(year);
        if (status != null) {
            entries = ArrayUtils.addAll(entries, status.getValue());
        } else {
            entries = ArrayUtils.addAll(entries, StringUtils.EMPTY);
        }
        return entries;
    }
}
