/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xln.util;


import com.xln.model.DatabaseTableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class ExcelTools {
 
    /** Loops through the Cells
     * 
     * NOTE: MAKE SURE BLANK CELLS ARE 'NULL' else THE iterator will
     * skip to the next line
     * 
     * @param list
     * @return 
    */
    public static DatabaseTableModel SanitizeArrayListExcel(List list) {
        DatabaseTableModel item = new DatabaseTableModel();
        item.setColumnA(list.get(0).toString().trim().equals("NULL") ? null : list.get(0).toString().trim());
        item.setColumnB(list.get(1).toString().trim().equals("NULL") ? null : list.get(1).toString().trim());
        item.setColumnC(list.get(2).toString().trim().equals("NULL") ? null : list.get(2).toString().trim());
        item.setColumnD(list.get(3).toString().trim().equals("NULL") ? null : list.get(3).toString().trim());
        item.setColumnE(list.get(4).toString().trim().equals("NULL") ? null : list.get(4).toString().trim());
        item.setColumnF(list.get(5).toString().trim().equals("NULL") ? null : list.get(5).toString().trim());

        return item;
    }
    
    public static ArrayList ExcelIteratorRead(String fileName) {
        ArrayList cellVectorHolder = new ArrayList();
        try {
            FileInputStream myInput = new FileInputStream(fileName);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                List list = new ArrayList();
                while (cellIter.hasNext()) {
                    XSSFCell myCell = (XSSFCell) cellIter.next();
                    list.add(myCell);
                }
                cellVectorHolder.add(list);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return cellVectorHolder;
    }
    
}
