package com.ringcenter.sdkdemo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
class LocalDateTimeUtilTest {

    @Test
    void should_diff_1_minute() {
        LocalDateTime startTime = LocalDateTime.of(2020, 4, 30, 12, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 4, 30, 12, 1, 0);
        Assertions.assertEquals(1.0, LocalDateTimeUtil.computeMinuteDiff(startTime, endTime));
    }
}