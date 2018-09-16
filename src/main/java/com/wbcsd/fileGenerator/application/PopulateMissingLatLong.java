package com.wbcsd.fileGenerator.application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.DamDataImpl;
import com.wbcsd.fileGenerator.api.LatLong;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;
import com.wbcsd.fileGenerator.application.util.GenerateDamDataCsvFile;
import com.wbcsd.fileGenerator.application.util.LatLongCalculator;
import com.wbcsd.fileGenerator.excel.CSVDataReader;
import com.wbcsd.fileGenerator.excel.DamDataReader;

/**
 * @author a.jassal
 * @version $Id: $
 * @since 1.4.1
 */
public class PopulateMissingLatLong {

    public static void main(String[] args) {
        CSVDataReader dataReader = new CSVDataReader();
        DamDataReader damDataReader = new DamDataReader(dataReader);

        GenerateDamDataCsvFile generateDamDataCsvFile = new GenerateDamDataCsvFile();

        List<DamDataImpl> finalData = new LinkedList<DamDataImpl>();
        Map<String, String> nearestCityMap = new HashMap<String, String>();

        List<String[]> originallyPopulatedDamData = dataReader
                .readFileData("D:\\Personal Docs\\wbcsd\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\DamData.csv");

        Map<State, List<String[]>> inputDamData = damDataReader
                .readDamData("D:\\Personal Docs\\wbcsd\\fileGenerator\\src\\main\\resources\\Input\\DamData");
        // Now parse inputDam to get key of state_damname

        for (Map.Entry<State, List<String[]>> entry : inputDamData.entrySet()) {
            State state = entry.getKey();
            List<String[]> dataRows = entry.getValue();
            for (String[] rowData : dataRows) {
                String nearestCity = "";
                String damName = rowData[2].trim();
                if (rowData.length != 19) {
                    nearestCity = rowData[5].trim();
                } else {
                    nearestCity = rowData[8].trim();
                }

                String mapKey = getMapKey(state, damName);
                nearestCityMap.put(mapKey, nearestCity);
            }
        }

        // Now read originallyPopulateddata
        for (int i = 0; i < originallyPopulatedDamData.size(); i++) {
            if (i == 0) {
                System.out.println("Its header");
            } else {
                String[] dataRow = originallyPopulatedDamData.get(i);
                DamDataImpl data = new DamDataImpl();
                String stateName = dataRow[3].trim();
                String damName = dataRow[4].trim();
                String lattitude = dataRow[5].trim();
                String longitude = dataRow[6].trim();
                String resAreaStr = dataRow[7].trim();
                String effSCapStr = dataRow[8].trim();

                State state = State.value(stateName);

                if (StringUtils.isBlank(longitude) || StringUtils.isBlank(lattitude)) {
                    System.err.println("Missing longitude or lattitude.. Calculating from nearest city");
                    String nearestCity = nearestCityMap.get(getMapKey(state, damName));
                    if (StringUtils.isBlank(nearestCity)) {
                        System.err.println("Blank nearest city for: " + state + " , " + damName);
                    } else {
                        LatLong latLongImpl = LatLongCalculator.getLongitudeLatitude(nearestCity);
                        if (latLongImpl.getLatitude() == null || latLongImpl.getLongitude() == null) {
                            // Now try and get from dam name
                            latLongImpl = LatLongCalculator.getLongitudeLatitude(damName);
                        }
                        longitude = latLongImpl.getLongitude();
                        lattitude = latLongImpl.getLatitude();
                    }
                }
                LatLongImpl latLong = new LatLongImpl();
                latLong.setLatitude(lattitude);
                latLong.setLongitude(longitude);
                data.setState(state);
                data.setDamName(damName);
                data.setLatLong(latLong);

                if (StringUtils.isNotBlank(resAreaStr)) {
                    try {
                        BigDecimal resArea = BigDecimal.valueOf(Double.valueOf(resAreaStr));
                        data.setResArea(resArea);
                    } catch (RuntimeException e) {
                        System.err.println("Not a correct data: " + resAreaStr);
                    }
                }
                if (StringUtils.isNotBlank(effSCapStr)) {
                    try {
                        BigDecimal effSCap = BigDecimal.valueOf(Double.valueOf(effSCapStr));
                        data.setEffStorageCap(effSCap);
                    } catch (RuntimeException e) {
                        System.err.println("Not a correct data: " + effSCapStr);
                    }
                }

                finalData.add(data);
            }
        }

        generateDamDataCsvFile.generate(finalData,
                "D:\\Personal Docs\\wbcsd\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo",
                "DamDataModified.csv");
    }

    /**
     * Gets the map key.
     * @param state the state
     * @param damName the dam name
     * @return the map key
     */
    private static String getMapKey(State state, final String damName) {
        return state.getName() + "_" + damName;

    }
}
