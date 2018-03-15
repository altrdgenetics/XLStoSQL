/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xln;

import com.xln.model.DatabaseTableModel;
import com.xln.sql.SQLTable;
import com.xln.util.ExcelTools;
import com.xln.util.StringUtilities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author User
 */
public class Main {
    
    public static void main(String[] args) {
        ExcelImport();
    }
    
    
    public static void ExcelImport() {
        long lStartTime = System.currentTimeMillis();
        int totalRecordCount = 0;
        
        //Load XLS File to Array List
        System.out.println("Gathering XLS Records to Array List...");
        ArrayList SMDSDocXLS = ExcelTools.ExcelIteratorRead("XLStoImport.xlsx");

        //Get Total Records
        totalRecordCount = SMDSDocXLS.size();
        
        //Setup ArrayList To Database Model
        List<DatabaseTableModel> cleanedSMDSDocsList = new ArrayList<>();

        //Sanitize Array List for DB Import
        System.out.println("Sanitizing XLS Records...");
        for (Iterator iterator = SMDSDocXLS.iterator(); iterator.hasNext();) {
            List list = (List) iterator.next();
            if (list.size() == 6) { //needs to be same size as columns in row
                cleanedSMDSDocsList.add(ExcelTools.SanitizeArrayListExcel(list));
            }
        }

        //Batch Update DB Records
        System.out.println("Inserting XLS Records to Database...");
        SQLTable.batchUpdateDatabaseTable(cleanedSMDSDocsList);
        
        //Complete Task Timer
        long lEndTime = System.currentTimeMillis();
        
        String finishedText = "Finished Migrating Documents: "
                + totalRecordCount + " records in " 
                + StringUtilities.convertLongToTime(lEndTime - lStartTime);
        
        System.out.println(finishedText);
    }
    
}
