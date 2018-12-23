package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCategarizationImpl.
 * 
 * @author Aashish
 * @version $Id: $
 */
public class GroundWaterOtherIndicatorsImpl2013Data {

	/** The state. */
	private State state;

	/** The district. */
	private String district;

	/** The proj demand. */
	private BigDecimal projDemand;

	/** The net g water available. */
	private BigDecimal netGWaterAvailable;

	/** The net current g water available. */
	private BigDecimal netGWaterAvailableForFutureIrrigation;

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets the district.
	 *
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * Sets the district.
	 *
	 * @param district the new district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * Gets the proj demand.
	 *
	 * @return the proj demand
	 */
	public BigDecimal getProjDemand() {
		return projDemand;
	}

	/**
	 * Sets the proj demand.
	 *
	 * @param projDemand the new proj demand
	 */
	public void setProjDemand(BigDecimal projDemand) {
		this.projDemand = projDemand;
	}

	/**
	 * Gets the net G water available.
	 *
	 * @return the net G water available
	 */
	public BigDecimal getNetGWaterAvailable() {
		return netGWaterAvailable;
	}

	/**
	 * Sets the net G water available.
	 *
	 * @param netGWaterAvailable the new net G water available
	 */
	public void setNetGWaterAvailable(BigDecimal netGWaterAvailable) {
		this.netGWaterAvailable = netGWaterAvailable;
	}

	/**
	 * Gets the net G water available for future irrigation.
	 *
	 * @return the net G water available for future irrigation
	 */
	public BigDecimal getNetGWaterAvailableForFutureIrrigation() {
		return netGWaterAvailableForFutureIrrigation;
	}

	/**
	 * Sets the net G water available for future irrigation.
	 *
	 * @param netGWaterAvailableForFutureIrrigation the new net G water available
	 *                                              for future irrigation
	 */
	public void setNetGWaterAvailableForFutureIrrigation(BigDecimal netGWaterAvailableForFutureIrrigation) {
		this.netGWaterAvailableForFutureIrrigation = netGWaterAvailableForFutureIrrigation;
	}

}
