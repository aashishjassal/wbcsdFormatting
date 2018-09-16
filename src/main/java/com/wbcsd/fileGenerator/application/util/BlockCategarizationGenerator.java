package com.wbcsd.fileGenerator.application.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.BlockCategarizationImpl;
import com.wbcsd.fileGenerator.api.ExploitationStatus;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * @author Aashish
 * @version $Id: $
 */
public class BlockCategarizationGenerator {
    private final GenerateBlockCategorizationCsvFile generateBlockCategorizationCsvFile;

    /**
     * Instantiates a new block categarization generator.
     */
    public BlockCategarizationGenerator() {
        generateBlockCategorizationCsvFile = new GenerateBlockCategorizationCsvFile();
    }

    /**
     * Generate.
     * @param siteDataMap the site data map
     * @param blockCategarization2004Data the block categarization2004 data
     * @param blockCategarization2009Data the block categarization2009 data
     * @param blockCategarization2011Data the block categarization2011 data
     * @param csvGenerationLocation the csv generation location
     */
    public void generate(Map<String, SiteInfo> siteDataMap, List<String[]> blockCategarization2004Data,
            List<String[]> blockCategarization2009Data, List<String[]> blockCategarization2011Data,
            String csvGenerationLocation) {

        Map<String, BlockCategarizationImpl> blockCategorizationMap = new TreeMap<String, BlockCategarizationImpl>();
        final Set<String> siteInfoKeys = siteDataMap.keySet();
        System.out.println("Reading 2004 data");
        readDataAndUpdateMap(blockCategarization2004Data, blockCategorizationMap, siteInfoKeys, 2004);
        System.out.println("Reading 2004 data done");
        System.out.println("Reading 2009 data");
        readDataAndUpdateMap(blockCategarization2009Data, blockCategorizationMap, siteInfoKeys, 2009);
        System.out.println("Reading 2009 data done");
        System.out.println("Reading 2011 data");
        readDataAndUpdateMap(blockCategarization2011Data, blockCategorizationMap, siteInfoKeys, 2011);
        System.out.println("Reading 2011 data done");

        // Now all the blocks which are in master sheet but not in any block categorization sheet, mark them as safe.
        Set<String> mappedDataKey = blockCategorizationMap.keySet();
        for (String masterDataMapKey : siteInfoKeys) {
            boolean contains = masterDataBlockInGeneratedDate(masterDataMapKey, mappedDataKey);
            if (!contains) {
                String[] tokens = StringUtils.split(masterDataMapKey, "_");
                String mapKeyState = tokens[1].trim();
                String mapKeyDistrict = tokens[2].trim();
                String mapKeyBlock = tokens[3].trim();

                BlockCategarizationImpl blockCat = new BlockCategarizationImpl();

                blockCat.setState(State.value(mapKeyState));
                blockCat.setDistrict(StringUtils.capitalize(mapKeyDistrict));
                blockCat.setBlock(StringUtils.capitalize(mapKeyBlock));
                blockCat.addYearBasedStatus(2004, ExploitationStatus.SAFE);
                blockCat.addYearBasedStatus(2009, ExploitationStatus.SAFE);
                blockCat.addYearBasedStatus(2011, ExploitationStatus.SAFE);

                try {
                    final String mapKey = BlockCategorizationUtils.getPrimaryKey(State.value(mapKeyState).getName(),
                            mapKeyDistrict, mapKeyBlock);
                    blockCategorizationMap.put(mapKey, blockCat);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

            }
        }

        generateBlockCategorizationCsvFile.generate(blockCategorizationMap.values(), csvGenerationLocation,
                "BlockCategorization2004-2011.csv");

    }

    private boolean masterDataBlockInGeneratedDate(final String masterDataKey, Set<String> mappedDataKey) {
        String[] tokens = StringUtils.split(masterDataKey, "_");
        String mapKeyState = tokens[1].trim();
        String mapKeyDistrict = tokens[2].trim();
        String mapKeyBlock = tokens[3].trim();

        for (String alreadyMappedKey : mappedDataKey) {
            String[] tokens2 = StringUtils.split(alreadyMappedKey, "_");
            String alreadymapKeyState = tokens2[0].trim();
            String alreadymapKeyDistrict = tokens2[1].trim();
            String alreadymapKeyBlock = tokens2[2].trim();

            if (StringUtils.equalsIgnoreCase(mapKeyState, alreadymapKeyState)) {
                if (StringUtils.equalsIgnoreCase(mapKeyDistrict, alreadymapKeyDistrict)) {
                    if (StringUtils.equalsIgnoreCase(mapKeyBlock, alreadymapKeyBlock)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @param blockCategorizationData
     * @param blockCategorizationMap
     * @param siteInfoKeys
     *            void
     */
    private void readDataAndUpdateMap(List<String[]> blockCategorizationData,
            Map<String, BlockCategarizationImpl> blockCategorizationMap, final Set<String> siteInfoKeys,
            final Integer year) {
        int length = blockCategorizationData.size();

        State stateToBeUsed = null;
        String districtToBoUsed = null;
        for (int i = 0; i < length; i++) {
            String[] dataLine = blockCategorizationData.get(i);
            if (i == 0) {
                System.out.println("Its header: " + dataLine);
            } else {
                String state = dataLine[0].trim();
                String district = dataLine[1].trim();
                district = DistrictInfoUtils.getOverriddenNameIfApplicable(district);
                String semiCriticalBlock = dataLine[2].trim();
                String criticalBlock = dataLine[3].trim();
                String overExploitedBlock = dataLine[4].trim();

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
                    // System.out.println("District is present. This is to be used");
                    districtToBoUsed = DistrictInfoUtils.getBestMatchDistrictName(stateToBeUsed.getName(), district,
                            siteInfoKeys);
                } else {
                    // System.out.println("District is not present. Use the old district");
                }

                if (StringUtils.isNotBlank(semiCriticalBlock) && !StringUtils.equalsIgnoreCase(semiCriticalBlock, "-")
                        && !StringUtils.equalsIgnoreCase(semiCriticalBlock, "--")
                        && !StringUtils.equals(semiCriticalBlock, "_")) {
                    // Got semi critical blockequalsIgnoreCase
                    String finalBlockName = SiteInfoUtils.getBestMatchBlockName(stateToBeUsed.getName(),
                            districtToBoUsed, semiCriticalBlock, siteInfoKeys);

                    final String mapKey = BlockCategorizationUtils.getPrimaryKey(stateToBeUsed.getName(),
                            districtToBoUsed, finalBlockName);

                    BlockCategarizationImpl blockCat = blockCategorizationMap.get(mapKey);
                    if (blockCat == null) {
                        blockCat = new BlockCategarizationImpl();
                        blockCat.setState(stateToBeUsed);
                        blockCat.setDistrict(districtToBoUsed);
                        blockCat.setBlock(finalBlockName);
                    }

                    blockCat.addYearBasedStatus(year, ExploitationStatus.SEMI_CRITICAL);
                    blockCategorizationMap.put(mapKey, blockCat);
                }
                if (StringUtils.isNotBlank(criticalBlock) && !StringUtils.equals(criticalBlock, "-")
                        && !StringUtils.equals(criticalBlock, "--") && !StringUtils.equals(criticalBlock, "_")) {
                    // Got critical block
                    String finalBlockName = SiteInfoUtils.getBestMatchBlockName(stateToBeUsed.getName(),
                            districtToBoUsed, criticalBlock, siteInfoKeys);

                    final String mapKey = BlockCategorizationUtils.getPrimaryKey(stateToBeUsed.getName(),
                            districtToBoUsed, finalBlockName);

                    BlockCategarizationImpl blockCat = blockCategorizationMap.get(mapKey);
                    if (blockCat == null) {
                        blockCat = new BlockCategarizationImpl();
                        blockCat.setState(stateToBeUsed);
                        blockCat.setDistrict(districtToBoUsed);
                        blockCat.setBlock(finalBlockName);
                    }

                    blockCat.addYearBasedStatus(year, ExploitationStatus.CRITICAL);
                    blockCategorizationMap.put(mapKey, blockCat);
                }
                if (StringUtils.isNotBlank(overExploitedBlock) && !StringUtils.equals(overExploitedBlock, "-")
                        && !StringUtils.equals(overExploitedBlock, "--")
                        && !StringUtils.equals(overExploitedBlock, "_")) {
                    // Got over exploited block
                    String finalBlockName = SiteInfoUtils.getBestMatchBlockName(stateToBeUsed.getName(),
                            districtToBoUsed, overExploitedBlock, siteInfoKeys);

                    final String mapKey = BlockCategorizationUtils.getPrimaryKey(stateToBeUsed.getName(),
                            districtToBoUsed, finalBlockName);

                    BlockCategarizationImpl blockCat = blockCategorizationMap.get(mapKey);
                    if (blockCat == null) {
                        blockCat = new BlockCategarizationImpl();
                        blockCat.setState(stateToBeUsed);
                        blockCat.setDistrict(districtToBoUsed);
                        blockCat.setBlock(finalBlockName);
                    }

                    blockCat.addYearBasedStatus(year, ExploitationStatus.OVER_EXPLOITED);
                    blockCategorizationMap.put(mapKey, blockCat);

                }

            }
        }
    }
}
