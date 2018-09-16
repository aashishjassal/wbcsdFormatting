package com.wbcsd.fileGenerator.application.util;

/**
 * The Class BlockCategorizationUtils.
 * @author Aashish
 * @version $Id: $
 */
public class BlockCategorizationUtils {

    /** The Constant DOT. */
    private static final String DOT = ".";

    /** The Constant UNDERSCORE. */
    private static final String UNDERSCORE = "_";

    /**
     * Gets the primary key.
     * @param stateName the state name
     * @param districtName the district name
     * @param blockName the block name
     * @return the primary key
     */
    public static String getPrimaryKey(final String stateName, final String districtName, final String blockName) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(stateName.toLowerCase()).append(UNDERSCORE).append(districtName.toLowerCase()).append(UNDERSCORE)
                .append(blockName.toLowerCase()).append(UNDERSCORE);

        return buffer.toString();
    }
}
