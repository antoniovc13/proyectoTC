package com.tivit.talmatc.feature.traslado_carga2.flight_list;

import com.tivit.talmatc.base.mvp.MvpInteractor;
import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public interface FlightListContract2 {

    interface FlightListView extends MvpView {
        void showProgressContent();
        void showProgressLoading();
        void showProgressEmpty();
        void showProgressError(String message);

        void updateFlightList(List<Flight> flightList);
        void updateListAutocomplete(List<Flight> flightList);
        void addFlightToList(int position, Flight flight);

    }

    interface FlightListPresenter<V extends FlightListView> extends MvpPresenter<V> {
        void onViewInitialized();
        void addFlight(String flight);
    }

    interface FlightListInteractor extends MvpInteractor {
        void callApiListFlightIni(FlightListContract2.OnFlightListListener onFlightListListener);
        void callLocalListFlight(FlightListContract2.OnFlightListListener onFlightListListener);
        void callLocalFindFlightByCode(FlightListContract2.OnFlightListListener onFlightListListener, String code);
    }

    interface OnFlightListListener {
        void showProgressContent();
        void showProgressError(String message);

        void updateFlightList(List<Flight> flightList);
        void updateListAutocomplete(List<Flight> flightList);
        void addFlightToList(int position, Flight flight);

    }
}
