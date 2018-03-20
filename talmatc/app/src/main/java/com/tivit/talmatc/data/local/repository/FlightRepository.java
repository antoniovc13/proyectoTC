package com.tivit.talmatc.data.local.repository;

import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 07/09/2017.
 */

public interface FlightRepository extends GenericRepository<Flight> {

    List<Flight> findAllByFlagAssociate(int flag) ;
    Flight findByCode(String code);
}
