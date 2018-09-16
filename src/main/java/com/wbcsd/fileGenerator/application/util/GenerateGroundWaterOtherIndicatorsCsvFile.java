package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.GroundWaterOtherIndicatorsImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateGroundWaterOtherIndicatorsCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateGroundWaterOtherIndicatorsCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateGroundWaterOtherIndicatorsCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param groundWaterOtherIndicatorsData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<GroundWaterOtherIndicatorsImpl> groundWaterOtherIndicatorsData,
            final String fileLocation, final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District",
                        "Annual Replenishable Ground Water Resource", "Domestic & Industrial Water Supply",
                        "Projected demand for Domestic and Industrial uses upto 2025",
                        "Net Current Ground Water Availability",
                        "Net Ground Water Availability for Future Irrigation use",
                        "Stage of Ground Water Development (%)"};

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final GroundWaterOtherIndicatorsImpl gWaterOIData : groundWaterOtherIndicatorsData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            gWaterOIData.getState().getCode(), gWaterOIData.getState().getName(),
                            gWaterOIData.getDistrict(), getBigDecimalAsString(gWaterOIData.getAnnualGWResource()),
                            getBigDecimalAsString(gWaterOIData.getCurrDomesticDemand()),
                            getBigDecimalAsString(gWaterOIData.getProjDemand()),
                            getBigDecimalAsString(gWaterOIData.getNetCurrentGWaterAvailable()),
                            getBigDecimalAsString(gWaterOIData.getNetGWaterAvailable()),
                            getBigDecimalAsString(gWaterOIData.getStageGWaterDev())};

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + gWaterOIData);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
