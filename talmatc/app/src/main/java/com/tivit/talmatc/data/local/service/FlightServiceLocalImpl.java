package com.tivit.talmatc.data.local.service;

import com.tivit.talmatc.data.local.DbOpenHelper;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.data.local.repository.FlightRepository;
import com.tivit.talmatc.data.local.repository.FlightRepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 14/09/2017.
 */

public class FlightServiceLocalImpl implements FlightServiceLocal {

    private DbOpenHelper dbOpenHelper;
    private FlightRepository flightRepository;

    public FlightServiceLocalImpl(DbOpenHelper dbOpenHelper) {
        this.dbOpenHelper = dbOpenHelper;
        flightRepository = new FlightRepositoryImpl(dbOpenHelper);
    }

    @Override
    public Observable<List<Flight>> findAllFlights() {
        return Observable.fromCallable(() -> flightRepository.findAll());
    }

    @Override
    public Observable<List<Flight>> findAllFlightAssociate() {
        return Observable.fromCallable(() -> flightRepository.findAllByFlagAssociate(1));
    }

    @Override
    public Observable<Flight> findFlightByCode(final String code) {
        return Observable.fromCallable(() -> flightRepository.findByCode(code));
    }

}
