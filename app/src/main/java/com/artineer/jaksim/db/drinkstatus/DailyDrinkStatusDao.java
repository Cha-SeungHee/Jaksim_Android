package com.artineer.jaksim.db.drinkstatus;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DailyDrinkStatusDao {

	@Query("SELECT * FROM dailydrinkstatus")
	List<DailyDrinkStatus> loadAll();

	@Query("SELECT * FROM dailydrinkstatus WHERE dateTime = :dateTime ")
	DailyDrinkStatus load(String dateTime);

	@Query("SELECT * FROM dailydrinkstatus WHERE dateTime >= :minDateTime AND dateTime <= :maxDateTime ")
	List<DailyDrinkStatus> loadBetween(String minDateTime, String maxDateTime);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertOrReplace(DailyDrinkStatus dailydrinkstatus);

	@Update
	void update(DailyDrinkStatus dailydrinkstatus);

	@Delete
	void delete(DailyDrinkStatus dailydrinkstatus);
}