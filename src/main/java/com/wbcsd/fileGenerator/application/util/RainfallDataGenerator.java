package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.RainfallDataImpl;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class RainfallDataGenerator {

    /** The generate g water other indicators csv file. */
    private final GenerateRainfallDataCsvFile generateRainfallDataCsvFile;

    public RainfallDataGenerator() {
        generateRainfallDataCsvFile = new GenerateRainfallDataCsvFile();
    }

    /**
     * Generate.
     * @param damData the dam data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<String, SiteInfo> siteDataMap, Map<State, List<String[]>> rainfallData,
            Map<State, List<List<String>>> rainfallLatestData, String csvGenerationLocation) {

        Map<String, RainfallDataImpl> rainfallDataMap = new TreeMap<String, RainfallDataImpl>();
        final Set<String> siteInfoStatePlusDistrictKeys = DistrictInfoUtils.getStatePlusDistrictKey(siteDataMap);
        System.out.println("Reading rainfall data");

        for (Map.Entry<State, List<String[]>> entry : rainfallData.entrySet()) {
            State state = entry.getKey();
            List<String[]> data = entry.getValue();

            for (int i = 0; i < data.size(); i++) {
                String[] rowData = data.get(i);

                String district = rowData[1].trim();
                String averageStr = rowData[15].trim();
                String yearStr = rowData[2].trim();

                if (StringUtils.isNotBlank(district)) {
                    Integer year = Integer.parseInt(yearStr);
                    district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);

                    district = DistrictInfoUtils.getBestMatchDistrictName(state.getName(), district,
                            siteInfoStatePlusDistrictKeys);

                    String mapKey = state.getCode() + "_" + district;
                    RainfallDataImpl dataImpl = null;
                    if (rainfallDataMap.containsKey(mapKey)) {
                        dataImpl = rainfallDataMap.get(mapKey);
                    } else {
                        dataImpl = new RainfallDataImpl();
                        dataImpl.setState(state);
                        dataImpl.setDistrict(district);
                        rainfallDataMap.put(mapKey, dataImpl);
                    }

                    List<BigDecimal> sum = new ArrayList<BigDecimal>();
                    // Read Jan(3) to Dec(14) data
                    for (int g = 3; g <= 14; g++) {
                        String monthStr = rowData[g].trim();
                        if (StringUtils.isNotBlank(monthStr) && !StringUtils.containsIgnoreCase(monthStr, "N.A")) {
                            sum.add(BigDecimal.valueOf(Double.valueOf(monthStr)));
                        }
                    }
                    if (!sum.isEmpty()) {
                        BigDecimal average = AverageUtils.getAverage(sum);
                        dataImpl.addYearBasedAverage(year, average);
                    }
                }
            }
        }

        System.out.println("Reading rainfall data done");

        System.out.println("Now reading latest rainfall data");
        for (Map.Entry<State, List<List<String>>> entry : rainfallLatestData.entrySet()) {
            State state = entry.getKey();
            List<List<String>> data = entry.getValue();

            for (int i = 0; i < data.size(); i++) {
                List<String> singleFileData = data.get(i);

                String dataDistrict = "";
                String data2011 = "";
                String data2012 = "";
                String data2013 = "";
                for (String line : singleFileData) {
                    if (StringUtils.startsWithIgnoreCase(line, "District")) {
                        dataDistrict = line;
                    } else if (StringUtils.startsWithIgnoreCase(line, "2011")) {
                        data2011 = line;
                    } else if (StringUtils.startsWithIgnoreCase(line, "2012")) {
                        data2012 = line;
                    } else if (StringUtils.startsWithIgnoreCase(line, "2013")) {
                        data2013 = line;
                    }
                }
                String[] districtToken = StringUtils.split(dataDistrict, ":");
                String[] data2011Token = StringUtils.split(data2011, " ");
                String[] data2012Token = StringUtils.split(data2012, " ");
                String[] data2013Token = StringUtils.split(data2013, " ");

                String district = districtToken[1].trim();
                district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);

                district = DistrictInfoUtils.getBestMatchDistrictName(state.getName(), district,
                        siteInfoStatePlusDistrictKeys);

                String mapKey = state.getCode() + "_" + district;
                RainfallDataImpl dataImpl = null;
                if (rainfallDataMap.containsKey(mapKey)) {
                    dataImpl = rainfallDataMap.get(mapKey);
                } else {
                    dataImpl = new RainfallDataImpl();
                    dataImpl.setState(state);
                    dataImpl.setDistrict(district);
                    rainfallDataMap.put(mapKey, dataImpl);
                }

                int tokens = data2011Token.length;
                List<BigDecimal> sum = new ArrayList<BigDecimal>();
                for (int a = 0; a < tokens; a++) {
                    // Read only odd tokens.
                    if (a % 2 == 0) {

                    } else {
                        sum.add(BigDecimal.valueOf(Double.valueOf(data2011Token[a])));
                    }
                }
                if (!sum.isEmpty()) {
                    BigDecimal average2011 = AverageUtils.getAverage(sum);
                    dataImpl.addYearBasedAverage(2011, average2011);
                }

                tokens = data2012Token.length;
                sum = new ArrayList<BigDecimal>();
                for (int a = 0; a < tokens; a++) {
                    // Read only odd tokens.
                    if (a % 2 == 0) {

                    } else {
                        sum.add(BigDecimal.valueOf(Double.valueOf(data2012Token[a])));
                    }
                }
                if (!sum.isEmpty()) {
                    BigDecimal average2012 = AverageUtils.getAverage(sum);
                    dataImpl.addYearBasedAverage(2012, average2012);
                }

                tokens = data2013Token.length;
                sum = new ArrayList<BigDecimal>();
                for (int a = 0; a < tokens; a++) {
                    // Read only odd tokens.
                    if (a % 2 == 0) {

                    } else {
                        sum.add(BigDecimal.valueOf(Double.valueOf(data2013Token[a])));
                    }
                }
                if (!sum.isEmpty()) {
                    BigDecimal average2013 = AverageUtils.getAverage(sum);
                    dataImpl.addYearBasedAverage(2013, average2013);
                }

            }
        }

        System.out.println("Reading latest rainfall data done");

        generateRainfallDataCsvFile.generate(rainfallDataMap.values(), csvGenerationLocation, "RainfallData.csv");

    }
}
