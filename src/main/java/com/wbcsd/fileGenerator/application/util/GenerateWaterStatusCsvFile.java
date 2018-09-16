package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.WaterStats;
import com.wbcsd.fileGenerator.api.impl.WaterStatsImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: GenerateAccountCsvFile.java 1419 2014-04-01 06:45:00Z magarwal $
 */
public class GenerateWaterStatusCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    private final List<Integer> yearList;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateWaterStatusCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateWaterStatusCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
        yearList = Lists.newArrayList(2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014);
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param waterStatus the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Map<String, SiteInfo> siteDataMap, final Collection<WaterStatsImpl> waterStatus,
            final String fileLocation, final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District", "Block",
                        "Site Id", "SiteName", "Latitude", "Longitude"};
                for (Integer year : yearList) {
                    entries = ArrayUtils.addAll(entries, year + "Quarter 1", year + "Quarter 2", year + "Quarter 3",
                            year + "Quarter 4", year + "Average");
                }

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final WaterStats stats : waterStatus) {
                try {
                    final SiteInfo siteInfo = siteDataMap.get(stats.getSiteId());
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            siteInfo.getState().getCode(), siteInfo.getState().getName(), siteInfo.getDistrict(),
                            siteInfo.getBlock(), siteInfo.getId(), siteInfo.getName(),
                            siteInfo.getLatLong().getLatitude(), siteInfo.getLatLong().getLongitude()};

                    for (Integer year : yearList) {
                        entries = putQuarterValues(stats, entries, year);
                    }

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + stats);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put quarter values.
     * @param stats the stats
     * @param entries the entries
     * @param year the year
     * @return String[]
     */
    private String[] putQuarterValues(final WaterStats stats, String[] entries, Integer year) {
        List<BigDecimal> quarter1 = stats.getFirstQuarterReading().get(year);
        List<BigDecimal> quarter2 = stats.getSecondQuarterReading().get(year);
        List<BigDecimal> quarter3 = stats.getThirdQuarterReading().get(year);
        List<BigDecimal> quarter4 = stats.getFourthQuarterReading().get(year);
        List<BigDecimal> allQuarters = new ArrayList<BigDecimal>();
        if (quarter1 != null) {
            allQuarters.addAll(quarter1);
        }
        if (quarter2 != null) {
            allQuarters.addAll(quarter2);
        }
        if (quarter3 != null) {
            allQuarters.addAll(quarter3);
        }
        if (quarter4 != null) {
            allQuarters.addAll(quarter4);
        }
        return ArrayUtils.addAll(entries, getAverageDataForAnYear(quarter1, stats),
                getAverageDataForAnYear(quarter2, stats), getAverageDataForAnYear(quarter3, stats),
                getAverageDataForAnYear(quarter4, stats), getAverageDataForAnYear(allQuarters, stats));
    }

    /**
     * Gets the average data for an year.
     * @param dataForAnYear the data for an year
     * @param stats the stats
     * @return the average data for an year
     */
    private String getAverageDataForAnYear(List<BigDecimal> dataForAnYear, WaterStats stats) {
        if (dataForAnYear == null || dataForAnYear.isEmpty()) {
            // System.err.println("No data for year available for: " + stats);
            return StringUtils.EMPTY;
        }
        return AverageUtils.getAverage(dataForAnYear).toString();
    }

}
