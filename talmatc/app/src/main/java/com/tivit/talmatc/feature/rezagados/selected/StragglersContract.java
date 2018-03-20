package com.tivit.talmatc.feature.rezagados.selected;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface StragglersContract {

    interface StragglersView extends MvpView {
        void showProgressContent();
        void showProgressLoading();
        void showProgressEmpty();
        void showProgressError(String message);

        //void updateFlightList(List<Flight> flightList);
        void updateListAutocomplete(List<Flight> flightList);
        //void addFlightToList(int position, Flight flight);

    }

    interface StragglersPresenter<V extends StragglersView> extends MvpPresenter<V> {
        void onViewInitialized();
        void addFlight(String flight);
    }

    interface StragglersInteractor extends MvpInteractor {
        void callApiListFlightIni(StragglersContract.OnStragglersListener onFlightListListener);
        void callLocalListFlight(StragglersContract.OnStragglersListener onFlightListListener);
        void callLocalFindFlightByCode(StragglersContract.OnStragglersListener onFlightListListener, String code);
    }

    interface OnStragglersListener {
        void showProgressContent();
        void showProgressError(String message);

        //void updateFlightList(List<Flight> flightList);
        void updateListAutocomplete(List<Flight> flightList);
        //void addFlightToList(int position, Flight flight);

    }
}
