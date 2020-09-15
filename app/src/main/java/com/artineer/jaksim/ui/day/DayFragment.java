package com.artineer.jaksim.ui.day;

import java.time.LocalDateTime;
import java.util.Locale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.artineer.jaksim.R;
import com.artineer.jaksim.common.calendar.CalendarDateTime;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import com.artineer.jaksim.ui.base.BaseFragment;
import com.artineer.jaksim.ui.calendar.CalendarViewModel;

public class DayFragment extends BaseFragment {

	@BindView(R.id.day_of_month)
	TextView dateText;
	@BindView(R.id.drink_name_text)
	TextView menuText;
	@BindView(R.id.drink_amount_text)
	TextView amountText;
	@BindView(R.id.drink_memo_text)
	TextView memoText;

	private CalendarViewModel calendarViewModel;
	private DailyDrinkInfoViewModel dailyDrinkInfoViewModel;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViewModel();
	}

	private void initViewModel() {
		if (getActivity() != null) {
			calendarViewModel = new ViewModelProvider(getActivity()).get(CalendarViewModel.class);

			dailyDrinkInfoViewModel = new ViewModelProvider(getActivity()).get(DailyDrinkInfoViewModel.class);
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_day_drink_info, container, false);

		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		observeEvents();

		fetchItems();
	}

	private void observeEvents() {
		calendarViewModel.getSelectedDateTime().observe(getViewLifecycleOwner(), this::fetchDailyDrinkInfo);

		dailyDrinkInfoViewModel.getDailyDrinkInfo().observe(getViewLifecycleOwner(), this::showDailyDrinkInfo);
	}

	private void fetchItems() {
		fetchDailyDrinkInfo(CalendarDateTime.getSelectedDateTime());
	}

	private void fetchDailyDrinkInfo(LocalDateTime selectedDateTime) {
		dailyDrinkInfoViewModel.fetchDailyDrinkInfo(selectedDateTime);
	}

	private void showDailyDrinkInfo(DailyDrinkInfo dailyDrinkInfo) {
		showDate(dailyDrinkInfo.getDateTime());

		showMenu(dailyDrinkInfo.getDailyDrinkStatus());

		showDrinkAmount(dailyDrinkInfo.getDailyDrinkStatus());

		showMemo(dailyDrinkInfo.getDailyDrinkStatus());
	}

	private void showDate(LocalDateTime dateTime) {
		dateText.setText(String.format(Locale.KOREA, "%d.%d", dateTime.getMonthValue(), dateTime.getDayOfMonth()));
	}

	private void showMenu(@Nullable DailyDrinkStatus dailyDrinkStatus) {
		if (dailyDrinkStatus != null) {
			menuText.setText(dailyDrinkStatus.getMenu());
		} else {
			menuText.setText(R.string.daily_drink_status_default_menu);
		}
	}

	private void showDrinkAmount(@Nullable DailyDrinkStatus dailyDrinkStatus) {
		if (dailyDrinkStatus != null) {
			amountText.setText(String.format(Locale.KOREA, "%d", dailyDrinkStatus.getDrinkAmount()));
		} else {
			amountText.setText(R.string.daily_drink_status_default_amount);
		}
	}

	private void showMemo(@Nullable DailyDrinkStatus dailyDrinkStatus) {
		if (dailyDrinkStatus != null) {
			memoText.setText(dailyDrinkStatus.getMemo());
		} else {
			memoText.setText(R.string.daily_drink_status_default_memo);
		}
	}
}
