package com.tivit.talmatc.data.local.service;

import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Alexzander Guillermo on 14/09/2017.
 */

public interface FlightServiceLocal {
    Observable<List<Flight>> findAllFlights();
    Observable<Flight> findFlightByCode(final String code);
}
