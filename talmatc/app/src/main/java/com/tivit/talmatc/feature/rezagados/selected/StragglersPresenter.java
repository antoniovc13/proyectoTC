package com.tivit.talmatc.feature.rezagados.selected;

import com.tivit.talmatc.base.ui.BasePresenter;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 27/08/2017.
 */

public class StragglersPresenter<V extends StragglersContract.StragglersView> extends BasePresenter<V> implements StragglersContract.StragglersPresenter<V>, StragglersContract.OnStragglersListener {

    private StragglersContract.StragglersInteractor mInteractor;

    public StragglersPresenter(V mView) {
        super(mView);
        mInteractor = new StragglersInteractor();
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        mInteractor.onUnsubscribe();
    }

    /* ======= START PRESENTER ======= */

    @Override
    public void onViewInitialized() {
        mView.showProgressLoading();
        mInteractor.callLocalListFlight(this);
        mInteractor.callApiListFlightIni(this);
    }


    @Override
    public void showProgressContent() {
        mView.showProgressContent();
    }
/*
    @Override
    public void hideLoading() {
        mView.hideLoading();
    }
*/
    @Override
    public void showProgressError(String message) {
        mView.hideLoading();
        mView.showProgressError(message);
    }
/*
    @Override
    public void updateFlightList(List<Flight> flightList) {
        Timber.e("presenter - updateFlightList - paso1");
        if(flightList.isEmpty()){
            mView.showProgressEmpty();
        }
        mView.updateFlightList(flightList);
        Timber.e("presenter - updateFlightList - paso2");
    }
*/
    @Override
    public void updateListAutocomplete(List<Flight> flightList) {
        Timber.e("presenter - updateListAutocomplete - ini");
        mView.updateListAutocomplete(flightList);
        Timber.e("presenter - updateListAutocomplete - fin");
    }
/*
    @Override
    public void addFlightToList(int position, Flight flight) {
        Timber.d("presenter - addFlightToList - paso1");
        if(flight == null){
            mView.showProgressEmpty();
        }
        mView.addFlightToList(0, flight);
        Timber.d("presenter - addFlightToList - paso2");
    }
*/


    @Override
    public void addFlight(String flight) {
        mView.showProgressLoading();
        mInteractor.callLocalFindFlightByCode(this, flight);
    }


}
