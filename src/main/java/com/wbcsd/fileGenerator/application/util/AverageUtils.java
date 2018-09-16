package com.wbcsd.fileGenerator.application.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Aashish
 * @version $Id: $
 */
public class AverageUtils {

    public static BigDecimal getAverage(List<BigDecimal> data) {
        BigDecimal average = BigDecimal.ZERO;
        for (BigDecimal b : data) {
            average = average.add(b);
        }
        try {
            return average.divide(BigDecimal.valueOf(data.size()), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return average;
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("17.8");
        BigDecimal b = new BigDecimal("3");
        System.out.println(a.divide(b, 5));
    }
}
