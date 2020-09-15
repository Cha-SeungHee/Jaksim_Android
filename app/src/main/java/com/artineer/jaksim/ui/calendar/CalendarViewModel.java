package com.artineer.jaksim.ui.calendar;

import androidx.lifecycle.MutableLiveData;

import com.artineer.jaksim.common.calendar.CalendarDateTime;
import com.artineer.jaksim.ui.base.BaseViewModel;

import java.time.LocalDateTime;

import lombok.Getter;

public class CalendarViewModel extends BaseViewModel {

    @Getter
    MutableLiveData<LocalDateTime> selectedDateTime = new MutableLiveData<>();

    @Getter
    MutableLiveData<String> selectedYearTitle = new MutableLiveData<>();

    @Getter
    MutableLiveData<String> selectedMonthTitle = new MutableLiveData<>();

    public void setSelectedDateTime(LocalDateTime dateTime) {
        CalendarDateTime.selectedDateTime = dateTime;
        selectedDateTime.setValue(CalendarDateTime.selectedDateTime);
    }

    void fetchSelectedDateTimeTitle() {
        fetchSelectedYearTitle();
        fetchSelectedMonthTitle();
    }

    private void fetchSelectedYearTitle() {
        String yearTitle = CalendarDateTime.getSelectedDateTime().getYear() + "년";
        selectedYearTitle.setValue(yearTitle);
    }

    private void fetchSelectedMonthTitle() {
        String monthTitle = CalendarDateTime.getSelectedDateTime().getMonthValue() + "월";
        selectedMonthTitle.setValue(monthTitle);
    }
}