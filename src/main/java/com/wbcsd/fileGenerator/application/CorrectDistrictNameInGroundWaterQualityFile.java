package com.wbcsd.fileGenerator.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.wbcsd.fileGenerator.application.util.DistrictMapper;
import com.wbcsd.fileGenerator.application.util.GenerateCsvFile;
import com.wbcsd.fileGenerator.excel.CSVDataReader;

/**
 * @author Aashish
 */
public class CorrectDistrictNameInGroundWaterQualityFile {

    public static void main(String[] args) {
        CSVDataReader dataReader = new CSVDataReader();
        GenerateCsvFile generateCsvFile = new GenerateCsvFile();

        List<String[]> masterData = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Input\\DISTRICT_BDY.csv");

        Map<String, List<String>> stateToDistrictMap = new HashMap<String, List<String>>();
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

        List<String[]> originallyPopulatedData = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\GroundWaterQuality.csv");

        List<String[]> finalDataToBePopulated = new LinkedList<String[]>();

        for (int i = 0; i < originallyPopulatedData.size(); i++) {
            if (i == 0) {
                System.out.println("Header");
                finalDataToBePopulated.add(originallyPopulatedData.get(i));
            } else {
                String[] dataRow = originallyPopulatedData.get(i);
                List<String> finalDataRow = new LinkedList<String>();
                String rowState = "";
                for (int r = 0; r < dataRow.length; r++) {
                    if (r == 3) {
                        rowState = dataRow[r].toUpperCase().trim();
                        finalDataRow.add(rowState);
                    } else if (r == 4) {
                        String rowDistrict = dataRow[r].toUpperCase().trim();
                        List<String> masterDistricts = stateToDistrictMap.get(rowState);
                        String finalDistrict = DistrictMapper.getOverriddenNameIfApplicable(rowState, rowDistrict,
                                masterDistricts);
                        finalDataRow.add(finalDistrict);
                    } else {
                        finalDataRow.add(dataRow[r].toUpperCase().trim());
                    }
                }

                finalDataToBePopulated.add(finalDataRow.toArray(new String[] {}));

            }
        }

        generateCsvFile
                .generate(
                        finalDataToBePopulated,
                        "E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\FINALDATA_DistrictCorrected",
                        "GroundWaterQuality.csv");

    }
}
