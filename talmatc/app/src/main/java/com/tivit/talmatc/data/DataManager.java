package com.tivit.talmatc.data;

import com.tivit.talmatc.data.local.AppLocalData;
import com.tivit.talmatc.data.prefs.AppPreferencesData;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public interface DataManager {
    AppLocalData getAppLocalData();

    AppPreferencesData getmPreferencesHelper();


}
