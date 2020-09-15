package com.artineer.jaksim.support.utils;

import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableUtils {

	public static Drawable wrapTint(@NonNull Drawable drawable, @ColorInt int color) {
		Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
		wrappedDrawable = wrappedDrawable.mutate();
		DrawableCompat.setTint(wrappedDrawable, color);
		return wrappedDrawable;
	}
}