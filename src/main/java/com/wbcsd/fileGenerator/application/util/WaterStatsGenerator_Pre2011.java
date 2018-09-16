package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.impl.WaterStatsImpl;

/**
 * @author Aashish
 * @version $Id: $
 */
public class WaterStatsGenerator_Pre2011 {

    private final GenerateWaterStatusCsvFile generateWaterStatsCsvFile;

    /**
     * @param generateWaterStatsCsvFile
     */
    public WaterStatsGenerator_Pre2011() {
        this.generateWaterStatsCsvFile = new GenerateWaterStatusCsvFile();
    }

    public void generate(Map<String, SiteInfo> siteDataMap_IDBased, Map<String, SiteInfo> siteDataMap,
            List<String[]> fileData, List<String[]> waterFileData2007, String csvGenerationLocation) {

        Map<String, WaterStatsImpl> waterStatsMap = new LinkedHashMap<String, WaterStatsImpl>();

        System.out.println("Reading 2011-2014 data");
        int length = fileData.size();
        for (int i = 0; i < length; i++) {
            String[] dataLine = fileData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String siteName = dataLine[1];
                String state = dataLine[6];
                String district = dataLine[7];
                String block = dataLine[9];

                String mapKey = SiteInfoUtils.getPrimaryKey(siteName, state, district, block);

                String readingDate = dataLine[11];
                String reading = dataLine[12];

                WaterStatsImpl stats = waterStatsMap.get(mapKey);
                if (stats == null) {
                    stats = new WaterStatsImpl();
                    SiteInfo info = siteDataMap.get(mapKey);
                    if (info == null) {
                        System.err.println("No site available for: " + mapKey);
                        continue;
                        // throw new RuntimeException("No site available for: " + mapKey);
                    }
                    stats.setSiteId(info.getId());
                    Integer month = DateUtils.getMonth(readingDate);
                    Integer year = DateUtils.getYear(readingDate);
                    if (StringUtils.isNotBlank(reading)) {
                        BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(reading));
                        if (month >= 1 && month <= 3) {
                            stats.addFirstQuarterReading(year, readingVal);
                        } else if (month >= 4 && month <= 6) {
                            stats.addSecondQuarterReading(year, readingVal);
                        } else if (month >= 7 && month <= 9) {
                            stats.addThirdQuarterReading(year, readingVal);
                        } else {
                            stats.addFourthQuarterReading(year, readingVal);
                        }
                        waterStatsMap.put(mapKey, stats);
                    } else {
                        System.err.println("No reading available, ignoring for siteId: " + info + " year:" + year);
                    }

                } else {
                    Integer month = DateUtils.getMonth(readingDate);
                    Integer year = DateUtils.getYear(readingDate);
                    if (StringUtils.isNotBlank(reading)) {
                        BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(reading));
                        if (month >= 1 && month <= 3) {
                            stats.addFirstQuarterReading(year, readingVal);
                        } else if (month >= 4 && month <= 6) {
                            stats.addSecondQuarterReading(year, readingVal);
                        } else if (month >= 7 && month <= 9) {
                            stats.addThirdQuarterReading(year, readingVal);
                        } else {
                            stats.addFourthQuarterReading(year, readingVal);
                        }
                        SiteInfo info = siteDataMap.get(mapKey);
                        if (info == null) {
                            System.err.println("No site available for: " + mapKey);
                            // throw new RuntimeException("No site available for: " + mapKey);
                            continue;
                        }
                        stats.setSiteId(info.getId());
                        waterStatsMap.put(mapKey, stats);
                    }

                }
            }

        }

        System.out.println("Reading 2007-2011 data");
        length = waterFileData2007.size();
        for (int i = 0; i < length; i++) {
            String[] dataLine = waterFileData2007.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String siteName = dataLine[4];
                String state = dataLine[1];
                String district = dataLine[0];
                String block = dataLine[2];

                String mapKey = SiteInfoUtils.getPrimaryKey(siteName, state, district, block);

                String readingDate = dataLine[3];
                String reading = dataLine[5];

                WaterStatsImpl stats = waterStatsMap.get(mapKey);
                if (stats == null) {
                    stats = new WaterStatsImpl();
                    SiteInfo info = siteDataMap.get(mapKey);
                    if (info == null) {
                        System.err.println("No site available for: " + mapKey);
                        continue;
                        // throw new RuntimeException("No site available for: " + mapKey);
                    }
                    stats.setSiteId(info.getId());
                    Integer month = DateUtils.getMonth(readingDate);
                    Integer year = DateUtils.getYear(readingDate);
                    if (StringUtils.isNotBlank(reading)) {
                        BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(reading));
                        if (month >= 1 && month <= 3) {
                            stats.addFirstQuarterReading(year, readingVal);
                        } else if (month >= 4 && month <= 6) {
                            stats.addSecondQuarterReading(year, readingVal);
                        } else if (month >= 7 && month <= 9) {
                            stats.addThirdQuarterReading(year, readingVal);
                        } else {
                            stats.addFourthQuarterReading(year, readingVal);
                        }
                        waterStatsMap.put(mapKey, stats);
                    } else {
                        System.err.println("No reading available, ignoring for siteId: " + info + " year:" + year);
                    }

                } else {
                    Integer month = DateUtils.getMonth(readingDate);
                    Integer year = DateUtils.getYear(readingDate);
                    if (StringUtils.isNotBlank(reading)) {
                        BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(reading));
                        if (month >= 1 && month <= 3) {
                            stats.addFirstQuarterReading(year, readingVal);
                        } else if (month >= 4 && month <= 6) {
                            stats.addSecondQuarterReading(year, readingVal);
                        } else if (month >= 7 && month <= 9) {
                            stats.addThirdQuarterReading(year, readingVal);
                        } else {
                            stats.addFourthQuarterReading(year, readingVal);
                        }
                        SiteInfo info = siteDataMap.get(mapKey);
                        if (info == null) {
                            System.err.println("No site available for: " + mapKey);
                            // throw new RuntimeException("No site available for: " + mapKey);
                            continue;
                        }
                        stats.setSiteId(info.getId());
                        waterStatsMap.put(mapKey, stats);
                    }

                }
            }

        }

        System.out.println("Reading 2007-2011 data done");

        generateWaterStatsCsvFile.generate(siteDataMap_IDBased, waterStatsMap.values(), csvGenerationLocation,
                "WaterStatus_2007-2011.csv");
    }
}
