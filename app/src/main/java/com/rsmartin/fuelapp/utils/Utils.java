package com.rsmartin.fuelapp.utils;

public class Utils {


    public static Double replaceComaToDot(String s) {
        String sPoint = s.replace(",", ".");
        return Double.valueOf(sPoint);
    }

    public static Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        int EARTH_RADIUS_M = 6371000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double dLat1 = Math.toRadians(lat1);
        double dLat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) *
                        Math.cos(dLat1) * Math.cos(dLat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return ((c * EARTH_RADIUS_M) / 1000);
    }

}
