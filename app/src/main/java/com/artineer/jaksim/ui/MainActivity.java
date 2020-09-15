package com.artineer.jaksim.ui;

import android.os.Bundle;
import com.artineer.jaksim.R;
import com.artineer.jaksim.ui.base.BaseActivity;
import com.artineer.jaksim.ui.calendar.CalendarFragment;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		showCalendarFragment();
	}

	private void showCalendarFragment() {
		commitFragment(new CalendarFragment(), R.id.calendar_fragment);
	}
}