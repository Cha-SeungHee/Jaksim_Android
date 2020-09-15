package com.artineer.jaksim.ui.calendar;

import java.time.LocalDateTime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.artineer.jaksim.R;
import com.artineer.jaksim.support.utils.DateTimeUtils;
import com.artineer.jaksim.ui.base.BaseFragment;
import com.artineer.jaksim.ui.month.pager.MonthViewPagerAdapter;
import com.artineer.jaksim.ui.day.DayFragment;

public class CalendarFragment extends BaseFragment {

	@BindView(R.id.month_title)
	TextView monthTitle;
	@BindView(R.id.year_title)
	TextView yearTitle;

	@BindView(R.id.month_pager)
	ViewPager monthViewPager;

	private CalendarViewModel calendarViewModel;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initViewModel();
	}

	private void initViewModel() {
		if (getActivity() != null) {
			calendarViewModel = new ViewModelProvider(getActivity()).get(CalendarViewModel.class);
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_calendar, container, false);

		ButterKnife.bind(this, view);

		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		setMonthViewPager();

		showDayFragment();

		observeEvents();

		fetchItems();
	}

	private void setMonthViewPager() {
		MonthViewPagerAdapter monthViewPagerAdapter = new MonthViewPagerAdapter(getChildFragmentManager());
		monthViewPager.setAdapter(monthViewPagerAdapter);
		monthViewPager.setCurrentItem(DateTimeUtils.getSelectDateTimeMonthPos());
		monthViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				LocalDateTime dateTime = DateTimeUtils.getDateTimeBy(position);

				calendarViewModel.setSelectedDateTime(dateTime);

				calendarViewModel.fetchSelectedDateTimeTitle();
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}

	private void showDayFragment() {
		commitFragment(new DayFragment(), R.id.day_fragment);
	}

	private void observeEvents() {
		calendarViewModel.getSelectedMonthTitle().observe(getViewLifecycleOwner(), monthTitle -> this.monthTitle.setText(monthTitle));

		calendarViewModel.getSelectedYearTitle().observe(getViewLifecycleOwner(), yearTitle -> this.yearTitle.setText(yearTitle));
	}

	private void fetchItems() {
		calendarViewModel.fetchSelectedDateTimeTitle();
	}
}