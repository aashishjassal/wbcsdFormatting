package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.GroundWaterOtherIndicatorsImpl2013Data;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class BlockCategarizationGenerator.
 * 
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterOtherIndicatorsGenerator2013 {

	private GenerateGroundWaterOtherIndicatorsCsvFile2013 csvGenerator = new GenerateGroundWaterOtherIndicatorsCsvFile2013();

	/**
	 * Generate.
	 * 
	 * @param siteDataMap                 the site data map
	 * @param gWaterOtherIndicatorsData   the block categarization2004 data
	 * @param blockCategarization2009Data the block categarization2009 data
	 * @param blockCategarization2011Data the block categarization2011 data
	 * @param csvGenerationLocation       the csv generation location
	 */
	public void generate(List<String[]> rawColumnData, List<String[]> waterIndicatorsData,
			String csvGenerationLocation) {
		Map<String, GroundWaterOtherIndicatorsImpl2013Data> newDataMap = new HashMap<String, GroundWaterOtherIndicatorsImpl2013Data>();
		System.out.println("Start reading water indicators data for 2013");
		Map<State, Set<String>> stateToDistrictMap = new ConcurrentHashMap<State, Set<String>>();
		State state = null;
		for (String[] newData : waterIndicatorsData) {

			String firstColumn = StringUtils.trim(newData[0]);
			if (StringUtils.isNotBlank(firstColumn) && !StringUtils.isNumeric(firstColumn)) {
				if (State.value(firstColumn) != null) {
					state = State.value(firstColumn);
					stateToDistrictMap.putIfAbsent(state, new TreeSet<String>());
					continue;
				} else {
					System.err.println("Its a state issue: " + firstColumn);
				}
			}
			if (state != null) {
				String district = null;
				GroundWaterOtherIndicatorsImpl2013Data data = new GroundWaterOtherIndicatorsImpl2013Data();
				data.setState(state);
				String secondColumn = StringUtils.trim(newData[1]);
				String thirdColumn = StringUtils.trim(newData[2]);
				String fourthColumn = StringUtils.trim(newData[3]);
				String fifthColumn = StringUtils.trim(newData[4]);
				if (StringUtils.isNotBlank(secondColumn)) {
					district = secondColumn;
					data.setDistrict(district);
				}
				if (StringUtils.isNotBlank(thirdColumn)) {
					data.setNetGWaterAvailable(new BigDecimal(thirdColumn));
				}
				if (StringUtils.isNotBlank(fourthColumn)) {
					data.setProjDemand(new BigDecimal(fourthColumn));
				}
				if (StringUtils.isNotBlank(fifthColumn)) {
					data.setNetGWaterAvailableForFutureIrrigation(new BigDecimal(fifthColumn));
				}
				stateToDistrictMap.get(state).add(district);
				newDataMap.put(SiteInfoUtils.getStateDistrictKey(state.getName(), district), data);
			}

		}

		System.out.println("End reading water indicators data for 2013");

		System.out.println("Now start reading raw data water indicators");

		List<GroundWaterOtherIndicatorsImpl2013Data> listToPopulate = new ArrayList<GroundWaterOtherIndicatorsImpl2013Data>();
		for (int i = 1; i < rawColumnData.size(); i++) {
			String[] data = rawColumnData.get(i);
			String stateColumn = StringUtils.trim(data[3]);
			if (State.value(stateColumn) != null) {
				state = State.value(stateColumn);
				String rawDataDistrict = StringUtils.trim(data[4]);
				String getForwardMappedDistrict = DistrictInfoUtils.getOverriddenNameIfApplicable(rawDataDistrict);
				List<String> getReverseMappedDistrict = DistrictInfoUtils
						.getReverseOverriddenNameIfApplicable(rawDataDistrict);
				boolean forward = populate(newDataMap, state, listToPopulate, rawDataDistrict,
						getForwardMappedDistrict);
				if (!forward) {
					boolean backward = false;
					for (String reverseDistrictName : getReverseMappedDistrict) {
						backward = populate(newDataMap, state, listToPopulate, rawDataDistrict, reverseDistrictName);
						if (backward) {
							// Match found, move on to next row
							break;
						}

					}
					if (!backward) {
						System.err.println("No data found: " + state + " " + rawDataDistrict + ". Possible districts: "
								+ stateToDistrictMap.get(state));
						
						GroundWaterOtherIndicatorsImpl2013Data notMapped = new GroundWaterOtherIndicatorsImpl2013Data();
						notMapped.setState(state);
						notMapped.setDistrict(rawDataDistrict);
						listToPopulate.add(notMapped);
					}
				}
			} else {
				System.err.println("Its a state issue: " + stateColumn);
			}

		}

		System.out.println("End reading raw data water indicators");

		csvGenerator.generate(listToPopulate, csvGenerationLocation, "GroundWaterOtherIndicators-2013.csv");
		System.out.println("all done..............");
	}

	private boolean populate(Map<String, GroundWaterOtherIndicatorsImpl2013Data> newDataMap, State state,
			List<GroundWaterOtherIndicatorsImpl2013Data> listToPopulate, String rawDataDistrictName,
			String districtNameInNewData) {
		String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(state.getName(), districtNameInNewData);
		GroundWaterOtherIndicatorsImpl2013Data dataToPopulate = newDataMap.get(stateDistrictKey);
		if (dataToPopulate == null) {
			return false;
		}
		dataToPopulate.setDistrict(rawDataDistrictName);
		;
		listToPopulate.add(dataToPopulate);
		return true;
	}
}
