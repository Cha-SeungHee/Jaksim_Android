package com.artineer.jaksim.db.drinkstatus;

import java.time.LocalDateTime;
import java.util.List;

import com.artineer.jaksim.db.AppDatabaseManager;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;

public class DrinkStatusBO {

	private DailyDrinkStatusDao dailyDrinkStatusDao;

	public DrinkStatusBO() {
		dailyDrinkStatusDao = AppDatabaseManager.getDatabase().dailyDrinkStatusDao();
	}

	public DailyDrinkStatus fetchDailyDrinkStatus(LocalDateTime dateTime) {
		return dailyDrinkStatusDao.load(dateTime.toString());
	}

	public List<DailyDrinkStatus> fetchDailyDrinkStatusList(LocalDateTime minDateTime, LocalDateTime maxDateTime) {
		return dailyDrinkStatusDao.loadBetween(minDateTime.toString(), maxDateTime.toString());
	}

	public void updateDailyDrinkStatus(DailyDrinkInfo dailyDrinkInfo) {
		dailyDrinkStatusDao.insertOrReplace(dailyDrinkInfo.getDailyDrinkStatus());
	}
}