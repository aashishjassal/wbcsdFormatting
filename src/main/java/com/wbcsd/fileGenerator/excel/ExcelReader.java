package com.wbcsd.fileGenerator.excel;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Aashish
 * @version $Id: $
 */
public class ExcelReader {

    public void read(final String path) {

        try {
            Workbook workbook = Workbook.getWorkbook(new File(path));
            Sheet sheet = workbook.getSheet(0);
            Cell cell1 = sheet.getCell(0, 2);
            System.out.println(cell1.getContents());
            Cell cell2 = sheet.getCell(3, 4);
            System.out.println(cell2.getContents());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
