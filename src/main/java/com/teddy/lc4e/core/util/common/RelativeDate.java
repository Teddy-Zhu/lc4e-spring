package com.teddy.lc4e.core.util.common;

import com.teddy.lc4e.core.util.l18n.ParserMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

/**
 * Created by teddy on 2015/6/26.
 */
@Service
public class RelativeDate {

    private static final Logger LOGGER = Logger.getLogger(RelativeDate.class);
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    @Autowired
    private ParserMessage msg;

    public String format(Date date, Locale locale, Date now) {
        long delta = now.getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            if (seconds < 30) {
                return msg.L18N("NOW", locale);
            } else {
                return (seconds <= 0 ? 1 : seconds) + " " + msg.L18N("ONE_SECOND_AGO", locale);
            }
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + " " + msg.L18N("ONE_MINUTE_AGO", locale);
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + " " + msg.L18N("ONE_HOUR_AGO", locale);
        }
        if (delta < 48L * ONE_HOUR) {
            return " " + msg.L18N("YESTERDAY", locale);
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + " " + msg.L18N("ONE_DAY_AGO", locale);
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + " " + msg.L18N("ONE_MONTH_AGO", locale);
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + " " + msg.L18N("ONE_YEAR_AGO", locale);
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
