package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.BlockCategarizationImpl;
import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.ExploitationStatus;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: GenerateAccountCsvFile.java 1419 2014-04-01 06:45:00Z magarwal $
 */
public class GenerateBlockCategorizationCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    private final List<Integer> yearList;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateBlockCategorizationCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateBlockCategorizationCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
        yearList = Lists.newArrayList(2004, 2009, 2011,2013);
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param blockCategorizationData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<BlockCategarizationImpl> blockCategorizationData, final String fileLocation,
            final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District", "Block"};
                for (Integer year : yearList) {
                    entries = ArrayUtils.addAll(entries, year + "Status");
                }

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final BlockCategarizationImpl blockCat : blockCategorizationData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            blockCat.getState().getCode().toUpperCase(), blockCat.getState().getName().toUpperCase(), blockCat.getDistrict().toUpperCase(),
                            blockCat.getBlock().toUpperCase()};

                    for (Integer year : yearList) {
                        entries = putStatus(blockCat, entries, year);
                    }

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + blockCat);
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
            entries = ArrayUtils.addAll(entries, status.toString());
        } else {
            entries = ArrayUtils.addAll(entries, StringUtils.EMPTY);
        }
        return entries;
    }
}
