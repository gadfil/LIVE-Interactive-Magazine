package com.aod.clubapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gadfil on 03.01.2015.
 */
public class DateTimeUtils {
    private static final SimpleDateFormat OUT_DATE_FORMAT = new SimpleDateFormat("EEEE, dd MMMM  HH:mm");
    private static final SimpleDateFormat OUT_TIME_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");

    public static String dateToDay(Date date) {
        return OUT_DATE_FORMAT.format(date);
    }

    public static String dateToTime(Date date) {
        return OUT_TIME_FORMAT.format(date);
    }

    public static Date ISO8601toDate(String ISO8601) throws ParseException {
        return DATE_FORMAT.parse(new StringBuilder(ISO8601).delete(ISO8601.length() - 6, ISO8601.length()).toString());
    }

}
