package com.alexis.reto_meli;

import android.app.Application;

import androidx.annotation.NonNull;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class RetoMeliApplication extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        
    }
}
