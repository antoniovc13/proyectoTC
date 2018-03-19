package com.tivit.talmatc;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class TivitApplication extends MultiDexApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
