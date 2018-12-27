/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.el.whippi.components.input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author david
 */
public class DateFieldUtil {

    // "yyyy-MM-dd'T'HH:mm"
    public static long toTimeStamp(String dateStr, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);
        try {
            return sdf.parse(dateStr).getTime();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toTimeStr(long timeStamp, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(timeZone);

        return sdf.format(new Date(timeStamp));
    }

    private DateFieldUtil() {
    }

}
