package cn.backend.access.utils;/**
 * @author DuoYuHa on 2019/11/18
 */

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 *
 *@author DuoYuHa on 2019/11/18
 */
public class MonthUtils {

    /**
     * 处理日期 LocalDate
     */
    public static Date monthFirstDay(Date day){

        Instant instant = day.toInstant();
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();

        //本月第一天  2017-11-01
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        return Date.from(instant);
    }

    /**
     * 处理日期 LocalDate
     */
    public static Date monthLastDay(Date day){

        Instant instant = day.toInstant();
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();

        //本月最后一天 2017-02-28  方便解决任何年份的二月份多少天
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfMonth);

        return Date.from(instant);

    }


}
