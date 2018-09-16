package com.wbcsd.fileGenerator.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The Interface WaterStats.
 * @author Aashish
 * @version $Id: $
 */
public interface WaterStats {

    /**
     * Gets the site id.
     * @return the site id
     */
    public String getSiteId();

    /**
     * Gets the first quarter reading.
     * @return the first quarter reading
     */
    public Map<Integer, List<BigDecimal>> getFirstQuarterReading();

    /**
     * Gets the second quarter reading.
     * @return the second quarter reading
     */
    public Map<Integer, List<BigDecimal>> getSecondQuarterReading();

    /**
     * Gets the third quarter reading.
     * @return the third quarter reading
     */
    public Map<Integer, List<BigDecimal>> getThirdQuarterReading();

    /**
     * Gets the fourth quarter reading.
     * @return the fourth quarter reading
     */
    public Map<Integer, List<BigDecimal>> getFourthQuarterReading();

    /**
     * Adds the first quarter reading.
     * @param readBigDecimal the read big decimal
     */
    public void addFirstQuarterReading(Integer year, BigDecimal readBigDecimal);

    /**
     * Adds the second quarter reading.
     * @param readBigDecimal the read big decimal
     */
    public void addSecondQuarterReading(Integer year, BigDecimal readBigDecimal);

    /**
     * Adds the third quarter reading.
     * @param readBigDecimal the read big decimal
     */
    public void addThirdQuarterReading(Integer year, BigDecimal readBigDecimal);

    /**
     * Adds the fourth quarter reading.
     * @param readBigDecimal the read big decimal
     */
    public void addFourthQuarterReading(Integer year, BigDecimal readBigDecimal);
}
