package com.wbcsd.fileGenerator.application.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.BlockCategarizationImpl;
import com.wbcsd.fileGenerator.api.ExploitationStatus;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * 
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterQualityGenerator_v2013 {

	private final GenerateBlockCategorizationCsvFile generateBlockCategorizationCsvFile;

	private static final Map<String, List<String>> districtsNotInOldDataButInNewData = new HashMap<String, List<String>>();

	static {
		districtsNotInOldDataButInNewData.put("Chhattisgarh", Lists.newArrayList("Balod", "Bemetara", "Gariaband"));
	}

	public GroundWaterQualityGenerator_v2013() {
		generateBlockCategorizationCsvFile = new GenerateBlockCategorizationCsvFile();
	}

	/**
	 * Generate. 
	 * 
	 * @param siteDataMap                 the site data map
	 * @param gWaterQualityDataMap        the block categarization2004 data
	 * @param blockCategarization2009Data the block categarization2009 data
	 * @param blockCategarization2011Data the block categarization2011 data
	 * @param csvGenerationLocation       the csv generation location
	 */
	public void generate(List<String[]> oldFileData, List<String[]> newFileData, String csvGenerationLocation) {
		Map<String, BlockCategarizationImpl> blockCategorizationMap = new TreeMap<String, BlockCategarizationImpl>();
		System.out.println("Reading existing data");
		for (String[] oldData : oldFileData) {

			String stateCode = oldData[2].trim();
			State state = State.valueByCode(stateCode);
			if (state == null) {
				throw new RuntimeException("Invalid state: " + stateCode);
			} else {
				String district = oldData[4].trim();
				String block = oldData[5].trim();
				String status_2004Code = oldData[6].trim();
				ExploitationStatus status_2004 = ExploitationStatus.value(status_2004Code);
				if (!status_2004Code.isEmpty() && status_2004 == null) {
					throw new RuntimeException("Invalid 2004 status: " + status_2004Code);
				}
				String status_2009Code = oldData[7].trim();
				ExploitationStatus status_2009 = ExploitationStatus.value(status_2009Code);
				if (!status_2009Code.isEmpty() && status_2009 == null) {
					throw new RuntimeException("Invalid 2009 status: " + status_2009Code);
				}
				String status_2011Code = oldData[8].trim();
				ExploitationStatus status_2011 = ExploitationStatus.value(status_2011Code);
				if (!status_2011Code.isEmpty() && status_2011 == null) {
					throw new RuntimeException("Invalid 2011 status: " + status_2011Code);
				}

				BlockCategarizationImpl blockCategarization = new BlockCategarizationImpl();
				blockCategarization.setState(state);
				blockCategarization.setDistrict(district);
				blockCategarization.setBlock(block);
				blockCategarization.addYearBasedStatus(2004, status_2004);
				blockCategarization.addYearBasedStatus(2009, status_2009);
				blockCategarization.addYearBasedStatus(2011, status_2011);
				// Put default of safe for 2013
				blockCategarization.addYearBasedStatus(2013, ExploitationStatus.SAFE);

				final String mapKey = BlockCategorizationUtils.getPrimaryKey(state.getName(), district, block);
				if (blockCategorizationMap.containsKey(mapKey)) {
//					System.out.println("Key already available, merging data: " + mapKey);
					BlockCategarizationImpl oldcatData = blockCategorizationMap.get(mapKey);
					handleDuplicateRecords(oldcatData, 2004, status_2004);
					handleDuplicateRecords(oldcatData, 2009, status_2009);
					handleDuplicateRecords(oldcatData, 2011, status_2011);
				} else {
					blockCategorizationMap.put(mapKey, blockCategarization);
				}

			}

		}
		System.out.println("Existing data read complete: " + blockCategorizationMap.size());
		Set<String> allKeys = blockCategorizationMap.keySet();

		final ConcurrentHashMap<String, List<String>> stateDistrictToBlocks = new ConcurrentHashMap<String, List<String>>();
		for (String mapKey : allKeys) {
			String[] tokens = StringUtils.split(mapKey, "_");
			String mapKeyState = tokens[0].trim();
			String mapKeyDistrict = tokens[1].trim();
			String mapKeyBlock = tokens[2].trim();
			String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(mapKeyState, mapKeyDistrict);
			stateDistrictToBlocks.putIfAbsent(stateDistrictKey, new ArrayList<String>());
			stateDistrictToBlocks.get(stateDistrictKey).add(mapKeyBlock);
		}

		System.out.println("Now reading new data for 2013");
		State state = null;
		String district = null;
		for (String[] newData : newFileData) {

			String firstColumn = StringUtils.trim(newData[0]);
			if (State.value(firstColumn) != null) {
				state = State.value(firstColumn);
				district = null;
				continue;
			}
			if (state != null) {
				String secondColumn = StringUtils.trim(newData[1]);
				String fourthColumn = StringUtils.trim(newData[3]);
				String sixthColumn = StringUtils.trim(newData[5]);
				String eigthColumn = StringUtils.trim(newData[7]);
				String tenthColumn = StringUtils.trim(newData[9]);
				if (StringUtils.isNotBlank(secondColumn)) {
					district = DistrictInfoUtils.getOverriddenNameIfApplicable(secondColumn);
				}

				if (StringUtils.isNotBlank(fourthColumn)) {
					populateValue(blockCategorizationMap, allKeys, stateDistrictToBlocks, state, district, fourthColumn,
							ExploitationStatus.SEMI_CRITICAL);
				}
				if (StringUtils.isNotBlank(sixthColumn)) {
					populateValue(blockCategorizationMap, allKeys, stateDistrictToBlocks, state, district, sixthColumn,
							ExploitationStatus.CRITICAL);
				}
				if (StringUtils.isNotBlank(eigthColumn)) {
					populateValue(blockCategorizationMap, allKeys, stateDistrictToBlocks, state, district, eigthColumn,
							ExploitationStatus.OVER_EXPLOITED);
				}
				if (StringUtils.isNotBlank(tenthColumn)) {
					populateValue(blockCategorizationMap, allKeys, stateDistrictToBlocks, state, district, tenthColumn,
							ExploitationStatus.SALINE);
				}

			}

		}

		System.out.println("New data generation complete: " + blockCategorizationMap.size());

		generateBlockCategorizationCsvFile.generate(blockCategorizationMap.values(), csvGenerationLocation,
				"BlockCategorization2004-2013.csv");

	}

	private void populateValue(Map<String, BlockCategarizationImpl> blockCategorizationMap, Set<String> allKeys,
			final ConcurrentHashMap<String, List<String>> stateDistrictToBlocks, State state, String district,
			String columnValue, ExploitationStatus status) {
		String block = SiteInfoUtils.getBestMatchBlockNameWithoutCountryInKey(state.getName(), district, columnValue,
				allKeys);

		final String mapKey = BlockCategorizationUtils.getPrimaryKey(state.getName(), district, block);

		try {
			BlockCategarizationImpl blockCategarizationImpl = blockCategorizationMap.get(mapKey);

			blockCategarizationImpl.addYearBasedStatus(2013, status);
		} catch (Exception e) {
			System.err.println("error: " + mapKey);
			printPossibleBlocks(stateDistrictToBlocks, state.getName(), district, block);

			// Populate this new block
			BlockCategarizationImpl blockCategarization = new BlockCategarizationImpl();
			blockCategarization.setState(state);
			blockCategarization.setDistrict(district);
			blockCategarization.setBlock(block);
			blockCategarization.addYearBasedStatus(2013, status);
			blockCategorizationMap.put(mapKey, blockCategarization);
		}
	}

	private void printPossibleBlocks(ConcurrentHashMap<String, List<String>> stateDistrictToBlocks, String state,
			String district, String block) {

		String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(state, district);
		List<String> list = stateDistrictToBlocks.get(stateDistrictKey);

		if (list == null) {
			if (districtsNotInOldDataButInNewData.get(state).contains(district)) {
				System.out.println("Ignored district");
				return;
			}
		}
		System.out.println("Not found block: " + block + " All blocks are: " + list);
		for (String allBlockListName : list) {
			if (StringUtils.substring(allBlockListName, 0, 4).equalsIgnoreCase(StringUtils.substring(block, 0, 4))
					|| StringUtils.substring(allBlockListName, allBlockListName.length() - 4, allBlockListName.length())
							.equalsIgnoreCase(StringUtils.substring(block, block.length() - 4, block.length()))) {
				// If first 4 or last 4 chars match, its a possible match
				System.out.println("POSSIBLE MATCH. Block: " + block + " Matching block: " + allBlockListName);

			}
		}
	}

	private void handleDuplicateRecords(BlockCategarizationImpl oldcatData, Integer year,
			ExploitationStatus newStatus) {
		Map<Integer, ExploitationStatus> oldYearData = oldcatData.getYearBasedAverage();

		if (oldYearData.get(year) != newStatus) {
			if (newStatus == null) {
//				System.out.println("Retaining old record data for: " + year);
			} else {
//				System.out.println("Using new record data for: " + year);
				oldcatData.addYearBasedStatus(2004, newStatus);
			}
		}
	}

}
