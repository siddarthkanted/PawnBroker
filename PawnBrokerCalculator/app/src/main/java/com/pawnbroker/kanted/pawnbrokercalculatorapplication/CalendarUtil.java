package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by sikanted on 11/12/2015.
 */
public class CalendarUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.dateFormat);
    private static String Tag = CalendarUtil.class.getName();

    public static String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String convertCalendarToString(Calendar calendar){
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Calendar convertStringToCalendar(String dateInString){
        try {
            Calendar calendar  = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(dateInString));
            return calendar;
        } catch (ParseException e) {
            Log.e(Tag, "parse exception in " + dateInString);
            return null;
        }
    }

    public static int differenceToFromInMonths(Calendar toCalendar, Calendar fromCalendar){
        int year = fromCalendar.get(Calendar.YEAR) - toCalendar.get(Calendar.YEAR);
        int months = fromCalendar.get(Calendar.MONTH) - toCalendar.get(Calendar.MONTH);
        int days = fromCalendar.get(Calendar.DAY_OF_MONTH) - toCalendar.get(Calendar.DAY_OF_MONTH);

        months += year*12;
        if(days >= 0 && months > 0){
            months +=1;
        }

        return months;
    }


    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }


    public static long daysBetween(String toCalendar, String fromCalendar){
        return daysBetween(convertStringToCalendar(toCalendar), convertStringToCalendar(fromCalendar));
    }

    public static int differenceToFromInMonths(String toCalendar, String fromCalendar){
        return differenceToFromInMonths(convertStringToCalendar(toCalendar),convertStringToCalendar(fromCalendar));
    }
}
