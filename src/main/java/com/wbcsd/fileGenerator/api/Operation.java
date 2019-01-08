package com.wbcsd.fileGenerator.api;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Enum Operation.
 * 
 * @author Aashish
 * @version $Id: $
 */
public enum Operation {

	/** The site info generation. */
	SITE_INFO_GENERATION("GenerateSiteInfo"),

	/** The water stats generation. */
	WATER_STATS_GENERATION_2012_ONWARDS("GenerateWaterStats"),

	/** The WATE r_ stat s_ generatio n_2007_2011. */
	WATER_STATS_GENERATION_2007_ONWARDS("GenerateWaterStats2007"),

	/** The block categarization. */
	BLOCK_CATEGARIZATION("BlockCategarization"),

	/** The ground water other indicators. */
	GROUND_WATER_OTHER_INDICATORS("GWaterOtherIndicators"),

	/** The dam data. */
	DAM_DATA("DamData"),

	/** The rainfall data. */
	RAINFALL_DATA("RainfallData"),

	/** The surface water quality. */
	SURFACE_WATER_QUALITY("SurfaceWaterQuality"),

	/** The ground water quality. */
	GROUND_WATER_QUALITY("GroundWaterQuality"),

	/** The water stats updation 2014 onwards. */
	WATER_STATS_UPDATION_2014_ONWARDS("UpdateWaterStats2014"),
	WATER_STATS_UPDATION_2015_ONWARDS("UpdateWaterStats2015"),
	/** The ground water quality 2013. */
	GROUND_WATER_QUALITY_2013("GroundWaterQuality2013"),

	/** The ground water other indicators 2013. */
	GROUND_WATER_OTHER_INDICATORS_2013("GWaterOtherIndicators2013");
	/** The value. */
	private final String value;

	/**
	 * Instantiates a new site type.
	 * 
	 * @param pDescription the description
	 */
	private Operation(final String pDescription) {
		this.value = pDescription;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Value.
	 * 
	 * @param value the value
	 * @return the operation
	 */
	public static Operation value(String value) {
		for (Operation op : Operation.values()) {
			if (StringUtils.equalsIgnoreCase(value, op.getValue())) {
				return op;
			}
		}
		return null;
	}

}
