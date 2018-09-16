package com.wbcsd.fileGenerator.application.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.GroundWaterQualityImpl;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterQualityGenerator {

    /** The generate g water other indicators csv file. */
    private final GenerateGroundWaterQualityCsvFile generateGWaterQualityCsvFile;

    public GroundWaterQualityGenerator() {
        generateGWaterQualityCsvFile = new GenerateGroundWaterQualityCsvFile();
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param gWaterQualityDataMap the block categarization2004 data
     * @param blockCategarization2009Data the block categarization2009 data
     * @param blockCategarization2011Data the block categarization2011 data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<String, SiteInfo> siteDataMap, Map<String, List<String[]>> gWaterQualityDataMap,
            String csvGenerationLocation) {

        Map<String, GroundWaterQualityImpl> gWaterQualityMap = new TreeMap<String, GroundWaterQualityImpl>();
        final Set<String> siteInfoStatePlusDistrictKeys = DistrictInfoUtils.getStatePlusDistrictKey(siteDataMap);
        System.out.println("Reading data");

        final Set<String> siteInfoKeys = siteDataMap.keySet();

        for (Map.Entry<String, List<String[]>> entrySet : gWaterQualityDataMap.entrySet()) {
            String category = entrySet.getKey();
            List<String[]> dataLines = entrySet.getValue();

            for (String[] dataLine : dataLines) {
                String state = dataLine[1].trim();
                State stateToBeUsed = null;
                if (StringUtils.isNotBlank(state)) {
                    // System.out.println("State is present. This is to be used");
                    stateToBeUsed = State.value(state);
                    if (stateToBeUsed == null) {
                        System.err.println("Its not a valid state... Review data.." + state);
                        throw new RuntimeException("Its not a valid state... Review data.." + state);
                    }
                }
                final List<String> allDistrictsForAState = getAllDistrictsForAState(stateToBeUsed, siteInfoKeys);
                List<String> safeDisticts = Lists.newCopyOnWriteArrayList(allDistrictsForAState);
                List<String> unsafeDistricts = new ArrayList<String>();

                String districtColl = dataLine[2].trim();
                String[] districtToken = StringUtils.split(districtColl, ",");
                for (final String individualToken : districtToken) {
                    String district = individualToken.trim();
                    if (district.equalsIgnoreCase("Chittoor")) {
                        System.out.println("ye");
                    }
                    district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);
                    district = DistrictInfoUtils.getBestMatchDistrictName(stateToBeUsed.getName(), district,
                            siteInfoStatePlusDistrictKeys);

                    if (safeDisticts.contains(district)) {
                        unsafeDistricts.add(district);
                        safeDisticts.remove(district);
                    }
                }

                for (String safe : safeDisticts) {
                    String key = getMapKey(stateToBeUsed, safe);
                    GroundWaterQualityImpl gWaterQuality = null;
                    if (gWaterQualityMap.containsKey(key)) {
                        gWaterQuality = gWaterQualityMap.get(key);
                    } else {
                        gWaterQuality = new GroundWaterQualityImpl();
                        gWaterQuality.setState(stateToBeUsed);
                        gWaterQuality.setDistrict(safe);
                    }

                    if (category.equalsIgnoreCase("Arsenic")) {
                        gWaterQuality.setArsenicBreached(false);
                    } else if (category.equalsIgnoreCase("Chloride")) {
                        gWaterQuality.setChlorideBreached(false);
                    } else if (category.equalsIgnoreCase("EC")) {
                        gWaterQuality.setEcBreached(false);
                    } else if (category.equalsIgnoreCase("Floride")) {
                        gWaterQuality.setFlorideBreached(false);
                    } else if (category.equalsIgnoreCase("Iron")) {
                        gWaterQuality.setIronBreached(false);
                    } else if (category.equalsIgnoreCase("Nitrate")) {
                        gWaterQuality.setNitrateBreached(false);
                    }

                    gWaterQualityMap.put(key, gWaterQuality);
                }

                for (String unsafe : unsafeDistricts) {
                    String key = getMapKey(stateToBeUsed, unsafe);
                    GroundWaterQualityImpl gWaterQuality = null;
                    if (gWaterQualityMap.containsKey(key)) {
                        gWaterQuality = gWaterQualityMap.get(key);
                    } else {
                        gWaterQuality = new GroundWaterQualityImpl();
                        gWaterQuality.setState(stateToBeUsed);
                        gWaterQuality.setDistrict(unsafe);
                    }

                    if (category.equalsIgnoreCase("Arsenic")) {
                        gWaterQuality.setArsenicBreached(true);
                    } else if (category.equalsIgnoreCase("Chloride")) {
                        gWaterQuality.setChlorideBreached(true);
                    } else if (category.equalsIgnoreCase("EC")) {
                        gWaterQuality.setEcBreached(true);
                    } else if (category.equalsIgnoreCase("Floride")) {
                        gWaterQuality.setFlorideBreached(true);
                    } else if (category.equalsIgnoreCase("Iron")) {
                        gWaterQuality.setIronBreached(true);
                    } else if (category.equalsIgnoreCase("Nitrate")) {
                        gWaterQuality.setNitrateBreached(true);
                    }

                    gWaterQualityMap.put(key, gWaterQuality);
                }

            }

        }

        System.out.println("Reading data done");

        generateGWaterQualityCsvFile.generate(gWaterQualityMap.values(), csvGenerationLocation,
                "GroundWaterQuality.csv");

    }

    private String getMapKey(State state, String district) {
        return state.getName() + "_" + district;
    }

    private List<String> getAllDistrictsForAState(State state, Set<String> siteInfoKeys) {
        List<String> allDistricts = new LinkedList<String>();
        for (String siteKey : siteInfoKeys) {
            String[] tokens = StringUtils.split(siteKey, "_");
            String mapKeyState = tokens[1].trim();
            String mapKeyDistrict = tokens[2].trim();

            if (StringUtils.equalsIgnoreCase(mapKeyState, state.getName())) {
                if (!allDistricts.contains(StringUtils.capitalize(mapKeyDistrict))) {
                    allDistricts.add(StringUtils.capitalize(mapKeyDistrict));
                }

            }
        }
        Collections.sort(allDistricts);
        return allDistricts;
    }
}
