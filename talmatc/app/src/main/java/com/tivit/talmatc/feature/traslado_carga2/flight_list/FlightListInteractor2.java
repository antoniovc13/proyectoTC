package com.tivit.talmatc.feature.traslado_carga2.flight_list;

import android.support.annotation.NonNull;

import com.tivit.talmatc.base.ui.BaseInteractor;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class FlightListInteractor2 extends BaseInteractor implements FlightListContract2.FlightListInteractor {

    public FlightListInteractor2() {
        super();
    }

/*
    @Override
    public Flight findFlight(String flight) {
        //getAppDataManager().getAppPreferencesData().saveTypeFlight(flight);

        Flight f = new Flight();
        f.setCode(flight);
        f.setDescripcion(flight);
        f.setPea("8");
        f.setEta("08:00");
        f.setEtd("08:45");
        f.setDrawable(R.mipmap.ic_flight_takeoff_black_24dp);
        return f;
    }
*/

    @Override
    public void callLocalListFlight(final FlightListContract2.OnFlightListListener onFlightListListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlights()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .onExceptionResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.d("interactor - callLocalListFlight - flightList:"+flightList.size());
                        onFlightListListener.updateListAutocomplete(flightList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("callLocalListFlight - onError");
                        Timber.e(e);
                        onFlightListListener.updateListAutocomplete(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onFlightListListener.showProgressContent();
                    }
                }));
    }


    @Override
    public void callApiListFlightIni(final FlightListContract2.OnFlightListListener onFlightListListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlightAssociate()
                .map(flightResponse -> flightResponse.getContent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.e("interactor - callApiListFlightIni - paso1");
                        onFlightListListener.updateFlightList(flightList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        //onFlightListListener.showProgressError(e.getMessage());
                        onFlightListListener.updateFlightList(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onFlightListListener.showProgressContent();
                    }
                }));
    }


    @Override
    public void callLocalFindFlightByCode(final FlightListContract2.OnFlightListListener onFlightListListener, String code) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findFlightByCode(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Flight>() {
                    @Override
                    public void onNext(@NonNull Flight flight) {
                        Timber.e("interactor - callLocalFindFlightByCode - paso1");
                        onFlightListListener.addFlightToList(0, flight);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
