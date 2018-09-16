package com.wbcsd.fileGenerator.application.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.SiteType;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;
import com.wbcsd.fileGenerator.api.impl.SiteInfoImpl;

/**
 * The Class MainApplication.
 */
/**
 * @author Aashish
 * @version $Id: $
 */
public class SiteInfoGenerator {

    private final GenerateSiteInfoCsvFile generateSiteInfoCsvFile;

    /**
     * @param generateSiteInfoCsvFile
     */
    public SiteInfoGenerator() {
        this.generateSiteInfoCsvFile = new GenerateSiteInfoCsvFile();
    }

    public void generate(List<String[]> fileData, String csvGenerationLocation) {

        Map<String, SiteInfo> siteDataMap = new LinkedHashMap<String, SiteInfo>();
        int length = fileData.size();
        System.out.println("working on data..");
        for (int i = 0; i < length; i++) {
            String[] dataLine = fileData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String siteId = dataLine[0];
                String siteName = dataLine[1];
                String latitude = dataLine[2];
                String longitude = dataLine[3];
                String type = dataLine[5];
                String state = dataLine[6];
                String district = dataLine[7];
                String block = dataLine[9];

                SiteInfoImpl siteInfo = new SiteInfoImpl();
                siteInfo.setId(siteId);
                siteInfo.setName(siteName);
                LatLongImpl latLongImpl = new LatLongImpl();
                latLongImpl.setLatitude(latitude);
                latLongImpl.setLongitude(longitude);
                siteInfo.setLatLong(latLongImpl);
                siteInfo.setSiteType(SiteType.value(type));
                siteInfo.setState(State.value(state));
                siteInfo.setDistrict(district);
                siteInfo.setBlock(block);
                if (!siteDataMap.containsKey(dataLine)) {
                    siteDataMap.put(siteId, siteInfo);
                }
            }
        }
        generateSiteInfoCsvFile.generate(siteDataMap.values(), csvGenerationLocation, "SiteInfo.csv");
    }
}
