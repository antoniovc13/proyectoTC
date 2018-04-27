package com.tivit.talmatc.feature.rezagados.selected;

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

public class StragglersInteractor extends BaseInteractor implements StragglersContract.StragglersInteractor {

    public StragglersInteractor() {
        super();
    }



    @Override
    public void callLocalListFlight(final StragglersContract.OnStragglersListener onStragglersListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlights()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .onExceptionResumeNext(Observable.just(new ArrayList<>()))//se agrega esta linea aparentemente no funciona
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.d("interactor - callLocalListFlight - flightList:"+flightList.size());
                        onStragglersListener.updateListAutocomplete(flightList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("callLocalListFlight - onError");
                        Timber.e(e);
                        onStragglersListener.updateListAutocomplete(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onStragglersListener.showProgressContent();
                    }
                }));
    }


    @Override
    public void callApiListFlightIni(final StragglersContract.OnStragglersListener onStragglersListener) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findAllFlightAssociate()
                .map(flightResponse -> flightResponse.getContent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(@NonNull List<Flight> flightList) {
                        Timber.e("interactor - callApiListFlightIni - paso1");
                        //onStragglersListener.updateFlightList(flightList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                        //onFlightListListener.showProgressError(e.getMessage());
                        //onStragglersListener.updateFlightList(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                        onStragglersListener.showProgressContent();
                    }
                }));
    }


    @Override
    public void callLocalFindFlightByCode(final StragglersContract.OnStragglersListener onStragglersListener, String code) {

        compositeDisposable.add(getAppDataManager().getAppLocalData().getFlightServiceLocal().findFlightByCode(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Flight>() {
                    @Override
                    public void onNext(@NonNull Flight flight) {
                        Timber.e("interactor - callLocalFindFlightByCode - paso1");
                        //onStragglersListener.addFlightToList(0, flight);
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
