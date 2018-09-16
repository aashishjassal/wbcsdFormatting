package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.DamDataImpl;
import com.wbcsd.fileGenerator.api.LatLong;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class DamDataGenerator {

    /** The generate g water other indicators csv file. */
    private final GenerateDamDataCsvFile generateDamDataCsvFile;

    public DamDataGenerator() {
        generateDamDataCsvFile = new GenerateDamDataCsvFile();
    }

    /**
     * Generate.
     * @param damData the dam data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<State, List<String[]>> damData, String csvGenerationLocation) {

        List<DamDataImpl> damDataList = new LinkedList<DamDataImpl>();
        System.out.println("Reading dam data");

        for (Map.Entry<State, List<String[]>> entry : damData.entrySet()) {
            State state = entry.getKey();
            List<String[]> data = entry.getValue();

            for (int i = 0; i < data.size(); i++) {
                String[] rowData = data.get(i);
                String name = "";
                String resAreaStr = "";
                String effStorageCapStr = "";
                String longitude = "";
                String lattitude = "";
                String nearestCity = "";

                if (rowData.length != 19) {
                    System.out.println("This data does not contain lat long, Need to identify. State: " + state
                            + "Header is: " + Lists.newArrayList(rowData));
                    name = rowData[2].trim();
                    resAreaStr = rowData[11].trim();
                    effStorageCapStr = rowData[12].trim();
                    nearestCity = rowData[5].trim();

                    LatLong latLongImpl = LatLongCalculator.getLongitudeLatitude(nearestCity);
                    if (latLongImpl == null) {
                        System.err.println("Can not determine lat long on basis of nearest city too: " + nearestCity);
                        continue;
                    }
                    longitude = latLongImpl.getLongitude();
                    lattitude = latLongImpl.getLatitude();

                } else {
                    name = rowData[2].trim();
                    resAreaStr = rowData[15].trim();
                    effStorageCapStr = rowData[16].trim();
                    longitude = rowData[4].trim();
                    lattitude = rowData[5].trim();
                    nearestCity = rowData[8].trim();

                    if (StringUtils.isBlank(longitude) || StringUtils.isBlank(lattitude)) {
                        System.err.println("Missing longitude or lattitude.. Calculating from nearest city");
                        LatLong latLongImpl = LatLongCalculator.getLongitudeLatitude(nearestCity);
                        if (latLongImpl == null) {
                            System.err.println("Can not determine lat long on basis of nearest city too: "
                                    + nearestCity);
                            continue;
                        }
                        longitude = latLongImpl.getLongitude();
                        lattitude = latLongImpl.getLatitude();
                    }
                }
                if (StringUtils.isNotBlank(name)) {
                    if (StringUtils.contains(name, "\n")) {
                        name = StringUtils.replace(name, "\n", " ");
                    }
                    DamDataImpl damDataImpl = new DamDataImpl();
                    damDataImpl.setState(state);
                    damDataImpl.setDamName(name);
                    LatLongImpl latLongImpl = new LatLongImpl();
                    latLongImpl.setLatitude(lattitude);
                    latLongImpl.setLongitude(longitude);
                    damDataImpl.setLatLong(latLongImpl);

                    if (StringUtils.isNotBlank(resAreaStr) && !StringUtils.equalsIgnoreCase(resAreaStr, "-")
                            && !StringUtils.equalsIgnoreCase(resAreaStr, "_")
                            && !StringUtils.containsIgnoreCase(resAreaStr, "--")
                            && !StringUtils.equalsIgnoreCase(resAreaStr, "|")) {
                        try {
                            // If contains multiple .(dot), remove keep only first dot
                            int dotCount = StringUtils.countMatches(resAreaStr, ".");
                            if (dotCount > 1) {
                                System.err.println("More then expected dots: " + dotCount + " in " + resAreaStr);
                                String[] token = StringUtils.split(resAreaStr, ".");
                                String intCount = token[0];
                                String decCount = "";
                                for (int a = 1; a < token.length; a++) {
                                    decCount = decCount + token[a];
                                }
                                resAreaStr = intCount + "." + decCount;
                            }
                            BigDecimal resArea = BigDecimal.valueOf(Double.valueOf(resAreaStr));
                            damDataImpl.setResArea(resArea);
                        } catch (RuntimeException e) {
                            System.err.println("Not a correct data: " + resAreaStr);
                        }

                    }
                    if (StringUtils.isNotBlank(effStorageCapStr)
                            && !StringUtils.equalsIgnoreCase(effStorageCapStr, "-")
                            && !StringUtils.containsIgnoreCase(effStorageCapStr, "--")
                            && !StringUtils.equalsIgnoreCase(effStorageCapStr, "_")
                            && !StringUtils.equalsIgnoreCase(effStorageCapStr, "|")) {
                        try {
                            // If contains multiple .(dot), remove keep only first dot
                            int dotCount = StringUtils.countMatches(effStorageCapStr, ".");
                            if (dotCount > 1) {
                                System.err.println("More then expected dots: " + dotCount + " in " + effStorageCapStr);
                                String[] token = StringUtils.split(effStorageCapStr, ".");
                                String intCount = token[0];
                                String decCount = "";
                                for (int a = 1; a < token.length; a++) {
                                    decCount = decCount + token[a];
                                }
                                effStorageCapStr = intCount + "." + decCount;
                            }
                            BigDecimal effStorageCap = BigDecimal.valueOf(Double.valueOf(effStorageCapStr));
                            damDataImpl.setEffStorageCap(effStorageCap);
                        } catch (RuntimeException e) {
                            System.err.println("Not a correct data: " + effStorageCapStr);
                        }

                    }

                    damDataList.add(damDataImpl);

                } else {
                    System.err.println("Blank dam name in state: " + state + " Data: " + Lists.newArrayList(rowData));
                }
            }
        }

        System.out.println("Reading dam data done");

        generateDamDataCsvFile.generate(damDataList, csvGenerationLocation, "DamData.csv");

    }
}
