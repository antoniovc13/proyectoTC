package com.tivit.talmatc.feature.traslado_carga3;

import com.tivit.talmatc.base.mvp.MvpPresenter;
import com.tivit.talmatc.base.mvp.MvpView;

/**
 * Created by Antonio.Valdivieso on 27/03/2018.
 */

public interface OrderContract {



    interface OrderView extends MvpView {
        void goToFlight();
        void backFlight();
        void goToFlightList();

    }
    interface OrderPresenter<V extends OrderView> extends MvpPresenter<V> {
        void goToFlight();
        void backFlight();
        void goToFlightList();

    }

    interface OnChangeTab{
        void goToFlight();
        void backFlight();
        void goToFlightList();
    }

}
