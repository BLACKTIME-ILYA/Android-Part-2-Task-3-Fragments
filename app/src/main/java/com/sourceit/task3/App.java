package com.sourceit.task3;

import android.app.Application;

/**
 * Created by User on 29.01.2016.
 */
public class App extends Application {
    private static App Instanse;

    public static App getApp() {
        return Instanse;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instanse = this;
    }
}
