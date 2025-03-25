package utils;

import exception.BizException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
public class DateUtil {

    public static List<LocalDate> DateRange(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new BizException("开始时间不能大于结束时间");
        }
        List<LocalDate> dateList = new ArrayList<>();
        while (!start.isAfter(end)) {
            dateList.add(start);
            start = start.plusDays(1);
        }
        return dateList;
    }

    public static LocalDateTime beginOfMonth(String strDate) {
        try {
            YearMonth yearMonth = YearMonth.parse(strDate);
            return LocalDateTime.of(yearMonth.getYear(), yearMonth.getMonth(), 1, 0, 0);
        } catch (Exception e) {
            throw new BizException("日期格式不正确");
        }
    }

    public static LocalDateTime endOfMonth(String strDate) {
        try {
            YearMonth yearMonth = YearMonth.parse(strDate);
            return LocalDateTime.of(yearMonth.getYear(), yearMonth.getMonthValue(), yearMonth.lengthOfMonth(), 23, 59,
                    59);
        } catch (Exception e) {
            throw new BizException("日期格式不正确");
        }
    }

    public static boolean range(LocalDateTime localDateTime, LocalDateTime startTime, LocalDateTime endTime) {
        return localDateTime.isAfter(startTime) && localDateTime.isBefore(endTime);
    }

    public static String format(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static String format(LocalDateTime time, String format) {
        return DateTimeFormatter.ofPattern(format).format(time);
    }
}
