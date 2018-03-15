/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xln.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
public class Global {
    
    //Global Variables
    private static final int BATCH_SIZE = 1000;
    
    //Date Time Formatters
    private static final SimpleDateFormat mmddyyyyhhmmssa = new SimpleDateFormat("EEE, MM/dd/yyyy hh:mm:ss a");
    
    //Getters
    public static int getBATCH_SIZE() {
        return BATCH_SIZE;
    }

    public static SimpleDateFormat getMmddyyyyhhmmssa() {
        return mmddyyyyhhmmssa;
    }
    
}
