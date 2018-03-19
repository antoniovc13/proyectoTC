package com.tivit.talmatc.data.local;

import android.content.Context;

//import com.tivit.talmatc.data.local.service.OrderServiceLocal;
//import com.tivit.talmatc.data.local.service.OrderServiceLocalImpl;
import com.tivit.talmatc.data.local.service.FlightServiceLocal;
import com.tivit.talmatc.data.local.service.FlightServiceLocalImpl;
import com.tivit.talmatc.data.local.service.ParameterServiceLocal;
import com.tivit.talmatc.data.local.service.ParameterServiceLocalImpl;
import com.tivit.talmatc.data.local.service.UserServiceLocal;
import com.tivit.talmatc.data.local.service.UserServiceLocalImpl;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public class AppLocalDataStore implements AppLocalData {

    private DbOpenHelper dbOpenHelper;

    private ParameterServiceLocal parameterServiceLocal;
    private UserServiceLocal userServiceLocal;
    private FlightServiceLocal flightServiceLocal;

    public AppLocalDataStore(Context context) {
        dbOpenHelper = DbOpenHelper.getInstance(context);
        this.parameterServiceLocal = new ParameterServiceLocalImpl(dbOpenHelper);
        this.userServiceLocal = new UserServiceLocalImpl(dbOpenHelper);
        this.flightServiceLocal = new FlightServiceLocalImpl(dbOpenHelper);
    }


    @Override
    public ParameterServiceLocal getParameterServiceLocal() { return parameterServiceLocal; }

    @Override
    public UserServiceLocal getUserServiceLocal() {
        return userServiceLocal;
    }

    @Override
    public FlightServiceLocal getFlightServiceLocal() {
        return flightServiceLocal;
    }

}
