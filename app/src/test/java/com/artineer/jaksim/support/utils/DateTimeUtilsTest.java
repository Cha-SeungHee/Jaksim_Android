package com.artineer.jaksim.support.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.artineer.jaksim.common.date.SupportDateTimeRange;
import static org.junit.Assert.*;

@SuppressWarnings("NonAsciiCharacters")
public class DateTimeUtilsTest {

	@Test
	public void 최대_지원하는_month_개수_테스트() {
		long difference = DateTimeUtils.untilByMonth(SupportDateTimeRange.getMaxDate(), SupportDateTimeRange.getMinDate());

		assertEquals(959, difference);
	}

	@Test
	public void 팔월_한달_날짜_리스트_받아오는_로직_테스트() {
		LocalDateTime localDateTime = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
		List<LocalDateTime> dateTimes = DateTimeUtils.getDayOfMonths(localDateTime);
		dateTimes.forEach((dateTime) -> {
			String text = "year : " + dateTime.getYear() + ", month : " + dateTime.getMonthValue() + ", day : " + dateTime.getDayOfMonth();
			System.out.println(text);
		});

		assertEquals(31, dateTimes.size());
	}

	@Test
	public void 이월_한달_날짜_리스트_받아오는_로직_테스트() {
		LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 1, 0, 0, 0);
		List<LocalDateTime> dateTimes = DateTimeUtils.getDayOfMonths(localDateTime);
		dateTimes.forEach((dateTime) -> {
			String text = "year : " + dateTime.getYear() + ", month : " + dateTime.getMonthValue() + ", day : " + dateTime.getDayOfMonth();
			System.out.println(text);
		});

		assertEquals(29, dateTimes.size());
	}

	@Test
	public void 이월_주_카운트_테스트() {
		LocalDateTime dateTime = LocalDateTime.of(2026, 2, 1, 0, 0, 0);
		int weekCount = DateTimeUtils.getWeekCount(dateTime);
		assertEquals(4, weekCount);
	}

	@Test
	public void 사월__주_카운트_테스트() {
		LocalDateTime dateTime = LocalDateTime.of(2020, 4, 1, 0, 0, 0);
		int weekCount = DateTimeUtils.getWeekCount(dateTime);
		assertEquals(5, weekCount);
	}

	@Test
	public void 칠월_주_카운트_테스트() {
		LocalDateTime dateTime = LocalDateTime.of(2020, 7, 1, 0, 0, 0);
		int weekCount = DateTimeUtils.getWeekCount(dateTime);
		assertEquals(5, weekCount);
	}

	@Test
	public void 팔월_주_카운트_테스트() {
		LocalDateTime dateTime = LocalDateTime.of(2020, 8, 1, 0, 0, 0);
		int weekCount = DateTimeUtils.getWeekCount(dateTime);
		assertEquals(6, weekCount);
	}
}
