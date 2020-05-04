package com.ringcenter.sdkdemo.utils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
public class LocalDateTimeUtil {

    private LocalDateTimeUtil() {
    }

    /**
     * 计算两个LocalDateTime之间的分钟差
     *
     * @param start
     * @param end
     * @return
     */
    public static long computeMinuteDiff(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMinutes();
    }

}
