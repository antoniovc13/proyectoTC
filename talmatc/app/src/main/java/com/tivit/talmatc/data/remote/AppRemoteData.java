package com.tivit.talmatc.data.remote;

//import com.tivit.talmatc.data.remote.service.OrderService;
import com.tivit.talmatc.data.remote.service.UserService;

import retrofit2.Retrofit;

/**
 * Created by Alexzander Guillermo on 05/09/2017.
 */

public interface AppRemoteData {

    Retrofit getRetrofit();

    //OrderService getOrderService();

    UserService getUserService();

}
