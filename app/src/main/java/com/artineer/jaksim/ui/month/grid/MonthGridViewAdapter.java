package com.artineer.jaksim.ui.month.grid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.artineer.jaksim.R;
import com.artineer.jaksim.common.calendar.CalendarDateTime;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;
import com.artineer.jaksim.support.utils.DateTimeUtils;
import com.artineer.jaksim.support.utils.DrawableUtils;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;

public class MonthGridViewAdapter extends BaseAdapter {

	private List<DailyDrinkInfo> dailyDrinkInfoList = new ArrayList<>();

	public void setDailyDrinkInfoList(List<DailyDrinkInfo> dailyDrinkInfoList) {
		this.dailyDrinkInfoList = dailyDrinkInfoList;

		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DayViewHolder dayViewHolder;

		if (convertView != null) {
			dayViewHolder = (DayViewHolder)convertView.getTag();
		} else {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_day_of_month, null, false);
			dayViewHolder = new DayViewHolder(convertView);
			convertView.setTag(dayViewHolder);
		}

		bind(dayViewHolder, position);

		return convertView;
	}

	private void bind(DayViewHolder dayViewHolder, int position) {
		DailyDrinkInfo dailyDrinkInfo = dailyDrinkInfoList.get(position);
		if (dailyDrinkInfo == null) {
			return;
		}

		dayViewHolder.setHeight(dailyDrinkInfo.getDateTime());
		dayViewHolder.bindDayOfMonth(dailyDrinkInfo.getDateTime());
		dayViewHolder.bindDayOfMonthBackground(dailyDrinkInfo.getDateTime());
		dayViewHolder.bindDrunkCircle(dailyDrinkInfo.getDailyDrinkStatus());
	}

	@Override
	public int getCount() {
		return dailyDrinkInfoList.size();
	}

	@Override
	public DailyDrinkInfo getItem(int position) {
		return dailyDrinkInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class DayViewHolder {

		@BindView(R.id.day_of_month_box)
		TextView dayOfMonthBox;
		@BindView(R.id.day_of_month)
		TextView dayOfMonth;
		@BindView(R.id.drink_circle)
		TextView drinkCircle;

		@BindColor(R.color.skyblue)
		int todayColor;
		@BindColor(R.color.grey)
		int selectDateColor;
		@BindColor(R.color.white)
		int dateColor;

		@BindDimen(R.dimen.calendar_month_height_for_view)
		int height;

		@BindDrawable(R.drawable.drawable_circle)
		Drawable dayOfMonthDrawable;

		DayViewHolder(View converterView) {
			ButterKnife.bind(this, converterView);
		}

		private void setHeight(LocalDateTime dateTime) {
			int weekCount = DateTimeUtils.getWeekCount(dateTime);
			dayOfMonthBox.setHeight(height / weekCount);
		}

		private void bindDayOfMonth(LocalDateTime dateTime) {
			if (isDifferentMonth(dateTime)) {
				dayOfMonth.setVisibility(View.INVISIBLE);
				return;
			}

			dayOfMonth.setText(String.format(Locale.KOREA, "%d", dateTime.getDayOfMonth()));

			if (DateTimeUtils.isSunday(dateTime)) {
				dayOfMonth.setTextColor(Color.RED);
			} else {
				dayOfMonth.setTextColor(Color.BLACK);
			}

			dayOfMonth.setVisibility(View.VISIBLE);
		}

		private boolean isDifferentMonth(LocalDateTime dateTime) {
			LocalDateTime lastDayOfMonth = getItem(getCount() - 1).getDateTime();
			return !DateTimeUtils.isSameMonth(dateTime, lastDayOfMonth);
		}

		private void bindDayOfMonthBackground(LocalDateTime dateTime) {
			Drawable drawable = DrawableUtils.wrapTint(dayOfMonthDrawable, dateColor);

			if (DateTimeUtils.isSameDate(CalendarDateTime.selectedDateTime, dateTime)) {
				drawable = DrawableUtils.wrapTint(dayOfMonthDrawable, selectDateColor);
			}

			if (DateTimeUtils.isSameDate(DateTimeUtils.getNow(), dateTime)) {
				drawable = DrawableUtils.wrapTint(dayOfMonthDrawable, todayColor);
			}

			dayOfMonth.setBackground(drawable);
		}

		private void bindDrunkCircle(@Nullable DailyDrinkStatus dailyDrinkStatus) {
			if (dailyDrinkStatus == null || dailyDrinkStatus.getDrinkAmount() <= 0) {
				drinkCircle.setVisibility(View.INVISIBLE);
				return;
			}

			drinkCircle.setVisibility(View.VISIBLE);
		}
	}
}