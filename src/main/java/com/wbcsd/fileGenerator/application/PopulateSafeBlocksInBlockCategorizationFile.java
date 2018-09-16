package com.wbcsd.fileGenerator.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.ExploitationStatus;
import com.wbcsd.fileGenerator.application.util.GenerateCsvFile;
import com.wbcsd.fileGenerator.excel.CSVDataReader;

/**
 * @author Aashish
 */
public class PopulateSafeBlocksInBlockCategorizationFile {

    public static void main(String[] args) {
        CSVDataReader dataReader = new CSVDataReader();
        GenerateCsvFile generateCsvFile = new GenerateCsvFile();

        List<String[]> masterDataBlockSheet = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Input\\SUBDIVISION_BDY.csv");

        Set<String> masterBlockSet = new TreeSet<String>();
        Set<String> childBlockSet = new TreeSet<String>();
        for (int i = 0; i < masterDataBlockSheet.size(); i++) {
            if (i != 0) {
                String[] row = masterDataBlockSheet.get(i);
                String state = row[4].trim().toUpperCase();
                String district = row[3].trim().toUpperCase();
                String tehsil = row[1].trim().toUpperCase();
                String subDivision = row[2].trim().toUpperCase();

                if (StringUtils.isNotBlank(state) && StringUtils.isNotBlank(district)) {
                    if (StringUtils.isNotBlank(tehsil)) {
                        String key = getMapKey(state, district, tehsil);
                        masterBlockSet.add(key);
                    } else if (StringUtils.isNotBlank(subDivision)) {
                        String key = getMapKey(state, district, subDivision);
                        masterBlockSet.add(key);
                    }
                }
            }
        }

        List<String[]> originallyPopulatedData = dataReader
                .readFileData("E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\FINALDATA_BLOCKCORRECTED\\BlockCategorization2004-2011.csv");

        List<String[]> finalDataToBePopulated = new LinkedList<String[]>();
        Map<String, String> stateMap = new HashMap<String, String>();

        for (int i = 0; i < originallyPopulatedData.size(); i++) {
            if (i == 0) {
                System.out.println("Header");
                finalDataToBePopulated.add(originallyPopulatedData.get(i));
            } else {
                finalDataToBePopulated.add(originallyPopulatedData.get(i));
                String[] dataRow = originallyPopulatedData.get(i);
                String rowState = dataRow[3].toUpperCase().trim();
                String rowStateCode = dataRow[2].toUpperCase().trim();
                String rowDistrict = dataRow[4].toUpperCase().trim();
                String rowBlock = dataRow[5].toUpperCase().trim();

                String key = getMapKey(rowState, rowDistrict, rowBlock);
                childBlockSet.add(key);

                stateMap.put(rowState, rowStateCode);

            }

        }

        for (String masterData : masterBlockSet) {
            if (!childBlockSet.contains(masterData)) {
                String[] token = StringUtils.split(masterData, "++");
                String state = token[0];
                String district = token[1];
                String block = token[2];

                List<String> dataList = new ArrayList<String>();
                dataList.add("1");
                dataList.add("INDIA");
                dataList.add(stateMap.get(state));
                dataList.add(state);
                dataList.add(district);
                dataList.add(block);
                dataList.add(ExploitationStatus.SAFE.toString());
                dataList.add(ExploitationStatus.SAFE.toString());
                dataList.add(ExploitationStatus.SAFE.toString());
                finalDataToBePopulated.add(dataList.toArray(new String[] {}));
            }
        }

        generateCsvFile
                .generate(
                        finalDataToBePopulated,
                        "E:\\EclipseWorkspace\\fileGenerator\\src\\main\\resources\\Output\\SiteInfo\\FINALDATA_BLOCKCORRECTED",
                        "BlockCategorization2004-2011_NewSafe.csv");

    }

    /**
     * @param state
     * @param district
     * @return
     *         String
     */
    private static String getMapKey(String state, String district, final String block) {
        String key = state + "++" + district + "++" + block;
        return key;
    }
}
