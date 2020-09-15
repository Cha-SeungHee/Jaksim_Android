package com.artineer.jaksim.common.calendar;

import com.artineer.jaksim.support.utils.DateTimeUtils;
import com.artineer.jaksim.common.date.SupportDateTimeRange;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class CalendarDateTime {

    @Getter
    @Setter
    public static LocalDateTime selectedDateTime = DateTimeUtils.getNow();
}