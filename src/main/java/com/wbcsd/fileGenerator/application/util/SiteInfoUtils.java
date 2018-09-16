package com.wbcsd.fileGenerator.application.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Aashish
 * @version $Id: $
 */
public class SiteInfoUtils {

	private static final Map<String, String> explicitConversions = new HashMap<String, String>();

	static {
		explicitConversions.put("Gandhinagr", "Gandhinagar");
		explicitConversions.put("Sarkarsammakulam", "Sarkar samakulam");

	}

	/**
	 * 
	 */
	private static final String DOT = ".";

	private static final String UNDERSCORE = "_";

	public static String getPrimaryKey(final String siteName, final String stateName, final String districtName,
			final String blockName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(siteName.toLowerCase()).append(UNDERSCORE).append(stateName.toLowerCase()).append(UNDERSCORE)
				.append(districtName.toLowerCase()).append(UNDERSCORE).append(blockName.toLowerCase())
				.append(UNDERSCORE);

		return buffer.toString();
	}

	public static String getStateDistrictBlockKey(final String stateName, final String districtName,
			final String blockName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase()).append(UNDERSCORE).append(districtName.toLowerCase()).append(UNDERSCORE)
				.append(blockName.toLowerCase());

		return buffer.toString();
	}

	public static String getStateDistrictKey(final String stateName, final String districtName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase()).append(UNDERSCORE).append(districtName.toLowerCase());

		return buffer.toString();
	}

	public static String getStateKey(final String stateName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(stateName.toLowerCase());

		return buffer.toString();
	}

	public static String getBestMatchBlockName(final String stateName, final String districtName,
			final String blockName, Set<String> allKeys) {
		for (final String k : allKeys) {
			String[] tokens = StringUtils.split(k, UNDERSCORE);
			String mapKeyState = tokens[1].trim();
			String mapKeyDistrict = tokens[2].trim();
			String mapKeyBlock = tokens[3].trim();

			if (StringUtils.equalsIgnoreCase(mapKeyState, stateName)) {
				if (StringUtils.equalsIgnoreCase(mapKeyDistrict, districtName)) {
					if (StringUtils.equalsIgnoreCase(mapKeyBlock, blockName)) {
						// Its best case scenario
						return blockName;
					} else if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Sarkarsammakulam in block file and Sarkar
						// Sammakulam in siteInfo file, return
						// site info name
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "North")) {
						// Cases like, POLLACHI(N) in block file and POLLACHI
						// NORTH in siteInfo file, return site info
						// name.

						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "North").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_N"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(N)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "South")) {
						// Cases like, POLLACHI(S) in block file and POLLACHI
						// SOUTH in siteInfo file, return site info
						// name.
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "South").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_S"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(S)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "East")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "East").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_E"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(E)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.endsWithIgnoreCase(mapKeyBlock, "West")) {
						String tempSiteInfoName = StringUtils.removeEndIgnoreCase(mapKeyBlock, "West").trim();
						if (StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "_W"), tempSiteInfoName)
								|| StringUtils.equalsIgnoreCase(StringUtils.remove(blockName, "(W)"), tempSiteInfoName))
							return StringUtils.capitalize(mapKeyBlock);
					}

					else if (StringUtils.containsIgnoreCase(StringUtils.remove(blockName, " "),
							StringUtils.remove(mapKeyBlock, " "))) {
						// Cases like, Boath(NC) in block file and Boath in
						// siteInfo file, return site info name.
						return StringUtils.capitalize(mapKeyBlock);
					} else if (StringUtils.containsIgnoreCase(blockName, DOT)) {
						// Cases like, B.K. Samudram in block file and
						// BUKKARAYASAMUDRAM in siteInfo file.
						String endOfBlockName = StringUtils.substringAfterLast(blockName, DOT);
						if (StringUtils.containsIgnoreCase(StringUtils.remove(mapKeyBlock, " "),
								StringUtils.remove(endOfBlockName, " "))) {
							return StringUtils.capitalize(mapKeyBlock);
						}
					}
				}
			}
		}
		// Return name with no space in between and converted
		String name = StringUtils.remove(blockName, " ");
		if (StringUtils.endsWith(name, "_N")) {
			return StringUtils.removeEnd(name, "_N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "-N")) {
			return StringUtils.removeEnd(name, "-N").trim() + "(N)";
		} else if (StringUtils.endsWith(name, "_S")) {
			return StringUtils.removeEnd(name, "_S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "-S")) {
			return StringUtils.removeEnd(name, "-S").trim() + "(S)";
		} else if (StringUtils.endsWith(name, "_E")) {
			return StringUtils.removeEnd(name, "_E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "-E")) {
			return StringUtils.removeEnd(name, "-E").trim() + "(E)";
		} else if (StringUtils.endsWith(name, "_W")) {
			return StringUtils.removeEnd(name, "_W").trim() + "(W)";
		} else if (StringUtils.endsWith(name, "-W")) {
			return StringUtils.removeEnd(name, "-W").trim() + "(W)";
		}
		return getOverriddenNameIfApplicable(name);
	}

	private static String getOverriddenNameIfApplicable(String name) {
		if (explicitConversions.containsKey(name)) {
			return explicitConversions.get(name);
		}
		return name;

	}

}
