package com.rsmartin.fuelapp.utils;

public class Utils {


    public static Double replaceComaToDot(String s) {
        String sPoint = s.replace(",", ".");
        return Double.valueOf(sPoint);
    }

}
