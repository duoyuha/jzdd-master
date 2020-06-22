import cn.backend.config.message.CustomMessage;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.DateUtils;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CalTest {

    public static void main(String[] args) {
        //LocalDateTime startTime = LocalDateTime.of(2019, 7, 28, 9, 59, 00);
        // LocalDateTime startTime = LocalDateTime.of(2019, 7, 26, 9, 59, 00);
        // LocalDateTime startCompareTime = startTime;
        // LocalDateTime endTime = LocalDateTime.of(2019, 8, 7, 10, 23, 00);
        // int workDay = 0;
        // int weekendDay = 0;
        // while (startCompareTime.compareTo(endTime) <= 0) {
        //     if (startCompareTime.getDayOfWeek() != DayOfWeek.SATURDAY && startCompareTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
        //         workDay++;
        //     } else {
        //         weekendDay++;
        //     }
        //     startCompareTime = startCompareTime.plusDays(1);
        // }
        // System.out.println(workDay);
        // System.out.println(weekendDay);
        // if (workDay <= 1) {
        //     System.out.println("0");
        // } else {
        //     startTime = startTime.plusDays(weekendDay);
        //     Duration duration = Duration.between(startTime, endTime);
        //     System.out.println(duration.toMinutes());
        // }

//        LocalDateTime startTime = LocalDateTime.of(2019, 7, 26, 9, 00, 00);
//        LocalDateTime startCompareTime = startTime;
//        LocalDateTime endTime = LocalDateTime.of(2019, 8, 3, 10, 23, 00);
//        int workDay = 0;
//        int weekendDay = 0;
//        while (startCompareTime.compareTo(endTime) <= 0) {
//            if (startCompareTime.getDayOfWeek() != DayOfWeek.SATURDAY && startCompareTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
//                workDay++;
//            } else {
//                weekendDay++;
//            }
//            startCompareTime = startCompareTime.plusDays(1);
//        }
//        System.out.println(workDay);
//        System.out.println(weekendDay);
//        if (workDay <= 1) {
//            System.out.println("0");
//        } else {
//            startTime = startTime.plusDays(weekendDay);
//            Duration duration = Duration.between(startTime, endTime);
//            System.out.println(duration.toMinutes());
//        }

        Date start = DateUtils.parseDate("18:20","hh:mm");
        Date end = DateUtils.parseDate("15:03","hh:mm");
        //时间校验
        if (start.after(end)){
            throw new AppException(CustomMessage.TIME_ERROR);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(start);
        long timeInMillis1 = instance.getTimeInMillis();
//        instance = null;
//        start = null;

        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(end);
        long timeInMillis2 = instance2.getTimeInMillis();
//        instance2 = null;
//        end = null;

        double mins = (timeInMillis2 - timeInMillis1)/1000/60;
        DecimalFormat df = new DecimalFormat("##.#");
        String dff=df.format(mins);
        double result= Double.valueOf(dff);
        System.out.println(result);
    }
}
