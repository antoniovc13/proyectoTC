package com.tivit.talmatc.feature.flight.selected;

import com.tivit.talmatc.base.ui.BasePresenter;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class FlightPresenter<V extends FlightContract.FlightView> extends BasePresenter<V> implements FlightContract.FlightPresenter<V>, FlightContract.OnFlightListener {

    private FlightContract.FlightInteractor mInteractor;

    public FlightPresenter(V mView) {
        super(mView);
        mInteractor = new FlightInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void onViewInitialized() {
        //mInteractor.callPreferencesCheckAuth(this);
    }

    @Override
    public void saveTypeFlight(String flight) {
        mInteractor.saveTypeFlight(flight);
    }

    @Override
    public void onFlightSuccess() {

    }

    @Override
    public void onFlightError(String message) {

    }



}
