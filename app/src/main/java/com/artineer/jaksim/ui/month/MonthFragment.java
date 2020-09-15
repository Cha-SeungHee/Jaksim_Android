package com.artineer.jaksim.ui.month;

import java.time.LocalDateTime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.artineer.jaksim.R;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;
import com.artineer.jaksim.ui.base.BaseFragment;
import com.artineer.jaksim.ui.calendar.CalendarViewModel;
import com.artineer.jaksim.ui.day.DailyDrinkInfoViewModel;
import com.artineer.jaksim.ui.day.DailyDrinkRecordDialog;
import com.artineer.jaksim.ui.month.grid.MonthGridViewAdapter;

public class MonthFragment extends BaseFragment {

	private static final String PAGE_POSITION = "page_position";

	@BindView(R.id.month_grid_view)
	GridView monthGridView;
	private MonthGridViewAdapter monthGridViewAdapter;

	private CalendarViewModel calendarViewModel;
	private MonthViewModel monthViewModel;
	private DailyDrinkInfoViewModel dailyDrinkInfoViewModel;

	private int pagePosition;

	public static MonthFragment newInstance(int pagePosition) {
		MonthFragment fragment = new MonthFragment();
		Bundle args = new Bundle();
		args.putInt(PAGE_POSITION, pagePosition);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initArguments();

		initViewModels();
	}

	private void initArguments() {
		Bundle bundle = getArguments();
		if (bundle != null) {
			pagePosition = bundle.getInt(PAGE_POSITION);
		}
	}

	private void initViewModels() {
		if (getActivity() != null) {
			calendarViewModel = new ViewModelProvider(getActivity()).get(CalendarViewModel.class);

			dailyDrinkInfoViewModel = new ViewModelProvider(getActivity()).get(DailyDrinkInfoViewModel.class);

			monthViewModel = new ViewModelProvider(this).get(MonthViewModel.class);
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_month, container, false);

		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initMonthGridView();

		observeEvents();

		fetchItems();
	}

	private void initMonthGridView() {
		monthGridViewAdapter = new MonthGridViewAdapter();
		monthGridView.setAdapter(monthGridViewAdapter);
		monthGridView.setOnItemClickListener((parent, view, position, id) -> {
			LocalDateTime selectedDateTime = monthGridViewAdapter.getItem(position).getDateTime();
			calendarViewModel.setSelectedDateTime(selectedDateTime);

		});

		monthGridView.setOnItemLongClickListener((parent, view, position, id) -> {
			DailyDrinkInfo dailyDrinkInfo = monthGridViewAdapter.getItem(position);
			showDailyDrinkRecordDialog(dailyDrinkInfo);
			return true;
		});
	}

	private void observeEvents() {
		calendarViewModel.getSelectedDateTime().observe(getViewLifecycleOwner(), selectedDate -> monthGridViewAdapter.notifyDataSetChanged());

		monthViewModel.getDailyDrinkInfoList().observe(getViewLifecycleOwner(), list -> monthGridViewAdapter.setDailyDrinkInfoList(list));

		dailyDrinkInfoViewModel.getDailyDrinkInfo().observe(getViewLifecycleOwner(), dailyDrinkInfo -> fetchItems());
	}

	private void fetchItems() {
		monthViewModel.fetchDailyDrinkInfoList(pagePosition);
	}

	private void showDailyDrinkRecordDialog(DailyDrinkInfo dailyDrinkInfo) {
		if (getContext() != null) {
			DailyDrinkRecordDialog dailyDrinkRecordDialog = new DailyDrinkRecordDialog(getContext(), dailyDrinkInfo, this::updateDailyDrinkInfo);
			dailyDrinkRecordDialog.setCancelable(true);
			dailyDrinkRecordDialog.show();
		}
	}

	private void updateDailyDrinkInfo(DailyDrinkInfo dailyDrinkInfo) {
		calendarViewModel.setSelectedDateTime(dailyDrinkInfo.getDateTime());

		dailyDrinkInfoViewModel.updateDailyDrinkInfo(dailyDrinkInfo);
	}
}