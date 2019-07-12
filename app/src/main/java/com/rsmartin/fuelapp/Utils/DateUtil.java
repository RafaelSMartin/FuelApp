package com.rsmartin.fuelapp.Utils;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static boolean checkDatePattern(String date) {
        if (date != null) {
            String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}"; //Formato dd-mm-yyyy
            return date.matches(datePattern);
        } else {
            return false;
        }
    }

    public static String formatDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static String formatDateToStringBars(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    public static String formatDateToHourString(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(date);
    }

    public static Date dateFromStringHour(String hourString) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date mDate = null;
        try {
            mDate = df.parse(hourString);
        } catch (ParseException ex) {

        }
        return mDate;
    }

    public static String formatDateToStringWithHour(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm \n dd-MM-yyyy");
        return df.format(date);
    }

    public static Date dateFromStringWithHour(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm \n dd-MM-yyyy");
        Date mDate = null;
        try {
            mDate = df.parse(dateString);
        } catch (ParseException ex) {

        }
        return mDate;
    }

    public static Date dateFromString(String dateString) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date mDate = null;
        try {
            mDate = formatoDelTexto.parse(dateString);
        } catch (ParseException ex) {
        }
        return mDate;
    }

    public static Date getDateFromDatePicker(DatePicker datePicker) {
        if (datePicker != null) {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            return calendar.getTime();
        }
        return null;
    }
}