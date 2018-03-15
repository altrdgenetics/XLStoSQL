/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xln.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Andrew
 */
public class StringUtilities {

    /**
     * Gets the current time in "MM/dd/yyyy HH:mm:ss a" Format
     *
     * @return String
     */
    public static String currentTime(){
        return Global.getMmddyyyyhhmmssa().format(new Date());
    }

    /**
     * converts millis to [__hr __min __sec] format
     *
     * @param millis long
     * @return String of duration
     */
    public static String convertLongToTime(long millis) {
        String duration = String.format("%02dhr %02dmin %02dsec",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        if (TimeUnit.MILLISECONDS.toHours(millis) == 0) {
            String[] split = duration.split("hr");
            duration = split[1].trim();
        }
        return duration.trim();
    }

}
