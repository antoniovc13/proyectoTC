package com.tivit.talmatc.data.local;

//import com.tivit.talmatc.data.local.service.OrderServiceLocal;
import com.tivit.talmatc.data.local.service.FlightServiceLocal;
import com.tivit.talmatc.data.local.service.UserServiceLocal;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public interface AppLocalData {

    //OrderServiceLocal getOrderServiceLocal();

    UserServiceLocal getUserServiceLocal();

    FlightServiceLocal getFlightServiceLocal();
}
