package com.artineer.jaksim.ui.month.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.artineer.jaksim.support.utils.DateTimeUtils;
import com.artineer.jaksim.ui.month.MonthFragment;

public class MonthViewPagerAdapter extends FragmentStatePagerAdapter {

	public MonthViewPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
	}

	@NonNull
	@Override
	public Fragment getItem(int position) {
		return MonthFragment.newInstance(position);
	}

	@Override
	public int getCount() {
		return DateTimeUtils.getSupportMonthCount();
	}
}
