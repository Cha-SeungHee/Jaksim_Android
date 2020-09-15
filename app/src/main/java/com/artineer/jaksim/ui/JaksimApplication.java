package com.artineer.jaksim.ui;

import android.app.Application;
import com.artineer.jaksim.BuildConfig;
import com.facebook.stetho.Stetho;
import timber.log.Timber;

public class JaksimApplication extends Application {

	private static JaksimApplication instance;

	public static JaksimApplication getAppContext() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		initContext();

		initDependencies();
	}

	private void initContext() {
		instance = this;
	}

	private void initDependencies() {
		initTimber();

		initStetho();
	}

	private void initTimber() {
		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}

	private void initStetho() {
		Stetho.initializeWithDefaults(this);
	}
}