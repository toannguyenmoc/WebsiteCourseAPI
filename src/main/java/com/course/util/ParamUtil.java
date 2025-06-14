package com.course.util;

import jakarta.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParamUtil {

    public static String getString(HttpServletRequest req, String name, String defaultValue) {
        String value = req.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public static int getInt(HttpServletRequest req, String name, int defaultValue) {
        try {
            String value = req.getParameter(name);
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static double getDouble(HttpServletRequest req, String name, double defaultValue) {
        try {
            String value = req.getParameter(name);
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(HttpServletRequest req, String name, boolean defaultValue) {
        String value = req.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static Date getDate(HttpServletRequest req, String name, String pattern) {
        try {
            String value = req.getParameter(name);
            if (value != null && !value.isEmpty()) {
                return new SimpleDateFormat(pattern).parse(value);
            }
        } catch (Exception ignored) {}
        return null;
    }
}
