package com.artineer.jaksim.ui.day;

import java.time.LocalDateTime;
import java.util.Locale;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.artineer.jaksim.R;
import com.artineer.jaksim.common.model.day.DailyDrinkInfo;
import com.artineer.jaksim.db.drinkstatus.DailyDrinkStatus;

public class DailyDrinkRecordDialog extends Dialog {

	@BindView(R.id.date)
	TextView dateText;
	@BindView(R.id.checkbox_soju)
	CheckBox sojuCheckBox;
	@BindView(R.id.checkbox_beer)
	CheckBox beerCheckBox;
	@BindView(R.id.checkbox_makgeol)
	CheckBox makgeolCheckBox;
	@BindView(R.id.checkbox_wine)
	CheckBox wineCheckBox;
	@BindView(R.id.checkbox_yangju)
	CheckBox yangjuCheckBox;
	@BindView(R.id.checkbox_cocktail)
	CheckBox cocktailCheckBox;
	@BindView(R.id.checkbox_goryang)
	CheckBox goryangCheckBox;
	@BindView(R.id.checkbox_sake)
	CheckBox sakeCheckBox;
	@BindView(R.id.seekbar_drink_amount)
	AppCompatSeekBar drinkAmountSeekbar;
	@BindView(R.id.text_memo)
	TextView memoText;
	@BindView(R.id.save_button)
	Button btn_closeDialog;

	private DailyDrinkInfo dailyDrinkInfo;

	private DialogCompleteListener dialogCompleteListener;

	public interface DialogCompleteListener {
		void onComplete(DailyDrinkInfo dailyDrinkInfo);
	}

	public DailyDrinkRecordDialog(@NonNull Context context, DailyDrinkInfo dailyDrinkInfo, DialogCompleteListener dialogCompleteListener) {
		super(context);

		this.dailyDrinkInfo = dailyDrinkInfo;
		this.dialogCompleteListener = dialogCompleteListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_drink_record);

		ButterKnife.bind(this);

		initViews();

		bindData();
	}

	private void initViews() {
		Window window = getWindow();
		if (window != null) {
			WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
			layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
			layoutParams.dimAmount = 0.5f;
			window.setAttributes(layoutParams);
			window.setGravity(Gravity.CENTER);
		}

		drinkAmountSeekbar.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
		drinkAmountSeekbar.getThumb().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
	}

	private void bindData() {
		bindDayOfMonth(dailyDrinkInfo.getDateTime());

		bindDailyDrinkStatus(dailyDrinkInfo.getDailyDrinkStatus());
	}

	private void bindDayOfMonth(LocalDateTime dateTime) {
		dateText.setText(String.format(Locale.KOREA, "%d월 %d일", dateTime.getMonthValue(), dateTime.getDayOfMonth()));
	}

	private void bindDailyDrinkStatus(@Nullable DailyDrinkStatus dailyDrinkStatus) {
		if (dailyDrinkStatus == null) {
			return;
		}

		sojuCheckBox.setChecked(dailyDrinkStatus.isDrinkSoju());
		beerCheckBox.setChecked(dailyDrinkStatus.isDrinkBeer());
		makgeolCheckBox.setChecked(dailyDrinkStatus.isDrinkMakgeol());
		wineCheckBox.setChecked(dailyDrinkStatus.isDrinkWine());
		yangjuCheckBox.setChecked(dailyDrinkStatus.isDrinkYangju());
		cocktailCheckBox.setChecked(dailyDrinkStatus.isDrinkCocktail());
		goryangCheckBox.setChecked(dailyDrinkStatus.isDrinkGoryang());
		sakeCheckBox.setChecked(dailyDrinkStatus.isDrinkSake());
		drinkAmountSeekbar.setProgress(dailyDrinkStatus.getDrinkAmount());
		memoText.setText(dailyDrinkStatus.getMemo());
	}

	@OnClick(R.id.save_button)
	void onSaveButtonClicked() {
		DailyDrinkStatus dailyDrinkStatus = dailyDrinkInfo.getDailyDrinkStatus();
		if (dailyDrinkStatus == null) {
			dailyDrinkStatus = new DailyDrinkStatus();
		}

		dailyDrinkStatus.setDrinkSoju(sojuCheckBox.isChecked());
		dailyDrinkStatus.setDrinkBeer(beerCheckBox.isChecked());
		dailyDrinkStatus.setDrinkMakgeol(makgeolCheckBox.isChecked());
		dailyDrinkStatus.setDrinkWine(wineCheckBox.isChecked());
		dailyDrinkStatus.setDrinkYangju(yangjuCheckBox.isChecked());
		dailyDrinkStatus.setDrinkCocktail(cocktailCheckBox.isChecked());
		dailyDrinkStatus.setDrinkGoryang(goryangCheckBox.isChecked());
		dailyDrinkStatus.setDrinkSake(sakeCheckBox.isChecked());
		dailyDrinkStatus.setDrinkAmount(drinkAmountSeekbar.getProgress());
		dailyDrinkStatus.setMemo(memoText.getText().toString());

		dailyDrinkInfo.setDailyDrinkStatus(dailyDrinkStatus);

		dialogCompleteListener.onComplete(dailyDrinkInfo);

		dismiss();
	}
}