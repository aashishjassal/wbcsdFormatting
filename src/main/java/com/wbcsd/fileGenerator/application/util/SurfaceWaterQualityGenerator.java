package com.wbcsd.fileGenerator.application.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.SurfaceWaterDataImpl;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class SurfaceWaterQualityGenerator {

    /** The generate g water other indicators csv file. */
    private final GenerateSurfaceWaterQualityCsvFile generateSurfaceWaterQualityCsvFile;

    public SurfaceWaterQualityGenerator() {
        generateSurfaceWaterQualityCsvFile = new GenerateSurfaceWaterQualityCsvFile();
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param surfaceWaterQualityData the block categarization2004 data
     * @param blockCategarization2009Data the block categarization2009 data
     * @param blockCategarization2011Data the block categarization2011 data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<String, SiteInfo> siteDataMap, List<String[]> surfaceWaterQualityData,
            String csvGenerationLocation) {

        List<SurfaceWaterDataImpl> surfaceWaterQualityList = new LinkedList<SurfaceWaterDataImpl>();
        final Set<String> siteInfoStatePlusDistrictKeys = DistrictInfoUtils.getStatePlusDistrictKey(siteDataMap);
        System.out.println("Reading surface water data");

        int length = surfaceWaterQualityData.size();

        for (int i = 0; i < length; i++) {
            String[] dataLine = surfaceWaterQualityData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String state = dataLine[0].trim();
                String district = dataLine[1].trim();
                district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);
                String observationName = dataLine[2].trim();
                observationName = StringUtils.replace(observationName, "\n", " ");
                String latitude = dataLine[3].trim();
                String longitude = dataLine[4].trim();

                String faecalColiformStr = dataLine[28].trim();
                String totalColiformStr = dataLine[27].trim();
                String dissOxyStr = dataLine[14].trim();
                String bodStr = dataLine[16].trim();
                String nitriteNitrateStr = dataLine[19].trim();
                String condStr = dataLine[11].trim();
                String pHStr = dataLine[10].trim();
                String sarStr = dataLine[26].trim();
                String codStr = dataLine[15].trim();

                if (StringUtils.isNotBlank(state)) {
                    // System.out.println("State is present. This is to be used");
                    State stateToBeUsed = State.value(state);
                    if (stateToBeUsed == null) {
                        System.err.println("Its not a valid state... Review data.." + state);
                        throw new RuntimeException("Its not a valid state... Review data.." + state);
                    }

                    district = DistrictInfoUtils.getBestMatchDistrictName(stateToBeUsed.getName(), district,
                            siteInfoStatePlusDistrictKeys);

                    SurfaceWaterDataImpl data = new SurfaceWaterDataImpl();
                    data.setState(stateToBeUsed);
                    data.setDistrict(district);
                    data.setObsAreaName(observationName);
                    data.setFaecalColiform(faecalColiformStr);
                    data.setTotalColiform(totalColiformStr);
                    data.setDissOxy(dissOxyStr);
                    data.setBod(bodStr);
                    data.setNitriteNitrate(nitriteNitrateStr);
                    data.setConductivity(condStr);
                    data.setpH(pHStr);
                    data.setSar(sarStr);
                    data.setCod(codStr);
                    LatLongImpl latLong = new LatLongImpl();
                    latLong.setLatitude(latitude);
                    latLong.setLongitude(longitude);
                    data.setLatLong(latLong);
                    surfaceWaterQualityList.add(data);
                }
            }

        }

        System.out.println("Reading 2011 data done");

        generateSurfaceWaterQualityCsvFile.generate(surfaceWaterQualityList, csvGenerationLocation,
                "SurfaceWaterQuality.csv");

    }
}
