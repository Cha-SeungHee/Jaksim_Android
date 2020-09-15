package com.artineer.jaksim.db.drinkstatus;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.artineer.jaksim.R;
import com.artineer.jaksim.support.utils.ResourceUtils;
import com.artineer.jaksim.support.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "DailyDrinkStatus", primaryKeys = "dateTime")
@Getter
@Setter
public class DailyDrinkStatus {

	@NonNull
	@ColumnInfo(name = "dateTime")
	String dateTime;
	@ColumnInfo(name = "isDrinkSoju")
	boolean isDrinkSoju;
	@ColumnInfo(name = "isDrinkBeer")
	boolean isDrinkBeer;
	@ColumnInfo(name = "isDrinkMakgeol")
	boolean isDrinkMakgeol;
	@ColumnInfo(name = "isDrinkWine")
	boolean isDrinkWine;
	@ColumnInfo(name = "isDrinkYangju")
	boolean isDrinkYangju;
	@ColumnInfo(name = "isDrinkCocktail")
	boolean isDrinkCocktail;
	@ColumnInfo(name = "isDrinkGoryang")
	boolean isDrinkGoryang;
	@ColumnInfo(name = "isDrinkSake")
	boolean isDrinkSake;
	@ColumnInfo(name = "drinkAmount")
	int drinkAmount;
	@ColumnInfo(name = "memo")
	String memo;

	public String getMenu() {
		String menu = "";
		if (isDrinkSoju) {
			menu += ResourceUtils.getString(R.string.soju) + StringUtils.SPACE;
		}

		if (isDrinkBeer) {
			menu += ResourceUtils.getString(R.string.beer) + StringUtils.SPACE;
		}

		if (isDrinkMakgeol) {
			menu += ResourceUtils.getString(R.string.makgeol) + StringUtils.SPACE;
		}

		if (isDrinkYangju) {
			menu += ResourceUtils.getString(R.string.yangju) + StringUtils.SPACE;
		}

		if (isDrinkCocktail) {
			menu += ResourceUtils.getString(R.string.cocktail) + StringUtils.SPACE;
		}

		if (isDrinkGoryang) {
			menu += ResourceUtils.getString(R.string.goryang) + StringUtils.SPACE;
		}

		if (isDrinkSake) {
			menu += ResourceUtils.getString(R.string.sake) + StringUtils.SPACE;
		}

		return (menu.length() == 0) ? ResourceUtils.getString(R.string.daily_drink_status_default_menu) : menu;
	}

	public String getMemo() {
		if (memo == null) {
			return ResourceUtils.getString(R.string.daily_drink_status_default_memo);
		}

		return memo;
	}
}