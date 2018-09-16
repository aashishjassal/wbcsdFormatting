package com.wbcsd.fileGenerator.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wbcsd.fileGenerator.api.WaterStats;

/**
 * The Class WaterStatsImpl.
 * @author Aashish
 * @version $Id: $
 */
public class WaterStatsImpl implements WaterStats {

    /** The site id. */
    private String siteId;

    /** The first quat reading. */
    private final ConcurrentHashMap<Integer, List<BigDecimal>> firstQuatReading = new ConcurrentHashMap<Integer, List<BigDecimal>>();

    /** The second quat reading. */
    private final ConcurrentHashMap<Integer, List<BigDecimal>> secondQuatReading = new ConcurrentHashMap<Integer, List<BigDecimal>>();

    /** The third quat reading. */
    private final ConcurrentHashMap<Integer, List<BigDecimal>> thirdQuatReading = new ConcurrentHashMap<Integer, List<BigDecimal>>();

    /** The fourth quat reading. */
    private final ConcurrentHashMap<Integer, List<BigDecimal>> fourthQuatReading = new ConcurrentHashMap<Integer, List<BigDecimal>>();

    /**
     * {@inheritDoc}
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the site id.
     * @param pSiteId the siteId to set
     */
    public void setSiteId(String pSiteId) {
        this.siteId = pSiteId;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, List<BigDecimal>> getFirstQuarterReading() {
        return firstQuatReading;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, List<BigDecimal>> getSecondQuarterReading() {
        return secondQuatReading;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, List<BigDecimal>> getThirdQuarterReading() {
        return thirdQuatReading;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, List<BigDecimal>> getFourthQuarterReading() {
        return fourthQuatReading;
    }

    /**
     * {@inheritDoc}
     */
    public void addFirstQuarterReading(Integer year, BigDecimal readBigDecimal) {
        firstQuatReading.putIfAbsent(year, new ArrayList<BigDecimal>());
        firstQuatReading.get(year).add(readBigDecimal);
    }

    /**
     * {@inheritDoc}
     */
    public void addSecondQuarterReading(Integer year, BigDecimal readBigDecimal) {
        secondQuatReading.putIfAbsent(year, new ArrayList<BigDecimal>());
        secondQuatReading.get(year).add(readBigDecimal);
    }

    /**
     * {@inheritDoc}
     */
    public void addThirdQuarterReading(Integer year, BigDecimal readBigDecimal) {
        thirdQuatReading.putIfAbsent(year, new ArrayList<BigDecimal>());
        thirdQuatReading.get(year).add(readBigDecimal);
    }

    /**
     * {@inheritDoc}
     */
    public void addFourthQuarterReading(Integer year, BigDecimal readBigDecimal) {
        fourthQuatReading.putIfAbsent(year, new ArrayList<BigDecimal>());
        fourthQuatReading.get(year).add(readBigDecimal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuffer builder = new StringBuffer();
        builder.append("Site Id: ").append(siteId).append(" firstQuatReading: ").append(firstQuatReading)
                .append(" secondQuatReading: ").append(secondQuatReading).append(" thirdQuatReading: ")
                .append(thirdQuatReading).append(" fourthQuatReading: ").append(fourthQuatReading);
        return builder.toString();
    }

}
