package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.GroundWaterOtherIndicatorsImpl;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterOtherIndicatorsGenerator {

    /** The generate g water other indicators csv file. */
    private final GenerateGroundWaterOtherIndicatorsCsvFile generateGWaterOtherIndicatorsCsvFile;

    public GroundWaterOtherIndicatorsGenerator() {
        generateGWaterOtherIndicatorsCsvFile = new GenerateGroundWaterOtherIndicatorsCsvFile();
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param gWaterOtherIndicatorsData the block categarization2004 data
     * @param blockCategarization2009Data the block categarization2009 data
     * @param blockCategarization2011Data the block categarization2011 data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<String, SiteInfo> siteDataMap, List<String[]> gWaterOtherIndicatorsData,
            String csvGenerationLocation) {

        List<GroundWaterOtherIndicatorsImpl> gWaterOtherIndicatorsList = new LinkedList<GroundWaterOtherIndicatorsImpl>();
        final Set<String> siteInfoStatePlusDistrictKeys = DistrictInfoUtils.getStatePlusDistrictKey(siteDataMap);
        System.out.println("Reading 2011 data");

        int length = gWaterOtherIndicatorsData.size();

        State stateToBeUsed = null;
        for (int i = 0; i < length; i++) {
            String[] dataLine = gWaterOtherIndicatorsData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String state = dataLine[0].trim();
                String district = dataLine[1].trim();
                district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);
                String annualGWResourceStr = dataLine[6].trim();
                String currDomesticDemandStr = dataLine[10].trim();
                String projDemandStr = dataLine[12].trim();
                String netGWaterAvailableStr = dataLine[13].trim();
                String netCurrentGWaterAvailableStr = dataLine[8].trim();
                String stageGWaterDevStr = dataLine[14].trim();

                if (StringUtils.isNotBlank(state)) {
                    // System.out.println("State is present. This is to be used");
                    stateToBeUsed = State.value(state);
                    if (stateToBeUsed == null) {
                        System.err.println("Its not a valid state... Review data.." + state);
                        throw new RuntimeException("Its not a valid state... Review data.." + state);
                    }
                } else {
                    // System.out.println("State is not present. Use the old state");
                }

                if (StringUtils.isNotBlank(district)) {

                    district = DistrictInfoUtils.getBestMatchDistrictName(stateToBeUsed.getName(), district,
                            siteInfoStatePlusDistrictKeys);

                    GroundWaterOtherIndicatorsImpl groundWaterOtherIndicatorsImpl = new GroundWaterOtherIndicatorsImpl();
                    groundWaterOtherIndicatorsImpl.setState(stateToBeUsed);
                    groundWaterOtherIndicatorsImpl.setDistrict(district);

                    if (StringUtils.isNotBlank(annualGWResourceStr)
                            && !StringUtils.equalsIgnoreCase(annualGWResourceStr, "-")) {
                        BigDecimal annualGWResource = BigDecimal.valueOf(Double.valueOf(annualGWResourceStr));
                        groundWaterOtherIndicatorsImpl.setAnnualGWResource(annualGWResource);
                    }
                    if (StringUtils.isNotBlank(projDemandStr) && !StringUtils.equalsIgnoreCase(projDemandStr, "-")) {
                        BigDecimal projDemand = BigDecimal.valueOf(Double.valueOf(projDemandStr));
                        groundWaterOtherIndicatorsImpl.setProjDemand(projDemand);
                    }
                    if (StringUtils.isNotBlank(netGWaterAvailableStr)
                            && !StringUtils.equalsIgnoreCase(netGWaterAvailableStr, "-")) {
                        BigDecimal netGWaterAvailable = BigDecimal.valueOf(Double.valueOf(netGWaterAvailableStr));
                        groundWaterOtherIndicatorsImpl.setNetGWaterAvailable(netGWaterAvailable);
                    }
                    if (StringUtils.isNotBlank(stageGWaterDevStr)
                            && !StringUtils.equalsIgnoreCase(stageGWaterDevStr, "-")) {
                        BigDecimal stageGWaterDev = BigDecimal.valueOf(Double.valueOf(stageGWaterDevStr));
                        groundWaterOtherIndicatorsImpl.setStageGWaterDev(stageGWaterDev);
                    }
                    if (StringUtils.isNotBlank(currDomesticDemandStr)
                            && !StringUtils.equalsIgnoreCase(currDomesticDemandStr, "-")) {
                        BigDecimal currDomesticDemand = BigDecimal.valueOf(Double.valueOf(currDomesticDemandStr));
                        groundWaterOtherIndicatorsImpl.setCurrDomesticDemand(currDomesticDemand);
                    }
                    if (StringUtils.isNotBlank(netCurrentGWaterAvailableStr)
                            && !StringUtils.equalsIgnoreCase(netCurrentGWaterAvailableStr, "-")) {
                        BigDecimal netCurrentGWaterAvailable = BigDecimal.valueOf(Double
                                .valueOf(netCurrentGWaterAvailableStr));
                        groundWaterOtherIndicatorsImpl.setNetCurrentGWaterAvailable(netCurrentGWaterAvailable);
                    }
                    gWaterOtherIndicatorsList.add(groundWaterOtherIndicatorsImpl);
                }

            }
        }

        System.out.println("Reading 2011 data done");

        generateGWaterOtherIndicatorsCsvFile.generate(gWaterOtherIndicatorsList, csvGenerationLocation,
                "GroundWaterOtherIndicators-2011.csv");

    }
}
