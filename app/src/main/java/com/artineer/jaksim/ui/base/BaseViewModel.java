package com.artineer.jaksim.ui.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

	protected ExecutorService executor = Executors.newFixedThreadPool(5);

	protected Handler uiThreadHandler = new Handler(Looper.getMainLooper());
}