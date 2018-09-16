package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;

/**
 * The Class BlockCategarizationImpl.
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterOtherIndicatorsImpl {

    /** The state. */
    private State state;

    /** The district. */
    private String district;

    /** The annual gw resource. */
    private BigDecimal annualGWResource;

    /** The curr domestic demand. */
    private BigDecimal currDomesticDemand;

    /** The proj demand. */
    private BigDecimal projDemand;

    /** The net g water available. */
    private BigDecimal netGWaterAvailable;

    /** The net current g water available. */
    private BigDecimal netCurrentGWaterAvailable;

    /** The stage g water dev. */
    private BigDecimal stageGWaterDev;

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
     * Gets the district.
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district.
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets the annual gw resource.
     * @return the annualGWResource
     */
    public BigDecimal getAnnualGWResource() {
        return annualGWResource;
    }

    /**
     * Sets the annual gw resource.
     * @param annualGWResource the annualGWResource to set
     */
    public void setAnnualGWResource(BigDecimal annualGWResource) {
        this.annualGWResource = annualGWResource;
    }

    /**
     * Gets the proj demand.
     * @return the projDemand
     */
    public BigDecimal getProjDemand() {
        return projDemand;
    }

    /**
     * Sets the proj demand.
     * @param projDemand the projDemand to set
     */
    public void setProjDemand(BigDecimal projDemand) {
        this.projDemand = projDemand;
    }

    /**
     * Gets the net g water available.
     * @return the netGWaterAvailable
     */
    public BigDecimal getNetGWaterAvailable() {
        return netGWaterAvailable;
    }

    /**
     * Sets the net g water available.
     * @param netGWaterAvailable the netGWaterAvailable to set
     */
    public void setNetGWaterAvailable(BigDecimal netGWaterAvailable) {
        this.netGWaterAvailable = netGWaterAvailable;
    }

    /**
     * Gets the stage g water dev.
     * @return the stageGWaterDev
     */
    public BigDecimal getStageGWaterDev() {
        return stageGWaterDev;
    }

    /**
     * Sets the stage g water dev.
     * @param stageGWaterDev the stageGWaterDev to set
     */
    public void setStageGWaterDev(BigDecimal stageGWaterDev) {
        this.stageGWaterDev = stageGWaterDev;
    }

    /**
     * Gets the curr domestic demand.
     * @return the currDomesticDemand
     */
    public BigDecimal getCurrDomesticDemand() {
        return currDomesticDemand;
    }

    /**
     * Sets the curr domestic demand.
     * @param currDomesticDemand the currDomesticDemand to set
     */
    public void setCurrDomesticDemand(BigDecimal currDomesticDemand) {
        this.currDomesticDemand = currDomesticDemand;
    }

    /**
     * Gets the net current g water available.
     * @return the netCurrentGWaterAvailable
     */
    public BigDecimal getNetCurrentGWaterAvailable() {
        return netCurrentGWaterAvailable;
    }

    /**
     * Sets the net current g water available.
     * @param netCurrentGWaterAvailable the netCurrentGWaterAvailable to set
     */
    public void setNetCurrentGWaterAvailable(BigDecimal netCurrentGWaterAvailable) {
        this.netCurrentGWaterAvailable = netCurrentGWaterAvailable;
    }

}
