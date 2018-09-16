package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.wbcsd.fileGenerator.api.SiteInfo;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: GenerateAccountCsvFile.java 1419 2014-04-01 06:45:00Z magarwal $
 */
public class GenerateSiteInfoCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     * @param logger the logger
     */
    public GenerateSiteInfoCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param logger the logger
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateSiteInfoCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    public void generate(final Collection<SiteInfo> infoArray, final String fileLocation, final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"SiteId", "SiteName", "Latitude", "Longitude", "State", "Type",
                        "District", "Block"};

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (SiteInfo siteInfo : infoArray) {
                try {
                    String[] entries = new String[] {siteInfo.getId(), siteInfo.getName(),
                            siteInfo.getLatLong().getLatitude(), siteInfo.getLatLong().getLongitude(),
                            siteInfo.getState().getCode(), siteInfo.getType().getDescription(), siteInfo.getDistrict(),
                            siteInfo.getBlock()};

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    System.out.println("Data exception: " + siteInfo);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
