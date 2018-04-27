package com.tivit.talmatc.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.data.AppDataManager;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.utils.Configuration;
import com.tivit.talmatc.utils.ErrorUtils;
import com.tivit.talmatc.utils.NotificationUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class DownloadService extends IntentService {

    private final AppDataManager appDataManager;

    public static final int RESULT_OK = 101;
    public static final int RESULT_EMPTY = 102;
    public static final int RESULT_EMPTY_MESSAGE = 103;
    public static final int RESULT_ERROR = 104;
    public static final int RESULT_INVALID_TOKEN = 105;

    public static final String PARAM_SOURCE = "DownloadService.param_source";

    private int result;

    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.tivit.android.service.receiver";

    // DATA
    private NotificationUtils notificationUtils;
    private int totalOrders;

    public DownloadService() {
        super("DownloadService");
        appDataManager = AppDataManager.getInstance(TivitApplication.getAppContext());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String title = TivitApplication.getAppContext().getResources().getString(R.string.notification_service_title_download);
        notificationUtils = new NotificationUtils(this);

        Bundle extras = intent.getExtras();
        assert extras != null;
        String source = extras.getString(PARAM_SOURCE);

//        String token = "Bearer " + appDataManager.getAppPreferencesData().getAuthorization().getAccessToken();

        appDataManager.getAppLocalData().getFlightServiceLocal().findAllFlightAssociate()
                .map(flightResponse -> flightResponse.getContent())
                /*.concatMap(flightList -> {
                    totalOrders = flightList.size();
                    return appDataManager.getAppLocalData().getOrderServiceLocal().saveOrderListFromRemote(orderList);
                })*/
                .subscribeWith(new DisposableObserver<List<Flight>>() {
                            @Override
                            public void onNext(@NonNull List<Flight> flightList) {
                                Timber.d("ON_NEXT");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Timber.d("ON_ERROR");
                                List<String> messages = ErrorUtils.onFailure(e);
                                Timber.d("paso1");

                                if(ErrorUtils.isAuthorizedException(e)) {
                                    result = RESULT_INVALID_TOKEN;
                                    Timber.d("paso2");
                                } else if (ErrorUtils.getCodeHttp(e) == 404) {
                                    Timber.d("paso3");
                                    if(TextUtils.equals(source, Configuration.SOURCE_BUTTOM)) {
                                        result = RESULT_EMPTY_MESSAGE;
                                        Timber.d("paso4");
                                    } else {
                                        result = RESULT_EMPTY;
                                        Timber.d("paso5");
                                    }
                                }
                                Timber.d("paso6");
                                publishResults(result);
                            }

                            @Override
                            public void onComplete() {
                                Timber.d("ON_COMPLETE");

                                String message = String.format("Se entonraron %d ordenes asignadas", totalOrders);
                                notificationUtils.showNotificationService(title, message, Notification.PRIORITY_HIGH);
                                result = RESULT_OK;

                                publishResults(result);
                            }
                        });


    }

    private void publishResults(int result) {
        Timber.d("publishResults -> result "+result);
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }

}
