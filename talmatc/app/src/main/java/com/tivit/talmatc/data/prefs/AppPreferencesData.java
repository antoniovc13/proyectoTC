package com.tivit.talmatc.data.prefs;

import com.tivit.talmatc.data.remote.model.Authorization;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public interface AppPreferencesData {

    Observable<Boolean> isAuthentificated();

    Observable<Boolean> removeAuthorization();

    Authorization getAuthorization();

    void saveAuthorization(Authorization value);

    void deleteAuthorization();

    void saveTypeFlight(String flight);
}
