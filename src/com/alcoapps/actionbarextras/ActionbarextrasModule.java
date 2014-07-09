/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.alcoapps.actionbarextras;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.kroll.common.Log;

import android.app.Activity;
import android.app.ActionBar;
import android.view.ViewConfiguration;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.text.Spannable;
import android.text.SpannableString;

@Kroll.module(name = "Actionbarextras", id = "com.alcoapps.actionbarextras")
public class ActionbarextrasModule extends KrollModule {

	// Standard Debugging variables
	private static final String TAG = "ActionbarextrasModule";

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public ActionbarextrasModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(TAG, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created

		// hack taken from:
		// http://stackoverflow.com/questions/9286822/how-to-force-use-of-overflow-menu-on-devices-with-menu-button
		try {
			ViewConfiguration config = ViewConfiguration.get(app);
			java.lang.reflect.Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception ex) {
			// Ignore
		}
	}

	// Methods
	@Kroll.method
	public void setExtras(KrollDict args) {
		Log.d(TAG, "called the setextras method");

		// declare stuff
		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		ActionBar actionBar = activity.getActionBar();

		if (!TiApplication.isUIThread()) {

			if (args.containsKey(TiC.PROPERTY_TITLE)) {
				actionBar.setTitle((String) args.get(TiC.PROPERTY_TITLE));
			}

			if (args.containsKey(TiC.PROPERTY_SUBTITLE)) {
				actionBar.setSubtitle((String) args.get(TiC.PROPERTY_SUBTITLE));
			}

			if (args.containsKey(TiC.PROPERTY_BACKGROUND_COLOR)) {
				actionBar.setBackgroundDrawable(new ColorDrawable(Color
						.parseColor((String) args
								.get(TiC.PROPERTY_BACKGROUND_COLOR))));
			}

			if (args.containsKey(TiC.PROPERTY_FONT)) {
				setFont(TiConvert.toString(args.get(TiC.PROPERTY_FONT)));
			}
		}
	}

	@Kroll.method
	public void setFont(String value) {
		setTitleFont(value);
		setSubtitleFont(value);
	}

	@Kroll.method
	public void setTitleFont(String value) {
		Log.d(TAG, "setTitleFont: " + value);
		try {
			TiApplication appContext = TiApplication.getInstance();
			Activity activity = appContext.getCurrentActivity();
			ActionBar actionBar = activity.getActionBar();

			String abTitle = TiConvert.toString(actionBar.getTitle());
			SpannableString s = new SpannableString(abTitle);
			s.setSpan(new TypefaceSpan(appContext, value), 0, s.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			actionBar.setTitle(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Kroll.method
	public void setSubtitleFont(String value) {
		Log.d(TAG, "setSubtitleFont: " + value);
		try {
			TiApplication appContext = TiApplication.getInstance();
			Activity activity = appContext.getCurrentActivity();
			ActionBar actionBar = activity.getActionBar();

			String abSubtitle = TiConvert.toString(actionBar.getSubtitle());
			if (abSubtitle != null){
				SpannableString s = new SpannableString(abSubtitle);
				s.setSpan(new TypefaceSpan(appContext, value), 0, s.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				actionBar.setSubtitle(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
