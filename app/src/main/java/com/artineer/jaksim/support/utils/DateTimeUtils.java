package com.artineer.jaksim.support.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

import com.artineer.jaksim.common.calendar.CalendarDateTime;
import com.artineer.jaksim.common.date.SupportDateTimeRange;

public class DateTimeUtils {

	public static LocalDateTime getNow() {
		return LocalDateTime.now();
	}

	public static int untilByMonth(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
		return (int)Math.abs(firstDateTime.until(secondDateTime, ChronoUnit.MONTHS));
	}

	public static List<LocalDateTime> getDayOfMonths(LocalDateTime dateTime) {
		LocalDateTime firstDateTime = getFirstDayOfMonth(dateTime);
		LocalDateTime lastDatetime = getLastDayOfMonth(dateTime);

		List<LocalDateTime> monthDateTimes = new ArrayList<>();

		int startIndex;
		if (firstDateTime.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue()) {
			startIndex = 0;
		} else {
			startIndex = -firstDateTime.getDayOfWeek().getValue();
		}

		for (int dayOfMonth = startIndex; dayOfMonth < lastDatetime.getDayOfMonth(); dayOfMonth++) {
			monthDateTimes.add(firstDateTime.plusDays(dayOfMonth));
		}

		return monthDateTimes;
	}

	private static LocalDateTime getFirstDayOfMonth(LocalDateTime dateTime) {
		return dateTime.with(TemporalAdjusters.firstDayOfMonth());
	}

	private static LocalDateTime getLastDayOfMonth(LocalDateTime dateTime) {
		return dateTime.with(TemporalAdjusters.lastDayOfMonth());
	}

	public static boolean isSunday(LocalDateTime dateTime) {
		return dateTime.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	public static boolean isSameDate(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
		return firstDateTime.toLocalDate().isEqual(secondDateTime.toLocalDate());
	}

	public static boolean isSameMonth(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
		return isSameYear(firstDateTime, secondDateTime) && firstDateTime.getMonthValue() == secondDateTime.getMonthValue();
	}

	private static boolean isSameYear(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
		return firstDateTime.getYear() == secondDateTime.getYear();
	}

	public static int getWeekCount(LocalDateTime dateTime) {
		LocalDate localDate = dateTime.toLocalDate();
		LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());
		return lastDayOfMonth.get(WeekFields.SUNDAY_START.weekOfMonth());
	}

	public static int getSupportMonthCount() {
		LocalDateTime minSupportDateTime = SupportDateTimeRange.getMinDate();
		LocalDateTime maxSupportDateTime = SupportDateTimeRange.getMaxDate();
		int difference = Math.abs(DateTimeUtils.untilByMonth(minSupportDateTime, maxSupportDateTime));
		return difference + 1;
	}

	public static int getSelectDateTimeMonthPos() {
		LocalDateTime minSupportDateTime = SupportDateTimeRange.getMinDate();
		return Math.abs(DateTimeUtils.untilByMonth(minSupportDateTime, CalendarDateTime.getSelectedDateTime()));
	}

	public static LocalDateTime getDateTimeBy(int monthPos) {
		return SupportDateTimeRange.getMinDate().plusMonths(monthPos);
	}
}