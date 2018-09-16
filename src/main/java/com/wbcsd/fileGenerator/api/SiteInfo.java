package com.wbcsd.fileGenerator.api;

/**
 * @author Aashish
 * @version $Id: $
 */
public interface SiteInfo {

    public String getId();

    public String getName();

    public LatLong getLatLong();

    public SiteType getType();

    public State getState();

    public String getDistrict();

    public String getBlock();

}
