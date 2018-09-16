package com.wbcsd.fileGenerator.api;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class BlockCategarizationImpl.
 * @author Aashish
 * @version $Id: $
 */
public class BlockCategarizationImpl implements BlockCategarization {

    /** The state. */
    private State state;

    /** The district. */
    private String district;

    /** The block. */
    private String block;

    /** The map of status. */
    private final Map<Integer, ExploitationStatus> mapOfStatus = new HashMap<Integer, ExploitationStatus>();

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
    public String getBlock() {
        return block;
    }

    /**
     * {@inheritDoc}
     */
    public Map<Integer, ExploitationStatus> getYearBasedAverage() {
        return mapOfStatus;
    }

    /**
     * Adds the year based status.
     * @param year the year
     * @param status the status
     */
    public void addYearBasedStatus(Integer year, ExploitationStatus status) {
        mapOfStatus.put(year, status);
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

    /**
     * Sets the block.
     * @param block the block to set
     */
    public void setBlock(String block) {
        this.block = block;
    }

}
