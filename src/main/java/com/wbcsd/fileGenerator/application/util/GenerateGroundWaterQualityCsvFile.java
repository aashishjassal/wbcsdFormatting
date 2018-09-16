package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.lang3.BooleanUtils;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.GroundWaterQualityImpl;

/**
 * The Class GenerateAccountCsvFile.
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateGroundWaterQualityCsvFile {

    /** CSV Writer Factory. */
    private final CSVWriterFactory csvWriterFactory;

    /**
     * Instantiates a new generate Account csv file.
     */
    public GenerateGroundWaterQualityCsvFile() {
        this(new CSVWriterFactory());
    }

    /**
     * Instantiates a new generate Account csv file. To be used in Unit-Test.
     * @param pCsvWriterFactory the the CSV Writer Factory
     */
    public GenerateGroundWaterQualityCsvFile(final CSVWriterFactory pCsvWriterFactory) {
        this.csvWriterFactory = pCsvWriterFactory;
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param groundWaterQualityData the water status
     * @param fileLocation the file location
     * @param fileName the file name
     */
    public void generate(final Collection<GroundWaterQualityImpl> groundWaterQualityData, final String fileLocation,
            final String fileName) {
        try {
            File file = new File(fileLocation, fileName);
            if (!file.exists()) {
                System.out.println("Already does not exist, generate header");
                CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
                String[] entries = new String[] {"Country Code", "Country", "State Code", "State", "District",
                        "Arsenic breached", "Chloride breached", "Floride breached", "EC breached", "Iron  breached",
                        "Nitrate breached"};

                writer.writeNext(entries);
                writer.close();

            }
            CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
            for (final GroundWaterQualityImpl gWaterOIData : groundWaterQualityData) {
                try {
                    String[] entries = new String[] {Country.INDIA.getCode(), Country.INDIA.getName(),
                            gWaterOIData.getState().getCode(), gWaterOIData.getState().getName(),
                            gWaterOIData.getDistrict(), BooleanUtils.toStringYesNo(gWaterOIData.isArsenicBreached()),
                            BooleanUtils.toStringYesNo(gWaterOIData.isChlorideBreached()),
                            BooleanUtils.toStringYesNo(gWaterOIData.isFlorideBreached()),
                            BooleanUtils.toStringYesNo(gWaterOIData.isEcBreached()),
                            BooleanUtils.toStringYesNo(gWaterOIData.isIronBreached()),
                            BooleanUtils.toStringYesNo(gWaterOIData.isNitrateBreached())};

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
}
