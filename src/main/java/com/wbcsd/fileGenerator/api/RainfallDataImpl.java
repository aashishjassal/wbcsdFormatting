package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class BlockCategarizationImpl.
 * @author Aashish
 * @version $Id: $
 */
public class RainfallDataImpl {

    /** The state. */
    private State state;

    /** The district. */
    private String district;

    /** The map of status. */
    private final Map<Integer, BigDecimal> mapOfAverage = new HashMap<Integer, BigDecimal>();

    /**
     * {@inheritDoc}
     */
    public State getState() {
        return state;
    }

    /**
     * {@inheritDoc}
     */
    public String getDistrict() {
        return district;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, BigDecimal> getYearBasedAverage() {
        return mapOfAverage;
    }

    /**
     * Adds the year based status.
     * @param year the year
     * @param status the status
     */
    public void addYearBasedAverage(Integer year, BigDecimal average) {
        mapOfAverage.put(year, average);
    }

    /**
     * Sets the state.
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Sets the district.
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

}
