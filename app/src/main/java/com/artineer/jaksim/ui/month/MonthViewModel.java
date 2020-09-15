package com.artineer.jaksim.ui.month;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import com.artineer.jaksim.common.date.SupportDateTimeRange;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import com.artineer.jaksim.db.drinkstatus.DrinkStatusBO;
import com.artineer.jaksim.support.utils.DateTimeUtils;
import com.artineer.jaksim.ui.base.BaseViewModel;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;
import lombok.Getter;

public class MonthViewModel extends BaseViewModel {

	private DrinkStatusBO drinkStatusBO = new DrinkStatusBO();

	@Getter
	MutableLiveData<List<DailyDrinkInfo>> dailyDrinkInfoList = new MutableLiveData<>();

	void fetchDailyDrinkInfoList(int pagePosition) {
		executor.execute(() -> {
			LocalDateTime monthDateTime = SupportDateTimeRange.getMinDate().plusMonths(pagePosition);

			List<LocalDateTime> dayOfMonths = DateTimeUtils.getDayOfMonths(monthDateTime);

			List<DailyDrinkInfo> list = new ArrayList<>();
			dayOfMonths.forEach(dateTime -> {
				DailyDrinkStatus dailyDrinkStatus = drinkStatusBO.fetchDailyDrinkStatus(dateTime);
				DailyDrinkInfo dailyDrinkInfo = new DailyDrinkInfo(dateTime, dailyDrinkStatus);
				list.add(dailyDrinkInfo);
			});

			uiThreadHandler.post(() -> dailyDrinkInfoList.setValue(list));
		});
	}
}