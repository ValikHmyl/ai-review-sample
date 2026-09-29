package com.example.taskmanager.util;

public class Utils {

    public static String trim(String s, int n) {
        if (s.length() > n) {
            return s.substring(0, n) + "...";
        }
        return s;
    }

    public static boolean check(String s) {
        return s != null && s.length() > 3 && s.length() < 500;
    }
}
