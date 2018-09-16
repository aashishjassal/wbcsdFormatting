package com.wbcsd.fileGenerator.application.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.wbcsd.fileGenerator.api.WaterStatsticsUpdationImpl;

/**
 * The Class GenerateAccountCsvFile.
 * 
 * @author A.Jassal
 * @version $Id: $
 */
public class GenerateWaterStatusUpdationCsvFile {

	/** CSV Writer Factory. */
	private final CSVWriterFactory csvWriterFactory;

	private final List<Integer> yearList;

	/**
	 * Instantiates a new generate Account csv file.
	 */
	public GenerateWaterStatusUpdationCsvFile() {
		this(new CSVWriterFactory());
	}

	/**
	 * Instantiates a new generate Account csv file. To be used in Unit-Test.
	 * 
	 * @param pCsvWriterFactory
	 *            the the CSV Writer Factory
	 */
	public GenerateWaterStatusUpdationCsvFile(final CSVWriterFactory pCsvWriterFactory) {
		this.csvWriterFactory = pCsvWriterFactory;
		yearList = Lists.newArrayList(2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015);
	}

	/**
	 * Generate.
	 * 
	 * @param siteDataMap
	 *            the site data map
	 * @param waterStatus
	 *            the water status
	 * @param fileLocation
	 *            the file location
	 * @param fileName
	 *            the file name
	 */
	public void generate(final Collection<WaterStatsticsUpdationImpl> waterStatus, final String fileLocation,
			final String fileName) {
		try {
			File file = new File(fileLocation, fileName);
			if (!file.exists()) {
				System.out.println("Already does not exist, generate header");
				CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
				String[] entries = new String[] { "Country Code", "Country", "State Code", "State", "District", "Block",
						"Site Id", "SiteName", "Latitude", "Longitude" };
				for (Integer year : yearList) {
					entries = ArrayUtils.addAll(entries, year + " Pre", year + " Post");
				}

				writer.writeNext(entries);
				writer.close();

			}
			System.out.println("All data count: " + waterStatus.size());
			int i = 0;

			CSVWriterWrapper writer = csvWriterFactory.create(fileLocation, fileName);
			for (final WaterStatsticsUpdationImpl stats : waterStatus) {
				try {
					String[] entries = new String[] { stats.getCountry().getCode(), stats.getCountry().getName(),
							stats.getState().getCode(), stats.getState().getName(), stats.getDistrict(),
							stats.getBlock(), stats.getId(), stats.getName(), stats.getLatLong().getLatitude(),
							stats.getLatLong().getLongitude() };

					for (Integer year : yearList) {
						entries = putQuarterValues(stats, entries, year);
					}
					i += 1;
					System.out.println("Count: " + i);
					writer.writeNext(entries);
				} catch (RuntimeException exception) {
					exception.printStackTrace();
					System.out.println("Data exception: " + stats);
				}
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Put quarter values.
	 * 
	 * @param stats
	 *            the stats
	 * @param entries
	 *            the entries
	 * @param year
	 *            the year
	 * @return String[]
	 */
	private String[] putQuarterValues(final WaterStatsticsUpdationImpl stats, String[] entries, Integer year) {
		BigDecimal pre = stats.getPreMonsoonReading().get(year);
		BigDecimal post = stats.getPostMonsoonReading().get(year);
		if (pre != null) {
			entries = ArrayUtils.addAll(entries, pre.toString());
		} else {
			entries = ArrayUtils.addAll(entries, "");
		}

		if (post != null) {
			entries = ArrayUtils.addAll(entries, post.toString());
		} else {
			entries = ArrayUtils.addAll(entries, "");
		}
		return entries;
	}

}
