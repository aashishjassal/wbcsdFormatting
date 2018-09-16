package com.wbcsd.fileGenerator.api.impl;

import com.wbcsd.fileGenerator.api.LatLong;

/**
 * The Class LatLongImpl.
 * @author Aashish
 * @version $Id: $
 */
public class LatLongImpl implements LatLong {

    /** The latitude. */
    private String latitude;

    /** The longitude. */
    private String longitude;

    /**
     * {@inheritDoc}
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * {@inheritDoc}
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the latitude.
     * @param pLatitude the latitude to set
     */
    public void setLatitude(String pLatitude) {
        this.latitude = pLatitude;
    }

    /**
     * Sets the longitude.
     * @param pLongitude the longitude to set
     */
    public void setLongitude(String pLongitude) {
        this.longitude = pLongitude;
    }

}
