package com.bentech.android.appcommons.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daniel on 9/2/2015.
 */
public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static String formatToSmartDateTime(String date) {
        if (isToday(date)) {
            return formatToTime(date);
        } else if (isThisYear(date)) {
            return new SimpleDateFormat("MMM d").format(new Date(Long.parseLong(date)));
        }

        return formatToDate(date);
    }

    private static boolean isThisYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));

        Calendar nowCalendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR) == nowCalendar.get(Calendar.YEAR);
    }

    private static boolean isToday(String dateMilliseconds) {
        return android.text.format.DateUtils.isToday(Long.parseLong(dateMilliseconds));
    }

    public static String formatToDateTime(String dateMilliseconds) {
        return DateFormat.getDateTimeInstance().format(new Date(Long.valueOf(dateMilliseconds)));
    }

    public static String formatToDate(String dateMilliseconds) {
        return DateFormat.getDateInstance().format(new Date(Long.valueOf(dateMilliseconds)));
    }

    public static String formatToTime(String dateMilliseconds) {
        return DateFormat.getTimeInstance().format(new Date(Long.valueOf(dateMilliseconds)));
    }

    @SuppressLint("SimpleDateFormat")
    public static DateFormat newXMLGregorianCalendaDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMonthString(String date) {
        return new SimpleDateFormat("MMM").format(new Date(Long.parseLong(date)));
    }

    public static String getMonthYearString(String date) {
        return new SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(new Date(Long.parseLong(date)));
    }

    public static String getLastNMonths(int monthSeed) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthSeed * -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return String.valueOf(calendar.getTime().getTime());
    }

    public static boolean isDateGreaterThanToday(long selectedDate) {
        return TimeUnit.DAYS.convert(moveDateToBeginningOfDay(new Date(selectedDate)).getTime() - moveDateToBeginningOfDay(new Date()).getTime(), TimeUnit.MILLISECONDS) >= 1;
    }

    public static boolean isDateGreaterThanOrEqualToday(long selectedDate) {
        return TimeUnit.DAYS.convert(moveDateToBeginningOfDay(new Date(selectedDate)).getTime() - moveDateToBeginningOfDay(new Date()).getTime(), TimeUnit.MILLISECONDS) >= 0;
    }
    public static Date moveDateToBeginningOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    public static String getLastNMonths(int monthSeed, long startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(startDate);
        calendar.add(Calendar.MONTH, monthSeed * -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return String.valueOf(calendar.getTime().getTime());
    }

    //http://stackoverflow.com/a/16559066
    public static int getMonthsBetweenDates(Long startDate, Long endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date(startDate));

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date(endDate));

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
    }
}
