package com.zhouhp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuji on 15-12-16.
 */
public class Converter {

    public static Date convertTime(String timeString) throws ParseException {
        Date time;
        time = dateFormat.parse(timeString);
        return time;
    }

    public static String convertId(String idString){
        return idString;
    }

    public static double convertCoordinate(String coordinateString) throws NumberFormatException{
        double coordinate;
        coordinate=Double.parseDouble(coordinateString);

        return coordinate;
    }

    private static DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

}
