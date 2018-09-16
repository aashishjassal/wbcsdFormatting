package com.wbcsd.fileGenerator.api.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.wbcsd.fileGenerator.api.LatLong;

/**
 * The Class LatLongImpl.
 * 
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
	 * 
	 * @param pLatitude
	 *            the latitude to set
	 */
	public void setLatitude(String pLatitude) {
		this.latitude = pLatitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param pLongitude
	 *            the longitude to set
	 */
	public void setLongitude(String pLongitude) {
		this.longitude = pLongitude;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) {
			return false;
		}
		if (!(arg0 instanceof LatLongImpl)) {
			return false;
		}
		LatLongImpl t = (LatLongImpl) arg0;
		EqualsBuilder b = new EqualsBuilder().append(getLatitude(), t.getLatitude()).append(getLongitude(),
				t.getLongitude());
		return b.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder b = new HashCodeBuilder();
		b.append(getLatitude()).append(getLongitude());
		return b.toHashCode();
	}

}
