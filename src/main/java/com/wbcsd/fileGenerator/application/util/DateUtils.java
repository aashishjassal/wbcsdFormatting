package com.wbcsd.fileGenerator.application.util;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.api.Month;

/**
 * @author Aashish
 * @version $Id: $
 */
public class DateUtils {

    public static Integer getYear(String date) {
        String[] datSplit = StringUtils.split(date, "-");
        if (datSplit[2].length() == 4) {
            return Integer.parseInt(datSplit[2]);
        }
        return Integer.parseInt("20" + datSplit[2]);
    }

    public static Integer getMonth(String date) {
        String[] datSplit = StringUtils.split(date, "-");
        if (StringUtils.isNumeric(datSplit[1])) {
            return Integer.parseInt(datSplit[1]);
        }
        return Month.value(datSplit[1]).getCode();
    }
}
