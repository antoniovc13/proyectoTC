package com.tivit.talmatc.data.local;

import android.content.Context;

//import com.tivit.talmatc.data.local.service.OrderServiceLocal;
//import com.tivit.talmatc.data.local.service.OrderServiceLocalImpl;
import com.tivit.talmatc.data.local.service.FlightServiceLocal;
import com.tivit.talmatc.data.local.service.FlightServiceLocalImpl;
import com.tivit.talmatc.data.local.service.UserServiceLocal;
import com.tivit.talmatc.data.local.service.UserServiceLocalImpl;

/**
 * Created by Alexzander Guillermo on 01/09/2017.
 */

public class AppLocalDataStore implements AppLocalData {

    private DbOpenHelper dbOpenHelper;

    //private OrderServiceLocal orderServiceLocal;
    private UserServiceLocal userServiceLocal;
    private FlightServiceLocal flightServiceLocal;

    public AppLocalDataStore(Context context) {
        dbOpenHelper = DbOpenHelper.getInstance(context);
        //this.orderServiceLocal = new OrderServiceLocalImpl(dbOpenHelper);
        this.userServiceLocal = new UserServiceLocalImpl(dbOpenHelper);
        this.flightServiceLocal = new FlightServiceLocalImpl(dbOpenHelper);
    }

    /*
    @Override
    public OrderServiceLocal getOrderServiceLocal() {
        return orderServiceLocal;
    }
    */

    @Override
    public UserServiceLocal getUserServiceLocal() {
        return userServiceLocal;
    }

    @Override
    public FlightServiceLocal getFlightServiceLocal() {
        return flightServiceLocal;
    }

}
