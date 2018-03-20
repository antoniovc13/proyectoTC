package com.tivit.talmatc.feature.flight.selected;

import com.tivit.talmatc.base.ui.BaseInteractor;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class FlightInteractor extends BaseInteractor implements FlightContract.FlightInteractor {

    public FlightInteractor() {
        super();
    }

    @Override
    public void saveTypeFlight(String flight) {
        getAppDataManager().getAppPreferencesData().saveTypeFlight(flight);
    }
}
