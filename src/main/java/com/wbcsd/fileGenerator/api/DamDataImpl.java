package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;

/**
 * The Class BlockCategarizationImpl.
 * @author Aashish
 * @version $Id: $
 */
public class DamDataImpl {

    /** The state. */
    private State state;

    /** The district. */
    private String damName;

    /** The annual gw resource. */
    private LatLong latLong;

    /** The proj demand. */
    private BigDecimal resArea;

    /** The net g water available. */
    private BigDecimal effStorageCap;

    /**
     * Gets the state.
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state.
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets the dam name.
     * @return the damName
     */
    public String getDamName() {
        return damName;
    }

    /**
     * Sets the dam name.
     * @param damName the damName to set
     */
    public void setDamName(String damName) {
        this.damName = damName;
    }

    /**
     * Gets the lat long.
     * @return the latLong
     */
    public LatLong getLatLong() {
        return latLong;
    }

    /**
     * Sets the lat long.
     * @param latLong the latLong to set
     */
    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

    /**
     * Gets the res area.
     * @return the resArea
     */
    public BigDecimal getResArea() {
        return resArea;
    }

    /**
     * Sets the res area.
     * @param resArea the resArea to set
     */
    public void setResArea(BigDecimal resArea) {
        this.resArea = resArea;
    }

    /**
     * Gets the eff storage cap.
     * @return the effStorageCap
     */
    public BigDecimal getEffStorageCap() {
        return effStorageCap;
    }

    /**
     * Sets the eff storage cap.
     * @param effStorageCap the effStorageCap to set
     */
    public void setEffStorageCap(BigDecimal effStorageCap) {
        this.effStorageCap = effStorageCap;
    }

}
