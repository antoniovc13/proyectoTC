package com.tivit.talmatc.data.remote;

//import com.tivit.talmatc.data.remote.service.OrderService;
import com.tivit.talmatc.data.remote.service.UserService;
import com.tivit.talmatc.utils.TokenAuthenticator;
import com.tivit.talmatc.utils.TokenInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexzander Guillermo on 05/09/2017.
 */

public class AppRemoteDataStore implements AppRemoteData {

    private static Retrofit retrofit;

    public AppRemoteDataStore() {
        getRetrofit();
    }

    @Override
    public Retrofit getRetrofit() {
        if(retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(loggingInterceptor());
            builder.addInterceptor(new TokenInterceptor());
            builder.authenticator(new TokenAuthenticator());

            OkHttpClient httpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiEndPoint.WS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

    private <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    //** START API SERVICES **//
/*
    @Override
    public OrderService getOrderService() {
        return createService(OrderService.class);
    }
*/
    @Override
    public UserService getUserService() {
        return createService(UserService.class);
    }


}
