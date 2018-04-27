package com.tivit.talmatc.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import timber.log.Timber;

public class FcmInstanceService extends FirebaseInstanceIdService {

    /*
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken  = FirebaseInstanceId.getInstance().getToken();
        Timber.d("FCM:"+ refreshedToken );

        // Saving reg id to shared preferences
        //PreferencesManager.saveFcmRegId(getApplicationContext(), refreshedToken);
    }
*/
    public static String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public void onTokenRefresh() {
        //Enviar token al servidor para almacenarlo
        Timber.d("PRUEBA***** "+ "Token: " + getToken());
        Log.d("PRUEBA", "Token: " + getToken());
    }

}
