package com.artineer.jaksim.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatusDao;

@Database(entities = {DailyDrinkStatus.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

	public abstract DailyDrinkStatusDao dailyDrinkStatusDao();
}