package com.wbcsd.fileGenerator.application;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.application.util.BlockMapper;
import com.wbcsd.fileGenerator.application.util.GenerateCsvFile;
import com.wbcsd.fileGenerator.excel.CSVDataReader;

/**
 * @author Aashish
 */
public class CorrectBlockNameInBlockCategorizationFile {

    public static void main(String[] args) {
        CSVDataReader dataReader = new CSVDataReader();
        GenerateCsvFile generateCsvFile = new GenerateCsvFile();

        List<String[]> masterDataBlockSheet = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Input\\SUBDIVISION_BDY.csv");

        Map<String, List<String>> stateDistrictToBlockMap = new TreeMap<String, List<String>>();
        for (int i = 0; i < masterDataBlockSheet.size(); i++) {
            if (i != 0) {
                String[] row = masterDataBlockSheet.get(i);
                String state = row[4].trim().toUpperCase();
                String district = row[3].trim().toUpperCase();
                String tehsil = row[1].trim().toUpperCase();
                String subDivision = row[2].trim().toUpperCase();

                if (StringUtils.isNotBlank(state) && StringUtils.isNotBlank(district)) {
                    String key = getMapKey(state, district);
                    List<String> blockColl = new LinkedList<String>();
                    if (stateDistrictToBlockMap.containsKey(key)) {
                        blockColl = stateDistrictToBlockMap.get(key);
                    }
                    if (StringUtils.isNotBlank(tehsil)) {
                        blockColl.add(tehsil);
                    }
                    if (StringUtils.isNotBlank(subDivision)) {
                        blockColl.add(subDivision);
                    }

                    Collections.sort(blockColl);
                    stateDistrictToBlockMap.put(key, blockColl);
                }
            }
        }

        List<String[]> originallyPopulatedData = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\FINALDATA_DistrictCorrected\\BlockCategorization2004-2011.csv");

        List<String[]> finalDataToBePopulated = new LinkedList<String[]>();

        for (int i = 0; i < originallyPopulatedData.size(); i++) {
            if (i == 0) {
                System.out.println("Header");
                finalDataToBePopulated.add(originallyPopulatedData.get(i));
            } else {
                String[] dataRow = originallyPopulatedData.get(i);
                List<String> finalDataRow = new LinkedList<String>();
                String rowState = "";
                String rowDistrict = "";
                for (int r = 0; r < dataRow.length; r++) {
                    if (r == 3) {
                        rowState = dataRow[r].toUpperCase().trim();
                        finalDataRow.add(rowState);
                    } else if (r == 4) {
                        rowDistrict = dataRow[r].toUpperCase().trim();
                        finalDataRow.add(rowDistrict);
                    } else if (r == 5) {
                        String rowBlock = dataRow[r].toUpperCase().trim();
                        String key = getMapKey(rowState, rowDistrict);
                        List<String> masterBlocks = stateDistrictToBlockMap.get(key);
                        if (masterBlocks == null || masterBlocks.isEmpty()) {
                            System.err.println("No master blocks available for: " + key + ", using existing one");
                            finalDataRow.add(rowBlock);
                        } else {
                            String finalBlock = BlockMapper.getOverriddenNameIfApplicable(key, rowBlock, masterBlocks);
                            finalDataRow.add(finalBlock);
                        }
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
                        "E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\FINALDATA_BLOCKCORRECTED",
                        "BlockCategorization2004-2011.csv");

    }

    /**
     * @param state
     * @param district
     * @return
     *         String
     */
    private static String getMapKey(String state, String district) {
        String key = state + "_" + district;
        return key;
    }
}
