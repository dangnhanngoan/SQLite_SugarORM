package com.example.ngoan.sqlite_sugarorm.appconfig;

import android.app.Application;

import com.orm.SugarContext;

/**
 * Created by Ngoan on 04/11/2017.
 */

public class AppConfig extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
