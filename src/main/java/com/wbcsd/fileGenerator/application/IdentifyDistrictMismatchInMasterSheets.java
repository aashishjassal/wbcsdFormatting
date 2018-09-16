package com.wbcsd.fileGenerator.application;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.excel.CSVDataReader;

/**
 * @author Aashish
 */
public class IdentifyDistrictMismatchInMasterSheets {

    public static void main(String[] args) {
        CSVDataReader dataReader = new CSVDataReader();

        List<String[]> masterData = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Input\\DISTRICT_BDY.csv");

        Map<String, List<String>> stateToDistrictMap = new TreeMap<String, List<String>>();
        for (int i = 0; i < masterData.size(); i++) {
            if (i != 0) {
                String[] row = masterData.get(i);
                String state = row[2].trim().toUpperCase();
                String district = row[1].trim().toUpperCase();
                List<String> districtColl = new LinkedList<String>();
                if (stateToDistrictMap.containsKey(state)) {
                    districtColl = stateToDistrictMap.get(state);
                }
                Collections.sort(districtColl);
                districtColl.add(district);
                stateToDistrictMap.put(state, districtColl);
            }
        }

        List<String[]> masterDataBlockSheet = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Input\\SUBDIVISION_BDY.csv");

        Map<String, List<String>> stateToDistrictMapInBlockSheet = new TreeMap<String, List<String>>();
        for (int i = 0; i < masterDataBlockSheet.size(); i++) {
            if (i != 0) {
                String[] row = masterDataBlockSheet.get(i);
                String state = row[4].trim().toUpperCase();
                String district = row[3].trim().toUpperCase();
                if (StringUtils.isNotBlank(state)) {
                    List<String> districtColl = new LinkedList<String>();
                    if (stateToDistrictMapInBlockSheet.containsKey(state)) {
                        districtColl = stateToDistrictMapInBlockSheet.get(state);
                    }
                    Collections.sort(districtColl);
                    if (!districtColl.contains(district)) {
                        districtColl.add(district);
                    }

                    stateToDistrictMapInBlockSheet.put(state, districtColl);
                }
            }
        }

        for (Map.Entry<String, List<String>> entry : stateToDistrictMap.entrySet()) {
            String state = entry.getKey();
            List<String> districtSheetDistricts = entry.getValue();

            List<String> blockSheetDistricts = stateToDistrictMapInBlockSheet.get(state);
            for (String district : districtSheetDistricts) {
                if (!blockSheetDistricts.contains(district)) {
                    System.err.println("State: " + state
                            + " district is in District master sheet but not in block sheet: " + district);
                }
            }

        }

        for (Map.Entry<String, List<String>> entry : stateToDistrictMapInBlockSheet.entrySet()) {
            String state = entry.getKey();
            List<String> blockSheetDistricts = entry.getValue();

            List<String> districtSheetDistricts = stateToDistrictMap.get(state);

            for (String district : blockSheetDistricts) {
                if (!districtSheetDistricts.contains(district)) {
                    System.err.println("State: " + state
                            + " district is in Block master sheet but not in district sheet: " + district);
                }
            }
        }

    }
}
