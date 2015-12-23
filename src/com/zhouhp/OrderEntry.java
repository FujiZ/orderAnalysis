package com.zhouhp;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by fuji on 15-12-16.
 */
public class OrderEntry {

    private OrderEntry(String record) throws ParseException {
        String[] attri=record.split("\\|");
        if(attri[ORDER_NO_INDEX].isEmpty())
            order_no=null;
        else
            order_no=Converter.convertId(attri[ORDER_NO_INDEX]);
        if(attri[MEMBER_ID_INDEX].isEmpty())
            member_id=null;
        else
            member_id=Converter.convertId(attri[MEMBER_ID_INDEX]);
        if(attri[STATUS_INDEX].isEmpty())
            status=null;
        else
            status= Integer.parseInt(attri[STATUS_INDEX]);

        dispatch_status=(attri[DISPATCH_STATUS_INDEX].isEmpty())?null:Integer.parseInt(attri[DISPATCH_STATUS_INDEX]);
        actual_board_time=(attri[ACTUAL_BOARD_TIME_INDEX].isEmpty())?null:Converter.convertTime(attri[ACTUAL_BOARD_TIME_INDEX]);
        actual_off_time=(attri[ACTUAL_OFF_TIME_INDEX].isEmpty())?null:Converter.convertTime(attri[ACTUAL_OFF_TIME_INDEX]);
        actual_board_lon=(attri[ACTUAL_BOARD_LON_INDEX].isEmpty())?null:Converter.convertCoordinate(attri[ACTUAL_BOARD_LON_INDEX]);
        actual_board_lat=(attri[ACTUAL_BOARD_LAT_INDEX].isEmpty())?null:Converter.convertCoordinate(attri[ACTUAL_BOARD_LAT_INDEX]);
        actual_off_lon=(attri[ACTUAL_OFF_LON_INDEX].isEmpty())?null:Converter.convertCoordinate(attri[ACTUAL_OFF_LON_INDEX]);
        actual_off_lat=(attri[ACTUAL_OFF_LAT_INDEX].isEmpty())?null:Converter.convertCoordinate(attri[ACTUAL_OFF_LAT_INDEX]);

    }

    public static OrderEntry parseEntry(String record) throws InvalidEntryException{
        OrderEntry newEntry;
        try {
            newEntry=new OrderEntry(record);
        } catch (ParseException|NumberFormatException e) {
            throw new InvalidEntryException();
        }
        return newEntry;
    }

    public static void setDataFormat(String formatstring){
        String[] formats=formatstring.split("\\|");
        for(int i=0;i<formats.length;++i){
            switch (formats[i]){
                case "order_no":
                    ORDER_NO_INDEX=i;break;
                case "member_id":
                    MEMBER_ID_INDEX=i;break;
                case "actual_board_time":
                    ACTUAL_BOARD_TIME_INDEX=i;break;
                case "actual_off_time":
                    ACTUAL_OFF_TIME_INDEX=i;break;
                case "actual_board_lon":
                    ACTUAL_BOARD_LON_INDEX=i;break;
                case "actual_board_lat":
                    ACTUAL_BOARD_LAT_INDEX=i;break;
                case "actual_off_lon":
                    ACTUAL_OFF_LON_INDEX=i;break;
                case "actual_off_lat":
                    ACTUAL_OFF_LAT_INDEX=i;break;
                case "dispatch_status":
                    DISPATCH_STATUS_INDEX=i;break;
                case "status":
                    STATUS_INDEX=i;break;
                default:
                    break;
            }
        }

    }

    double getActualDistance(){
        return Calculator.calDistance(actual_board_lon,actual_board_lat,actual_off_lon,actual_off_lat);
    }

    long getActualTime(){
        return Calculator.calTime(actual_board_time,actual_off_time);
    }

    int getStatus(){
        return status;
    }

    boolean hasTime(){
        return actual_board_time!=null&&actual_off_time!=null;
    }

    boolean hasCoordinate(){
        return actual_board_lon!=null&&actual_off_lon!=null;
    }

    private String order_no;
    private String member_id;

    private Integer status;
    private Integer dispatch_status;

    private Date actual_board_time;
    private Date actual_off_time;


    private Double actual_board_lon;
    private Double actual_board_lat;

    private Double actual_off_lon;
    private Double actual_off_lat;


    private static int ORDER_NO_INDEX;
    private static int MEMBER_ID_INDEX;

    private static int ACTUAL_BOARD_TIME_INDEX;
    private static int ACTUAL_OFF_TIME_INDEX;

    private static int ACTUAL_BOARD_LON_INDEX;
    private static int ACTUAL_BOARD_LAT_INDEX;

    private static int ACTUAL_OFF_LON_INDEX;
    private static int ACTUAL_OFF_LAT_INDEX;

    private static int STATUS_INDEX;
    private static int DISPATCH_STATUS_INDEX;

}
