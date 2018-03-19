package com.tivit.talmatc.data;

import android.content.Context;

import com.tivit.talmatc.data.local.AppLocalData;
import com.tivit.talmatc.data.local.AppLocalDataStore;
import com.tivit.talmatc.data.prefs.AppPreferencesData;
import com.tivit.talmatc.data.prefs.AppPreferencesDataStore;
import com.tivit.talmatc.data.remote.AppRemoteData;
import com.tivit.talmatc.data.remote.AppRemoteDataStore;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public class AppDataManager {

    private static AppDataManager instance;

    private AppLocalData appLocalData;
    private AppPreferencesData appPreferencesData;
    private AppRemoteData appRemoteData;

    private AppDataManager(Context context) {
        appLocalData = new AppLocalDataStore(context);
        appPreferencesData = new AppPreferencesDataStore(context);
        appRemoteData = new AppRemoteDataStore();
    }

    public static synchronized AppDataManager getInstance(Context context) {
        if(instance == null) {
            instance = new AppDataManager(context);
        }
        return instance;
    }

    public AppLocalData getAppLocalData() {
        return appLocalData;
    }

    public AppPreferencesData getAppPreferencesData() {
        return appPreferencesData;
    }

    public AppRemoteData getAppRemoteData() {
        return appRemoteData;
    }

}
