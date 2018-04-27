package com.tivit.talmatc.services;

import android.app.Notification;
import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tivit.talmatc.utils.NotificationUtils;
import com.tivit.talmatc.utils.Util;
import com.tivit.talmatc.utils.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

public class FcmMessagingService extends FirebaseMessagingService {

    private final String TAG = Util.getClassName(this.getClass());

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        //Timber.d("onMessageReceived ***");
        if (remoteMessage == null) return;

        String title = "";
        String message = "";

        if(remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            message = remoteMessage.getNotification().getBody();
        }
        //Timber.d("onMessageReceived ***:"+message);

        JSONObject data = null;
        //Timber.d("paso1 ***");

        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        /*
        vibrator.vibrate(1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */


        if (remoteMessage.getData()!=null && remoteMessage.getData().size() > 0) {
            try {
                /*
                vibrator.vibrate(1000);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
/*
                vibrator.vibrate(1000);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
*/
                data = json.getJSONObject("extras");

                /*
                vibrator.vibrate(1000);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
                notificationUtils = new NotificationUtils(this);
                notificationUtils.showNotificationMessage(title, message, data);

            } catch (JSONException e) {
                Timber.d(TAG, "Json Exception: " + e.getMessage());
            } catch (Exception e) {
                Timber.d(TAG, "Exception: " + e.getMessage());
            }
        }



        /////////
        /*
        try {
            vibrator.vibrate(1000);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notificationUtils = new NotificationUtils(this);
            vibrator.vibrate(1000);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notificationUtils.showNotificationMessage(title, message, data);

        } catch (JSONException e) {
            Timber.d(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Timber.d(TAG, "Exception: " + e.getMessage());
        }
*/

    }
}
