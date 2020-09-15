package com.artineer.jaksim.common.date;

import java.time.LocalDateTime;

public class SupportDateTimeRange {

    public static LocalDateTime getMinDate() {
        return SupportDateTime.SOLAR.getMinSupportDateTime();
    }

    public static LocalDateTime getMaxDate() {
        return SupportDateTime.SOLAR.getMaxSupportDateTime();
    }
}