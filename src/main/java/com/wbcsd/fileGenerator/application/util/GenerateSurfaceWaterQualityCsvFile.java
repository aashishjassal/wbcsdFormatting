package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.SurfaceWaterDataImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateSurfaceWaterQualityCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateSurfaceWaterQualityCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateSurfaceWaterQualityCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param groundWaterOtherIndicatorsData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<SurfaceWaterDataImpl> groundWaterOtherIndicatorsData,
            final String fileLocation, final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District",
                        "Observation Name", "Lattitude", "Longitude", "Faecal Coliform", "Total Coliform",
                        "Dissolved Oxygen", "BOD", "Nitrate + Nitrite", "Conductivity", "pH", "SAR", "COD"};

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final SurfaceWaterDataImpl surfWater : groundWaterOtherIndicatorsData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            surfWater.getState().getCode(), surfWater.getState().getName(), surfWater.getDistrict(),
                            surfWater.getObsAreaName(), surfWater.getLatLong().getLatitude(),
                            surfWater.getLatLong().getLongitude(), surfWater.getFaecalColiform(),
                            surfWater.getTotalColiform(), surfWater.getDissOxy(), surfWater.getBod(),
                            surfWater.getNitriteNitrate(), surfWater.getConductivity(), surfWater.getpH(),
                            surfWater.getSar(), surfWater.getCod()};

                    writer.writeNext(entries);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    System.out.println("Data exception: " + surfWater);
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
