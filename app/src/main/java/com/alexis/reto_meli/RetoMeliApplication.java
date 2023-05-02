package com.alexis.reto_meli;

import android.app.Application;

import androidx.annotation.NonNull;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class RetoMeliApplication extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
        startAppCenter();
    }

    private void startAppCenter() {
        AppCenter.start(this, "",
                Analytics.class, Crashes.class);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Crashes.trackError(e);
    }
}
