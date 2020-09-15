package com.artineer.jaksim.ui.day;

import java.time.LocalDateTime;

import androidx.lifecycle.MutableLiveData;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import com.artineer.jaksim.db.drinkstatus.DrinkStatusBO;
import com.artineer.jaksim.ui.base.BaseViewModel;
import lombok.Getter;

public class DailyDrinkInfoViewModel extends BaseViewModel {

	private DrinkStatusBO drinkStatusBO = new DrinkStatusBO();

	@Getter
	MutableLiveData<DailyDrinkInfo> dailyDrinkInfo = new MutableLiveData<>();

	public void updateDailyDrinkInfo(DailyDrinkInfo dailyDrinkInfo) {
		executor.execute(() -> {
			dailyDrinkInfo.getDailyDrinkStatus().setDateTime(dailyDrinkInfo.getDateTime().toString());

			drinkStatusBO.updateDailyDrinkStatus(dailyDrinkInfo);

			uiThreadHandler.post(() -> fetchDailyDrinkInfo(dailyDrinkInfo.getDateTime()));
		});
	}

	void fetchDailyDrinkInfo(LocalDateTime dateTime) {
		executor.execute(() -> {
			DailyDrinkStatus dailyDrinkStatus = drinkStatusBO.fetchDailyDrinkStatus(dateTime);

			uiThreadHandler.post(() -> dailyDrinkInfo.setValue(new DailyDrinkInfo(dateTime, dailyDrinkStatus)));
		});
	}
}