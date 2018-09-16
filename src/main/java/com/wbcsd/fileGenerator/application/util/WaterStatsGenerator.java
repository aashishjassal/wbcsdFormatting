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
public class WaterStatsGenerator {

    private final GenerateWaterStatusCsvFile generateWaterStatsCsvFile;

    /**
     * @param generateWaterStatsCsvFile
     */
    public WaterStatsGenerator() {
        this.generateWaterStatsCsvFile = new GenerateWaterStatusCsvFile();
    }

    public void generate(Map<String, SiteInfo> siteDataMap, List<String[]> fileData, String csvGenerationLocation) {

        Map<String, WaterStatsImpl> waterStatsMap = new LinkedHashMap<String, WaterStatsImpl>();

        int length = fileData.size();
        for (int i = 0; i < length; i++) {
            String[] dataLine = fileData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String siteId = dataLine[0];
                String readingDate = dataLine[11];
                String reading = dataLine[12];

                WaterStatsImpl stats = waterStatsMap.get(siteId);
                if (stats == null) {
                    stats = new WaterStatsImpl();
                    stats.setSiteId(siteId);
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
                        waterStatsMap.put(siteId, stats);
                    } else {
                        System.err.println("No reading available, ignoring for siteId: " + siteId + " year:" + year);
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
                        waterStatsMap.put(siteId, stats);
                    }

                }
            }

        }
        generateWaterStatsCsvFile.generate(siteDataMap, waterStatsMap.values(), csvGenerationLocation,
                "WaterStatus_2012Onwards.csv");
    }
}
