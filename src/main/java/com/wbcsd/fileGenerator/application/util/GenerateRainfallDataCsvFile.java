package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.RainfallDataImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateRainfallDataCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    private final List<Integer> yearList;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateRainfallDataCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateRainfallDataCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
        yearList = Lists.newArrayList(2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013);
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param rainfallData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<RainfallDataImpl> rainfallData, final String fileLocation,
            final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District"};
                for (Integer year : yearList) {
                    entries = ArrayUtils.addAll(entries, year + " Average");
                }

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final RainfallDataImpl d : rainfallData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            d.getState().getCode(), d.getState().getName(), d.getDistrict()};
                    for (Integer year : yearList) {
                        entries = putAverage(d, entries, year);
                    }
                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + d);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put quarter values.
     * @param rainData the stats
     * @param entries the entries
     * @param year the year
     * @return String[]
     */
    private String[] putAverage(final RainfallDataImpl rainData, String[] entries, Integer year) {
        BigDecimal average = rainData.getYearBasedAverage().get(year);
        entries = ArrayUtils.addAll(entries, getBigDecimalAsString(average));
        return entries;
    }

    /**
     * Gets the big decimal as string.
     * @param data the data
     * @return the big decimal as string
     */
    private String getBigDecimalAsString(BigDecimal data) {
        if (data == null) {
            // System.err.println("No data for year available for: " + stats);
            return StringUtils.EMPTY;
        }
        return data.toString();
    }
}
