package com.wbcsd.fileGenerator.application.util;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.wbcsd.fileGenerator.excel.TextDataReader;

/**
 * @author Aashish
 * @version $Id: $
 */
public class BlockExplicitMapIdentifier {

    public static void main(String[] args) {
        TextDataReader dataReader = new TextDataReader();

        Set<String> dataSet = new TreeSet<String>();

        List<String> masterData = dataReader.readFileData("E:\\WaterBlock.txt");
        for (int i = 0; i < masterData.size(); i++) {
            String row = masterData.get(i);
            if (StringUtils.containsIgnoreCase(row, "not found")) {
                String blockInfo = StringUtils.splitByWholeSeparator(row, "not found")[0];
                blockInfo = StringUtils.remove(blockInfo, "In");
                blockInfo = StringUtils.remove(blockInfo, "block");
                String[] token = StringUtils.split(blockInfo, ":");
                dataSet.add("explicitConversions.put(\"" + token[0].trim() + "_" + token[1].trim() + "\", \"\");");
            }
        }

        for (String d : dataSet) {
            System.out.println(d);
        }
    }

}
