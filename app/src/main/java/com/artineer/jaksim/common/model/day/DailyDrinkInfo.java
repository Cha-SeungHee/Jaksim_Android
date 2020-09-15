package com.artineer.jaksim.common.model.day;

import java.time.LocalDateTime;

import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DailyDrinkInfo {
	LocalDateTime dateTime;

	DailyDrinkStatus dailyDrinkStatus;
}