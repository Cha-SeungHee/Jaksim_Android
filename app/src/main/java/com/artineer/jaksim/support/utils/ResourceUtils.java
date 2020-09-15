package com.artineer.jaksim.support.utils;

import androidx.annotation.StringRes;
import com.artineer.jaksim.ui.JaksimApplication;

public class ResourceUtils {

	public static String getString(@StringRes int resId) {
		return JaksimApplication.getAppContext().getString(resId);
	}
}