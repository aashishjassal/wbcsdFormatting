package com.wbcsd.fileGenerator.api.impl;

import com.wbcsd.fileGenerator.api.LatLong;
import com.wbcsd.fileGenerator.api.SiteInfo;
import com.wbcsd.fileGenerator.api.SiteType;
import com.wbcsd.fileGenerator.api.State;

/**
 * The Class SiteInfoImpl.
 * @author Aashish
 * @version $Id: $
 */
public class SiteInfoImpl implements SiteInfo {

    /** The id. */
    private String id;

    /** The name. */
    private String name;

    /** The lat long. */
    private LatLong latLong;

    /** The site type. */
    private SiteType siteType;

    /** The state. */
    private State state;

    /** The district. */
    private String district;

    /** The block. */
    private String block;

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public LatLong getLatLong() {
        return latLong;
    }

    /**
     * {@inheritDoc}
     */
    public SiteType getType() {
        return siteType;
    }

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
     * Sets the id.
     * @param pId the id to set
     */
    public void setId(String pId) {
        this.id = pId;
    }

    /**
     * Sets the name.
     * @param pName the name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Sets the lat long.
     * @param pLatLong the latLong to set
     */
    public void setLatLong(LatLong pLatLong) {
        this.latLong = pLatLong;
    }

    /**
     * Sets the site type.
     * @param pSiteType the siteType to set
     */
    public void setSiteType(SiteType pSiteType) {
        this.siteType = pSiteType;
    }

    /**
     * Sets the state.
     * @param pState the state to set
     */
    public void setState(State pState) {
        this.state = pState;
    }

    /**
     * Sets the district.
     * @param pDistrict the district to set
     */
    public void setDistrict(String pDistrict) {
        this.district = pDistrict;
    }

    /**
     * Sets the block.
     * @param pBlock the block to set
     */
    public void setBlock(String pBlock) {
        this.block = pBlock;
    }

}
