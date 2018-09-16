package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.WaterStatsticsUpdationImpl;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;

/**
 * @author Aashish
 * @version $Id: $
 */
public class WaterStatsUpdation_2014_15 {

	private final GenerateWaterStatusUpdationCsvFile generateWaterStatsCsvFile;

	/**
	 * @param generateWaterStatsCsvFile
	 */
	public WaterStatsUpdation_2014_15() {
		this.generateWaterStatsCsvFile = new GenerateWaterStatusUpdationCsvFile();
	}

	public void generate(List<String[]> oldFileData, List<String[]> newData, String csvGenerationLocation) {

		Map<String, WaterStatsticsUpdationImpl> waterStatsMap = new LinkedHashMap<String, WaterStatsticsUpdationImpl>();

		Map<LatLongImpl, String> latLongToKeyMap = new LinkedHashMap<LatLongImpl, String>();

		ConcurrentHashMap<String, Set<String>> stateDistrictBlockToSitesMap = new ConcurrentHashMap<String, Set<String>>();
		ConcurrentHashMap<String, Set<String>> stateDistrictToAllBlockMap = new ConcurrentHashMap<String, Set<String>>();

		ConcurrentHashMap<String, Set<String>> stateToAllDistrictsMap = new ConcurrentHashMap<String, Set<String>>();

		ConcurrentHashMap<String, Set<WaterStatsticsUpdationImpl>> sameKeyMultiSites = new ConcurrentHashMap<String, Set<WaterStatsticsUpdationImpl>>();

		System.out.println("Reading existing data");
		int length = oldFileData.size();
		for (int i = 0; i < length; i++) {
			String[] dataLine = oldFileData.get(i);
			if (i == 0) {
				System.out.println("Its header: " + dataLine);
			} else {
				String stateName = dataLine[3];
				String district = dataLine[4].toUpperCase();
				String block = dataLine[5].toUpperCase();
				String siteId = dataLine[6].toUpperCase();
				String siteName = dataLine[7].toUpperCase();

				String lat = dataLine[8];
				String longi = dataLine[9];
				String pre2007 = dataLine[10];
				String post2007 = dataLine[11];
				String pre2008 = dataLine[12];
				String post2008 = dataLine[13];
				String pre2009 = dataLine[14];
				String post2009 = dataLine[15];
				String pre2010 = dataLine[16];
				String post2010 = dataLine[17];
				String pre2011 = dataLine[18];
				String post2011 = dataLine[19];
				String pre2012 = dataLine[20];
				String post2012 = dataLine[21];
				String pre2013 = dataLine[22];
				String post2013 = dataLine[23];

				WaterStatsticsUpdationImpl waterStatsticsUpdation = new WaterStatsticsUpdationImpl();
				waterStatsticsUpdation.setCountry(Country.INDIA);
				waterStatsticsUpdation.setState(State.value(stateName));
				waterStatsticsUpdation.setDistrict(district);
				waterStatsticsUpdation.setBlock(block);
				waterStatsticsUpdation.setId(siteId);
				waterStatsticsUpdation.setName(siteName);
				LatLongImpl latLongImpl = new LatLongImpl();
				latLongImpl.setLatitude(lat);
				latLongImpl.setLongitude(longi);
				waterStatsticsUpdation.setLatLong(latLongImpl);

				addPreReading(2007, pre2007, waterStatsticsUpdation);
				addPostReading(2007, post2007, waterStatsticsUpdation);
				addPreReading(2008, pre2008, waterStatsticsUpdation);
				addPostReading(2008, post2008, waterStatsticsUpdation);
				addPreReading(2009, pre2009, waterStatsticsUpdation);
				addPostReading(2009, post2009, waterStatsticsUpdation);
				addPreReading(2010, pre2010, waterStatsticsUpdation);
				addPostReading(2010, post2010, waterStatsticsUpdation);
				addPreReading(2011, pre2011, waterStatsticsUpdation);
				addPostReading(2011, post2011, waterStatsticsUpdation);
				addPreReading(2012, pre2012, waterStatsticsUpdation);
				addPostReading(2012, post2012, waterStatsticsUpdation);
				addPreReading(2013, pre2013, waterStatsticsUpdation);
				addPostReading(2013, post2013, waterStatsticsUpdation);

				String mapKey = SiteInfoUtils.getPrimaryKey(siteName, waterStatsticsUpdation.getState().getName(),
						district, block);

				if (waterStatsMap.containsKey(mapKey)) {
					System.out.println("already in map: " + mapKey);
					sameKeyMultiSites.putIfAbsent(mapKey, new HashSet<WaterStatsticsUpdationImpl>());
					// Put existing entry
					sameKeyMultiSites.get(mapKey).add(waterStatsMap.get(mapKey));
					// Also put new entry
					sameKeyMultiSites.get(mapKey).add(waterStatsticsUpdation);

					waterStatsMap.remove(mapKey);
				} else {
					waterStatsMap.put(mapKey, waterStatsticsUpdation);
				}

				latLongToKeyMap.put(latLongImpl, mapKey);

				String stateDistrictBlockKey = SiteInfoUtils
						.getStateDistrictBlockKey(waterStatsticsUpdation.getState().getName(), district, block);
				stateDistrictBlockToSitesMap.putIfAbsent(stateDistrictBlockKey, new TreeSet<String>());
				stateDistrictBlockToSitesMap.get(stateDistrictBlockKey).add(siteName);

				String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(waterStatsticsUpdation.getState().getName(),
						district);
				stateDistrictToAllBlockMap.putIfAbsent(stateDistrictKey, new TreeSet<String>());
				stateDistrictToAllBlockMap.get(stateDistrictKey).add(block);

				String stateKey = SiteInfoUtils.getStateKey(waterStatsticsUpdation.getState().getName());
				stateToAllDistrictsMap.putIfAbsent(stateKey, new TreeSet<String>());
				stateToAllDistrictsMap.get(stateKey).add(district);
			}
		}
		Map<String, List<String>> stateToDistrictMap = DistrictMapper.getStateToDistrictMap();
		Map<String, List<String>> stateDistrictToBlockMap = BlockMapper.getStateDistrictToBlockMap();

		Map<String, Set<String>> notFoundKeys = new LinkedHashMap<String, Set<String>>();
		Map<String, Set<String>> notFoundBlockKeys = new LinkedHashMap<String, Set<String>>();
		Map<String, Set<String>> notFoundDistrictKeys = new LinkedHashMap<String, Set<String>>();
		System.out.println("Now reading new data, old data count: " + waterStatsMap.size());
		System.out.println("Same key multi data count: " + sameKeyMultiSites.size());
		length = newData.size();
		for (int i = 0; i < length; i++) {
			String[] dataLine = newData.get(i);
			if (i == 0) {
				System.out.println("Its header: " + dataLine);
			} else {
				String stateName = dataLine[2].toUpperCase();
				String district = dataLine[3].toUpperCase();
				String block = dataLine[5].toUpperCase();
				String siteName = dataLine[6].toUpperCase();

				String lat = dataLine[0];
				String longi = dataLine[1];

				LatLongImpl newDataLatLong = new LatLongImpl();
				newDataLatLong.setLatitude(lat);
				newDataLatLong.setLongitude(longi);

				List<String> masterDistricts = stateToDistrictMap.get(stateName);
				String finalDistrict = DistrictMapper.getOverriddenNameIfApplicable(stateName, district,
						masterDistricts);
				String key = BlockMapper.getMapKey(stateName, finalDistrict);
				List<String> masterBlocks = stateDistrictToBlockMap.get(key);
				String finalBlock = BlockMapper.getOverriddenNameIfApplicable(key, block, masterBlocks);

				String mapKey = SiteInfoUtils.getPrimaryKey(siteName, stateName, finalDistrict, finalBlock);
				WaterStatsticsUpdationImpl existingWaterStats = waterStatsMap.get(mapKey);
				if (existingWaterStats == null) {
					// System.err.println("Existing water stats null for this
					// key: " + mapKey);
					if (stateName.equalsIgnoreCase("Andaman and Nicobar Island")) {
						// System.out.println("Its not andaman");
						continue;
					} else if (sameKeyMultiSites.containsKey(mapKey)) {
						System.out.println("Entry available in same key multi sites data: " + mapKey);
						Set<WaterStatsticsUpdationImpl> allsites = sameKeyMultiSites.get(mapKey);
						for (WaterStatsticsUpdationImpl site : allsites) {
							if (site.getLatLong().equals(newDataLatLong)) {
								System.out.println("same lat long");
								populateData(dataLine, stateName, site);
							}
						}
					} else if (latLongToKeyMap.containsKey(newDataLatLong)) {
						// System.out.println("Found match with latlong, so
						// there is a site name mismatch, New data Key: "
						// + mapKey + ". Old data key: " +
						// latLongToKeyMap.get(newDataLatLong));
						existingWaterStats = waterStatsMap.get(latLongToKeyMap.get(newDataLatLong));
					}

				}
				if (existingWaterStats != null) {

					populateData(dataLine, stateName, existingWaterStats);

				}

				else {
					if (!notFoundKeys.containsKey(mapKey)) {
						String stateDistrictBlockKey = SiteInfoUtils.getStateDistrictBlockKey(stateName, finalDistrict,
								finalBlock);
						if (stateDistrictBlockToSitesMap.get(stateDistrictBlockKey) == null) {
							String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(stateName, finalDistrict);
							if (stateDistrictToAllBlockMap.get(stateDistrictKey) == null) {
								String stateKey = SiteInfoUtils.getStateKey(stateName);
								notFoundDistrictKeys.put(mapKey, stateToAllDistrictsMap.get(stateKey));
							} else {
								notFoundBlockKeys.put(mapKey, stateDistrictToAllBlockMap.get(stateDistrictKey));
							}
						} else {
							notFoundKeys.put(mapKey, stateDistrictBlockToSitesMap.get(stateDistrictBlockKey));
						}
					}
				}
			}

		}

		for (Map.Entry<String, Set<String>> s : notFoundKeys.entrySet()) {
			System.err
					.println("Exisiting water stats null for this key: " + s.getKey() + " All sites: " + s.getValue());

		}

		for (Map.Entry<String, Set<String>> s : notFoundBlockKeys.entrySet()) {
			System.err.println("No blocks for this key: " + s.getKey() + " All blocks: " + s.getValue());

		}

		for (Map.Entry<String, Set<String>> s : notFoundDistrictKeys.entrySet()) {
			System.err.println("No districts for this key: " + s.getKey() + " All districts: " + s.getValue());

		}
		System.out.println("Reading all data done, all count: " + waterStatsMap.size());
		System.out.println("Same key multi data count: " + sameKeyMultiSites.size());
		// Collections.addAll(waterStatsMap.values(), waterStatsMap.values());
		Collection<WaterStatsticsUpdationImpl> coll = new ArrayList<WaterStatsticsUpdationImpl>();
		coll.addAll(waterStatsMap.values());
		for (Set<WaterStatsticsUpdationImpl> w : sameKeyMultiSites.values()) {
			coll.addAll(w);
		}

		generateWaterStatsCsvFile.generate(coll, csvGenerationLocation, "WaterStatus_2007-2015.csv");

	}

	private void populateData(String[] dataLine, String stateName, WaterStatsticsUpdationImpl existingWaterStats) {
		// System.out.println("Existing water stats found");
		String readingDate = dataLine[7];
		// System.out.println("Date: " + readingDate);
		Integer month = DateUtils.getMonth(readingDate);
		Integer year = DateUtils.getYear(readingDate);
		if (stateName.equalsIgnoreCase(State.ARUNACHAL_PRADESH.getName())
				|| stateName.equalsIgnoreCase(State.ASSAM.getName())
				|| stateName.equalsIgnoreCase(State.MANIPUR.getName())
				|| stateName.equalsIgnoreCase(State.MEGHALAYA.getName())
				|| stateName.equalsIgnoreCase(State.NAGALAND.getName())
				|| stateName.equalsIgnoreCase(State.TRIPURA.getName())) {
			// System.out.println("Its a NE state, reading March/Nov
			// Reading");
			if (month == 3) {
				// Correct reading
				String readingValue = dataLine[8];
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				String readingValue = dataLine[8];
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				System.out.println("Its a NE state, only March/Nov Reading applicable, this month: " + month);
			}
		} else if (stateName.equalsIgnoreCase(State.ORRISA.getName())
				|| stateName.equalsIgnoreCase(State.WEST_BENGAL.getName())
				|| stateName.equalsIgnoreCase(State.KERALA.getName())) {
			// System.out.println("Its an april state, reading
			// April/Nov Reading");
			if (month == 4) {
				// Correct reading
				String readingValue = dataLine[8];
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				String readingValue = dataLine[8];
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				System.out.println("Its an april state, only april/Nov Reading applicable, this month: " + month);
			}
		} else {
			// System.out.println("Its rest of India state, reading
			// May/Nov Reading");
			if (month == 5) {
				// Correct reading
				String readingValue = dataLine[8];
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				String readingValue = dataLine[8];
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				System.out.println("Its rest of India state, only May/Nov Reading applicable, this month: " + month);
			}
		}
	}

	private void addPreReading(Integer year, String preReading, WaterStatsticsUpdationImpl waterStatsticsUpdation) {
		if (StringUtils.isNotBlank(preReading)) {
			BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(preReading));
			waterStatsticsUpdation.addPreMonsoonReading(year, readingVal);
		}
	}

	private void addPostReading(Integer year, String postReading, WaterStatsticsUpdationImpl waterStatsticsUpdation) {
		if (StringUtils.isNotBlank(postReading)) {
			BigDecimal readingVal = BigDecimal.valueOf(Double.valueOf(postReading));
			waterStatsticsUpdation.addPostMonsoonReading(year, readingVal);
		}
	}
}
