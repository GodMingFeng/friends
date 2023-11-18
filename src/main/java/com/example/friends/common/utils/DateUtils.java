package com.example.friends.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 *
 */
public class DateUtils {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String DATE_WITHOUT_SEPARATOR_PATTERN = "yyyyMMdd";

    private static final Integer DEFAULT_VALUE = 0;

    /**
     * 获取当前时间
     */
    public static Date now() {
        return localDateTime2Date(LocalDateTime.now());
    }

    /**
     * 获取1970-01-01 00:00:00的日期
     */
    public static Date ofDefault() {
        return of(1970, 1, 1);
    }


    /**
     * 根据年月日 时分秒 构建日期
     */
    public static Date of(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        return localDateTime2Date(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second));
    }


    /**
     * 根据年月日 构建日期
     */
    public static Date of(int year, int month, int dayOfMonth) {
        return localDate2Date(LocalDate.of(year, month, dayOfMonth));
    }

    /**
     * 获取给定日期当月的最后一天
     */
    private static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取给定日期当月的最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        return localDate2Date(getLastDayOfMonth(date2localDate(date)));
    }

    /**
     * 获取给定日期当月的第一天
     */
    private static LocalDate getFirstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取给定日期当月的第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        return localDate2Date(getFirstDayOfMonth(date2localDate(date)));
    }

    /**
     * 获取当月的第一天
     */
    public static Date getFirstDayOfMonth() {
        return localDate2Date(getFirstDayOfMonth(LocalDate.now()));
    }

    /**
     * 获取当月的最后一天
     */
    public static Date getLastDayOfMonth() {
        return localDate2Date(getLastDayOfMonth(LocalDate.now()));
    }

    /**
     * 获取指定日期00:00:00时间
     */
    public static Date getFirstTimeOfDay(Date date) {
        LocalDate localDate = date2localDate(date);
        LocalDateTime of = LocalDateTime
                .of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0);
        return localDateTime2Date(of);
    }

    /**
     * 获取指定日期23:59:59时间
     */
    public static Date getLastTimeOfDay(Date date) {
        LocalDate localDate = date2localDate(date);
        LocalDateTime of = LocalDateTime
                .of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 23, 59, 59);
        return localDateTime2Date(of);
    }

    /**
     * 获取给定日期当月的最后一天,如果是当月则返回当日时间
     */
    private static LocalDate getLastOrCurrentDayOfDate(LocalDate localDate) {
        LocalDate now = LocalDate.now();
        if (Objects.equals(now.getYear(), localDate.getYear()) && Objects
                .equals(now.getMonth(), localDate.getMonth())) {
            return now;
        }
        return getLastDayOfMonth(localDate);
    }

    /**
     * 获取给定日期当月的最后一天,如果是当月则返回当日时间
     */
    public static Date getLastOrCurrentDayOfDate(Date date) {
        return localDate2Date(getLastOrCurrentDayOfDate(date2localDate(date)));
    }

    /**
     * 获取给定日期前n月的最后一天
     */
    private static LocalDate getLastDayOfLocalDateMinusMonths(LocalDate localDate, long months) {
        return getLastDayOfMonth(localDate).minusMonths(months);
    }

    /**
     * 获取给定日期前n月的最后一天g
     */
    public static Date getLastDayOfLocalDateMinusMonths(Date date, long months) {
        return localDate2Date(getLastDayOfLocalDateMinusMonths(date2localDate(date), months));
    }

    /**
     * 获取给定日期后n月的最后一天
     */
    private static LocalDate getLastDayOfLocalDatePlusMonths(LocalDate localDate, long months) {
        return getLastDayOfMonth(localDate).plusMonths(months);
    }

    /**
     * 获取给定日期后n月的最后一天
     */
    public static Date getLastDayOfLocalDatePlusMonths(Date date, long months) {
        return localDate2Date(getLastDayOfLocalDatePlusMonths(date2localDate(date), months));
    }

    /**
     * 获取给定日期的最后一天的前n天
     */
    private static LocalDate getLastDayOfLocalDateMinusDays(LocalDate localDate, long days) {
        return getLastDayOfMonth(localDate).minusDays(days);
    }

    /**
     * 获取给定日期的最后一天的前n天
     */
    public static Date getLastDayOfLocalDateMinusDays(Date date, long days) {
        return localDate2Date(getLastDayOfLocalDateMinusDays(date2localDate(date), days));
    }

    /**
     * 获取给定日期后n月的最后一天
     */
    private static LocalDate getLastDayOfLocalDatePlusDays(LocalDate localDate, long days) {
        return getLastDayOfMonth(localDate).plusDays(days);
    }

    /**
     * 获取给定日期后n月的最后一天
     */
    public static Date getLastDayOfLocalDatePlusDays(Date date, long days) {
        return localDate2Date(getLastDayOfLocalDatePlusDays(date2localDate(date), days));
    }

    /**
     * 得到当前日期n分钟后的日期
     */
    public static Date plusMinutes(Date date, long minutes) {
        return localDateTime2Date(date2localDateTime(date).plusMinutes(minutes));
    }


    /**
     * 得到当前日期n分钟前的日期
     */
    public static Date minusMinutes(Date date, long minutes) {
        return plusMinutes(date, -minutes);
    }


    /**
     * 得到当前日期n小时后的日期
     */
    public static Date plusHours(Date date, long hours) {
        return localDateTime2Date(date2localDateTime(date).plusHours(hours));
    }


    /**
     * 得到当前日期n小时前的日期
     */
    public static Date minusHours(Date date, long hours) {
        return plusHours(date, -hours);
    }

    /**
     * 得到当前日期n天后的日期
     */
    public static Date plusDays(Date date, long days) {
        return localDateTime2Date(date2localDateTime(date).plusDays(days));
    }

    /**
     * 得到当前日期n天前的日期
     */
    public static Date minusDays(Date date, long days) {
        return plusDays(date, -days);
    }

    /**
     * 得到当前日期n月后的日期
     */
    public static Date plusMonths(Date date, long months) {
        return localDateTime2Date(date2localDateTime(date).plusMonths(months));
    }

    /**
     * 得到当前日期n月前的日期
     */
    public static Date minusMonths(Date date, long months) {
        return plusMonths(date, -months);
    }

    /**
     * 得到传入时间的小时 0~23
     */
    private static int getHour(LocalDateTime localDateTime) {
        return localDateTime.getHour();
    }

    /**
     * 设置小时
     */
    private static LocalDateTime setHour(LocalDateTime localDateTime, int hour) {
        return localDateTime.withHour(hour);
    }


    /**
     * 得到传入时间为周几
     */
    private static int getDayOfWeek(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 得到传入时间为周几
     */
    public static int getDayOfWeek(Date date) {
        return getDayOfWeek(date2localDate(date));
    }


    /**
     * 得到现在为周几
     */
    public static int getDayOfWeek() {
        return getDayOfWeek(LocalDate.now());
    }

    /**
     * 得到传入时间的小时
     */
    public static int getHour(Date date) {
        return getHour(date2localDateTime(date));
    }

    /**
     * 得到现在的小时
     */
    public static int getHour() {
        return getHour(LocalDateTime.now());
    }

    /**
     * 设置小时
     */
    public static Date setHour(Date date, int hour) {
        return localDateTime2Date(setHour(date2localDateTime(date), hour));
    }

    /**
     * 判断是否为默认时间1970-01-01 00:00:00
     */
    public static boolean isDateEqualDefaultTime(Date date) {
        if (date == null) {
            return false;
        }
        return secondsBetweenStartAndEnd(date, ofDefault()) == 0;
    }

    /**
     * 得到两个日期的相差的秒数
     */
    private static long secondsBetweenStartAndEnd(LocalDateTime begin, LocalDateTime end) {
        return Duration.between(begin, end).getSeconds();
    }

    /**
     * 得到两个日期的相差的秒数
     */
    public static long secondsBetweenStartAndEnd(Date begin, Date end) {
        return secondsBetweenStartAndEnd(date2localDateTime(begin), date2localDateTime(end));
    }

    /**
     * 得到两个日期的相差的天数
     */
    private static int daysBetweenStartAndEnd(LocalDate begin, LocalDate end) {
        return Period.between(begin, end).getDays();
    }

    /**
     * 得到两个日期的相差的月数
     */
    private static int monthsBetweenStartAndEnd(LocalDate begin, LocalDate end) {
        return Period.between(begin, end).getMonths();
    }

    /**
     * 得到两个日期的相差的年数
     */
    private static int yearsBetweenStartAndEnd(LocalDate begin, LocalDate end) {
        return Period.between(begin, end).getYears();
    }


    /**
     * 得到两个日期的相差的天数
     */
    public static int daysBetweenStartAndEnd(Date begin, Date end) {
        return daysBetweenStartAndEnd(date2localDate(begin), date2localDate(end));
    }

    /**
     * 得到两个日期的相差的月数
     */
    public static int monthsBetweenStartAndEnd(Date begin, Date end) {
        return monthsBetweenStartAndEnd(date2localDate(begin), date2localDate(end));
    }

    /**
     * 得到两个日期的相差的年数
     */
    public static int yearsBetweenStartAndEnd(Date begin, Date end) {
        return yearsBetweenStartAndEnd(date2localDate(begin), date2localDate(end));
    }

    /**
     * 格式化为对应pattern
     */
    private static String formatWithPattern(LocalDateTime localDate, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    /**
     * 格式化为对应pattern
     */
    public static String formatWithPattern(Date date, String pattern) {
        return formatWithPattern(date2localDateTime(date), pattern);
    }

    /**
     * 格式化为yyyy-MM-dd HH:mm:ss
     */
    public static String formatWithDateTimePattern(Date date) {
        return formatWithPattern(date, DATE_TIME_PATTERN);
    }

    /**
     * 格式化为yyyy-MM-dd
     */
    public static String formatWithDatePattern(Date date) {
        return formatWithPattern(date, DATE_PATTERN);
    }

    /**
     * 格式化为yyyyMMdd
     */
    public static String formatWithDateNoseparatorPattern(Date date) {
        return formatWithPattern(date, DATE_WITHOUT_SEPARATOR_PATTERN);
    }

    /**
     * 转换为date
     */
    public static Date parseWithDateTimePattern(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return localDateTime2Date(dateTime);
    }

    /**
     * 转换为date
     */
    public static Date parseWithDatePattern(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate2Date(localDate);
    }

    /**
     * 获取当前整分钟时间
     */
    public static Date getCurrentWholeMinutes(Date date) {
        LocalDateTime now = date2localDateTime(date);
        return localDateTime2Date(LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), DEFAULT_VALUE));
    }

    /**
     * LocalDateTime 转 Date
     */
    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime date2localDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDate转 Date
     */
    private static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     */
    public static LocalDate date2localDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 前面的日期是否在后面的日期之前
     */
    public static boolean firstIsBeforeSecond(Date first, Date second) {

        LocalDateTime firstLocalDate = date2localDateTime(first);

        LocalDateTime secondLocalDate = date2localDateTime(second);

        return firstLocalDate.isBefore(secondLocalDate);
    }
}
