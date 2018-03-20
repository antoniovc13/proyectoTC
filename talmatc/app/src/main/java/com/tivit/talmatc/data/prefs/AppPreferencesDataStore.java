package com.tivit.talmatc.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tivit.talmatc.data.remote.model.Authorization;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public class AppPreferencesDataStore implements AppPreferencesData {

    private static final String PREF_FILE_NAME = "TIVIT_TASK";
    private static final String PREF_KEY_CURRENT_USER = "PREF_KEY_CURRENT_USER";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_VUELO = "PREF_KEY_CURRENT_VUELO";

    private final SharedPreferences mPrefs;

    public AppPreferencesDataStore(Context context) {
        mPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /** AUTH **/

    @Override
    public Observable<Boolean> isAuthentificated() {
        return Observable.fromCallable(() -> getAuthorization() != null);
    }

    @Override
    public Observable<Boolean> removeAuthorization() {
        return Observable.fromCallable(() ->  {
            mPrefs.edit().remove(PREF_KEY_CURRENT_USER).apply();
            return true;
        });
    }

    @Override
    public Authorization getAuthorization() {
        if (mPrefs.contains(PREF_KEY_CURRENT_USER)) {
            return new Gson().fromJson(mPrefs.getString(PREF_KEY_CURRENT_USER, null), Authorization.class);
        } else {
            return null;
        }
    }

    @Override
    public void saveAuthorization(Authorization value) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER, new Gson().toJson(value)).apply();
    }

    @Override
    public void deleteAuthorization() {
        mPrefs.edit().remove(PREF_KEY_CURRENT_USER).apply();
    }

    @Override
    public void saveTypeFlight(String flight) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_VUELO, new Gson().toJson(flight)).apply();
    }
}
