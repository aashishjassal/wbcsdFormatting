package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.DamDataImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateDamDataCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateDamDataCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateDamDataCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param damData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<DamDataImpl> damData, final String fileLocation, final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "Dam Name",
                        "Lattitude", "Longitude", "Resorvoir Area", "Effective Storage Capacity"};

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final DamDataImpl dam : damData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            dam.getState().getCode(), dam.getState().getName(), dam.getDamName(),
                            dam.getLatLong().getLatitude(), dam.getLatLong().getLongitude(),
                            getBigDecimalAsString(dam.getResArea()), getBigDecimalAsString(dam.getEffStorageCap())};

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + dam);
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
