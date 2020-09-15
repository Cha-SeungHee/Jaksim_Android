package com.artineer.jaksim.db;

import androidx.room.Room;
import com.artineer.jaksim.ui.JaksimApplication;

public class AppDatabaseManager {

	private static String DATABASE_NAME = "jaksim_database";

	private static AppDatabase INSTANCE =
			Room.databaseBuilder(JaksimApplication.getAppContext(), AppDatabase.class, DATABASE_NAME).build();

	public static AppDatabase getDatabase() {
		return INSTANCE;
	}
}