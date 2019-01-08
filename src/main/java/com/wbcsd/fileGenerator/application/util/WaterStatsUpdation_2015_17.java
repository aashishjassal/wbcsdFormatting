package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

import com.wbcsd.fileGenerator.api.Country;
import com.wbcsd.fileGenerator.api.LatLong;
import com.wbcsd.fileGenerator.api.State;
import com.wbcsd.fileGenerator.api.WaterStatsticsUpdationImpl;
import com.wbcsd.fileGenerator.api.impl.LatLongImpl;

/**
 * @author Aashish
 * @version $Id: $
 */
public class WaterStatsUpdation_2015_17 {

	private final GenerateWaterStatusUpdationCsvFile generateWaterStatsCsvFile;

	/**
	 * @param generateWaterStatsCsvFile
	 */
	public WaterStatsUpdation_2015_17() {
		this.generateWaterStatsCsvFile = new GenerateWaterStatusUpdationCsvFile();
	}

	private class Site implements Comparable<Site> {
		private String name;
		private LatLong latLong;

		public Site(String name, LatLong latLong) {
			super();
			this.name = name;
			this.latLong = latLong;
		}

		public String getName() {
			return name;
		}

		public LatLong getLatLong() {
			return latLong;

		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public int compareTo(Site o) {
			return this.getName().compareTo(o.getName());
		}

	}

	public void generate(List<String[]> oldFileData, List<String[]> newData2015PostMonsoon,
			List<String[]> newData2016_17, String csvGenerationLocation) {

		Map<String, WaterStatsticsUpdationImpl> waterStatsMap = new LinkedHashMap<String, WaterStatsticsUpdationImpl>();

		Map<LatLongImpl, String> latLongToKeyMap = new LinkedHashMap<LatLongImpl, String>();

		ConcurrentHashMap<String, Set<Site>> stateDistrictBlockToSitesMap = new ConcurrentHashMap<String, Set<Site>>();
		ConcurrentHashMap<String, Set<String>> stateDistrictToAllBlockMap = new ConcurrentHashMap<String, Set<String>>();
		Map<String, String> mapKey_SiteIdMap = new HashMap<String, String>();
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
				String pre2014 = dataLine[24];
				String post2014 = dataLine[25];
				String pre2015 = dataLine[26];
				String post2015 = dataLine[27];

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
				addPreReading(2014, pre2014, waterStatsticsUpdation);
				addPostReading(2014, post2014, waterStatsticsUpdation);

				// Some existing sites have 2015 data populated, so lets retain it as this will
				// be helpful in getting existing data if data not in new data
				addPreReading(2015, pre2015, waterStatsticsUpdation);
				addPostReading(2015, post2015, waterStatsticsUpdation);

				String mapKey = SiteInfoUtils.getPrimaryKey(siteName, waterStatsticsUpdation.getState().getName(),
						district, block);

				if (waterStatsMap.containsKey(mapKey)) {
					System.out.println("already in map: " + mapKey + " Existing siteId: " + mapKey_SiteIdMap.get(mapKey)
							+ " new SiteId: " + siteId + " MATCH: "
							+ siteId.equalsIgnoreCase(mapKey_SiteIdMap.get(mapKey)));
					sameKeyMultiSites.putIfAbsent(mapKey, new HashSet<WaterStatsticsUpdationImpl>());
					// Put existing entry
					sameKeyMultiSites.get(mapKey).add(waterStatsMap.get(mapKey));
					// Also put new entry
					sameKeyMultiSites.get(mapKey).add(waterStatsticsUpdation);

					waterStatsMap.remove(mapKey);
				} else {
					waterStatsMap.put(mapKey, waterStatsticsUpdation);
					mapKey_SiteIdMap.put(mapKey, siteId);
				}

				latLongToKeyMap.put(latLongImpl, mapKey);

				String stateDistrictBlockKey = SiteInfoUtils
						.getStateDistrictBlockKey(waterStatsticsUpdation.getState().getName(), district, block);
				stateDistrictBlockToSitesMap.putIfAbsent(stateDistrictBlockKey, new TreeSet<Site>());
				stateDistrictBlockToSitesMap.get(stateDistrictBlockKey).add(new Site(siteName, latLongImpl));

				String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(waterStatsticsUpdation.getState().getName(),
						district);
				stateDistrictToAllBlockMap.putIfAbsent(stateDistrictKey, new TreeSet<String>());
				stateDistrictToAllBlockMap.get(stateDistrictKey).add(block);

				String stateKey = SiteInfoUtils.getStateKey(waterStatsticsUpdation.getState().getName());
				stateToAllDistrictsMap.putIfAbsent(stateKey, new TreeSet<String>());
				stateToAllDistrictsMap.get(stateKey).add(district);
			}
		}
		Map<String, WaterStatsticsUpdationImpl> newData = new LinkedHashMap<String, WaterStatsticsUpdationImpl>();
		Map<String, Set<String>> notFoundBlockKeys = new HashMap<String, Set<String>>();

//		waterStatsMap.get("vaizapoor 2010pz1_telangana_adilabad_adilabad_");

		System.out.println("Now reading new data for 2015 post Monsoon, old data count: " + waterStatsMap.size());
		System.out.println("Same key multi data count: " + sameKeyMultiSites.size());
		populateNewData(newData2015PostMonsoon, waterStatsMap, latLongToKeyMap, stateDistrictBlockToSitesMap,
				stateDistrictToAllBlockMap, stateToAllDistrictsMap, sameKeyMultiSites, newData, notFoundBlockKeys);

		System.out.println("Reading all data done for 2015 post monsoon, all count: " + waterStatsMap.size());
		System.out.println("Same key multi data count: " + sameKeyMultiSites.size());
		System.out.println("New data count: " + newData.size());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("TEST: "
//				+ newData.get("durgapur_andaman and nicobar_north_middle_andaman_diglipur_").getPostMonsoonReading()
//				+ " "
//				+ newData.get("durgapur_andaman and nicobar_north_middle_andaman_diglipur_").getPreMonsoonReading());
		// Collections.addAll(waterStatsMap.values(), waterStatsMap.values());
//		waterStatsMap.get("vaizapoor 2010pz1_telangana_adilabad_adilabad_");

		System.out.println("Now reading new data for 2016 and 2017");

		populateNewData(newData2016_17, waterStatsMap, latLongToKeyMap, stateDistrictBlockToSitesMap,
				stateDistrictToAllBlockMap, stateToAllDistrictsMap, sameKeyMultiSites, newData, notFoundBlockKeys);

		System.out.println("Reading all data done for 2016 and 2017, all count: " + waterStatsMap.size());
		System.out.println("Same key multi data count: " + sameKeyMultiSites.size());
		System.out.println("New data count: " + newData.size());

		List<WaterStatsticsUpdationImpl> coll = new ArrayList<WaterStatsticsUpdationImpl>();
		coll.addAll(waterStatsMap.values());
		for (Set<WaterStatsticsUpdationImpl> w : sameKeyMultiSites.values()) {
			coll.addAll(w);
		}
		coll.addAll(newData.values());
		System.out.println("Final Data size: " + coll.size());

		coll.sort((o1, o2) -> {
			return new CompareToBuilder().append(o1.getState(), o2.getState())
					.append(o1.getDistrict(), o2.getDistrict()).append(o1.getBlock(), o2.getBlock())
					.append(o1.getId(), o2.getId()).toComparison();
		});

		generateWaterStatsCsvFile.generate(coll, csvGenerationLocation, "WaterStatus_2007-2017.csv");
		System.out.println("CSV generation done");
	}

	private void populateNewData(List<String[]> newData, Map<String, WaterStatsticsUpdationImpl> waterStatsMap,
			Map<LatLongImpl, String> latLongToKeyMap, ConcurrentHashMap<String, Set<Site>> stateDistrictBlockToSitesMap,
			ConcurrentHashMap<String, Set<String>> stateDistrictToAllBlockMap,
			ConcurrentHashMap<String, Set<String>> stateToAllDistrictsMap,
			ConcurrentHashMap<String, Set<WaterStatsticsUpdationImpl>> sameKeyMultiSites,
			Map<String, WaterStatsticsUpdationImpl> newDataToPopulate, Map<String, Set<String>> notFoundBlockKeys) {
		int length;
		length = newData.size();
		for (int i = 0; i < length; i++) {

			String[] dataLine = newData.get(i);
			if (i == 0) {
				System.out.println("Its header: " + dataLine);
			} else {
				String stateName = dataLine[4].toUpperCase();
				String district = dataLine[5].toUpperCase();
				String block = dataLine[7].toUpperCase();
				String siteName = dataLine[8].toUpperCase();

				String lat = dataLine[0];
				String longi = dataLine[1];

				LatLongImpl newDataLatLong = new LatLongImpl();
				newDataLatLong.setLatitude(lat);
				newDataLatLong.setLongitude(longi);

				String finalDistrict = DistrictInfoUtils.getOverriddenNameIfApplicable(district);
				String key = BlockMapper.getMapKey(stateName, finalDistrict);
				String finalBlock = BlockMapper.getOverriddenNameIfApplicable(key, block, new ArrayList<String>());

				String mapKey = SiteInfoUtils.getPrimaryKey(siteName, stateName, finalDistrict, finalBlock);
				WaterStatsticsUpdationImpl existingWaterStats = waterStatsMap.get(mapKey);
//				if (siteName.startsWith("VAIZAPOOR 2010PZ")) {
//					System.out.println("aaa");
//				}
				if (existingWaterStats == null) {
					// System.err.println("Existing water stats null for this
					// key: " + mapKey);
					if (stateName.equalsIgnoreCase("Andaman and Nicobar")) {
//						System.out.println("Its andaman");
						continue;
					} else if (sameKeyMultiSites.containsKey(mapKey)) {
//						System.out.println("Entry available in same key multi sites data: " + mapKey);
						Set<WaterStatsticsUpdationImpl> allsites = sameKeyMultiSites.get(mapKey);
						for (WaterStatsticsUpdationImpl site : allsites) {
							if (site.getLatLong().equals(newDataLatLong)) {
//								System.out.println("same lat long: " + mapKey + " site: " + site.getId());
								populateData(dataLine, stateName, site);

							}
						}
					} else if (latLongToKeyMap.containsKey(newDataLatLong)) {
						existingWaterStats = waterStatsMap.get(latLongToKeyMap.get(newDataLatLong));
					}

				}
				if (existingWaterStats != null) {
					populateData(dataLine, stateName, existingWaterStats);
				}

				else {
					if (!newDataToPopulate.containsKey(mapKey)) {
						String stateDistrictBlockKey = SiteInfoUtils.getStateDistrictBlockKey(stateName, finalDistrict,
								finalBlock);
						if (stateDistrictBlockToSitesMap.get(stateDistrictBlockKey) == null) {
							String stateDistrictKey = SiteInfoUtils.getStateDistrictKey(stateName, finalDistrict);
							if (stateDistrictToAllBlockMap.get(stateDistrictKey) == null) {
//								String stateKey = SiteInfoUtils.getStateKey(stateName);
//								System.err.println("No districts: " + finalDistrict + " All districts: "
//										+ stateToAllDistrictsMap.get(stateKey));

								generateNewEntry(newDataToPopulate, dataLine, stateName, siteName, lat, longi,
										finalDistrict, finalBlock, mapKey);
								// notFoundDistrictKeys.put(mapKey, stateToAllDistrictsMap.get(stateKey));
							} else {
								if (!notFoundBlockKeys.containsKey(finalBlock)) {
									System.err.println("No blocks: " + finalBlock + " All blocks: "
											+ stateDistrictToAllBlockMap.get(stateDistrictKey));
									notFoundBlockKeys.put(finalBlock, stateDistrictToAllBlockMap.get(stateDistrictKey));

								}
								generateNewEntry(newDataToPopulate, dataLine, stateName, siteName, lat, longi,
										finalDistrict, finalBlock, mapKey);

							}
						} else {
							generateNewEntry(newDataToPopulate, dataLine, stateName, siteName, lat, longi,
									finalDistrict, finalBlock, mapKey);

						}
					} else {
						populateData(dataLine, stateName, newDataToPopulate.get(mapKey));
					}
				}
			}

		}

	}

	private void generateNewEntry(Map<String, WaterStatsticsUpdationImpl> newDataToPopulate, String[] dataLine,
			String stateName, String siteName, String lat, String longi, String finalDistrict, String finalBlock,
			String mapKey) {
		WaterStatsticsUpdationImpl waterStatsticsUpdation = new WaterStatsticsUpdationImpl();
		waterStatsticsUpdation.setCountry(Country.INDIA);
		waterStatsticsUpdation.setState(State.value(stateName));
		waterStatsticsUpdation.setDistrict(finalDistrict);
		waterStatsticsUpdation.setBlock(finalBlock);
		waterStatsticsUpdation.setName(siteName);
		LatLongImpl latLongImpl = new LatLongImpl();
		latLongImpl.setLatitude(lat);
		latLongImpl.setLongitude(longi);
		String custSiteId = "CUST" + latLongImpl.getLatAsIngt() + latLongImpl.getLongAsIngt();
		waterStatsticsUpdation.setId(custSiteId);

		waterStatsticsUpdation.setLatLong(latLongImpl);
		populateData(dataLine, stateName, waterStatsticsUpdation);
		newDataToPopulate.put(mapKey, waterStatsticsUpdation);
	}

	private void populateData(String[] dataLine, String stateName, WaterStatsticsUpdationImpl existingWaterStats) {
		// System.out.println("Existing water stats found");
		String readingDate = dataLine[9];
		String readingValue = dataLine[10];

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
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				// System.out.println("Its a NE state, only March/Nov Reading applicable, this
				// month: " + month);
			}
		} else if (stateName.equalsIgnoreCase(State.ORRISA.getName())
				|| stateName.equalsIgnoreCase(State.WEST_BENGAL.getName())
				|| stateName.equalsIgnoreCase(State.KERALA.getName())) {
			// System.out.println("Its an april state, reading
			// April/Nov Reading");
			if (month == 4) {
				// Correct reading
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				// System.out.println("Its an april state, only april/Nov Reading applicable,
				// this month: " + month);
			}
		} else {
			// System.out.println("Its rest of India state, reading
			// May/Nov Reading");
			if (month == 5) {
				// Correct reading
				addPreReading(year, readingValue, existingWaterStats);
			} else if (month == 11) {
				// Correct reading
				addPostReading(year, readingValue, existingWaterStats);
			} else {
				// System.out.println("Its rest of India state, only May/Nov Reading applicable,
				// this month: " + month);
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
