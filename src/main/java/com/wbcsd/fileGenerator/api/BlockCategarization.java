package com.wbcsd.fileGenerator.api;

import java.util.Map;

/**
 * The Interface BlockCategarization.
 * @author Aashish
 * @version $Id: $
 */
public interface BlockCategarization {

    /**
     * Gets the state.
     * @return the state
     */
    public State getState();

    /**
     * Gets the district.
     * @return the district
     */
    public String getDistrict();

    /**
     * Gets the block.
     * @return the block
     */
    public String getBlock();

    /**
     * Gets the year based status.
     * @return the year based status
     */
    public Map<Integer, ExploitationStatus> getYearBasedAverage();
}
