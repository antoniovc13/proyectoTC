package com.tivit.talmatc.feature.flight.selected;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.remote.model.Login;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface FlightContract {

    interface FlightView extends MvpView {
        void openMainActivity();
        void showFlightView();
    }

    interface FlightPresenter<V extends FlightView> extends MvpPresenter<V> {
        void onViewInitialized();
        void saveTypeFlight(String flight);
    }

    interface FlightInteractor extends MvpInteractor {
        void saveTypeFlight(String flight);
    }

    interface OnFlightListener {
        void onFlightSuccess();
        void onFlightError(String message);
    }
}
