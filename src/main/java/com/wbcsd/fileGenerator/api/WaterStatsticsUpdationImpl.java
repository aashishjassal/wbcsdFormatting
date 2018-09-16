package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wbcsd.fileGenerator.api.impl.SiteInfoImpl;

public class WaterStatsticsUpdationImpl extends SiteInfoImpl implements IWaterStatsticsUpdation {

	private Country country;

	private final ConcurrentHashMap<Integer, BigDecimal> preMonsoonReading = new ConcurrentHashMap<Integer, BigDecimal>();
	private final ConcurrentHashMap<Integer, BigDecimal> postMonsoonReading = new ConcurrentHashMap<Integer, BigDecimal>();

	public Map<Integer, BigDecimal> getPreMonsoonReading() {
		return preMonsoonReading;
	}

	public Map<Integer, BigDecimal> getPostMonsoonReading() {
		return postMonsoonReading;
	}

	public void addPreMonsoonReading(Integer year, BigDecimal readBigDecimal) {
		preMonsoonReading.put(year, readBigDecimal);
	}

	public void addPostMonsoonReading(Integer year, BigDecimal readBigDecimal) {
		postMonsoonReading.put(year, readBigDecimal);
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}
}
