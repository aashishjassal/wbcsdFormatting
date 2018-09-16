package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;
import java.util.Map;

public interface IWaterStatsticsUpdation extends SiteInfo {

	public void setCountry(Country country);

	public Country getCountry();

	public Map<Integer, BigDecimal> getPreMonsoonReading();

	public Map<Integer, BigDecimal> getPostMonsoonReading();

	public void addPreMonsoonReading(Integer year, BigDecimal readBigDecimal);

	public void addPostMonsoonReading(Integer year, BigDecimal readBigDecimal);

}
