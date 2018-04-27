package com.tivit.talmatc.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.feature.login.LoginActivity;
import com.tivit.talmatc.feature.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alexzander Guillermo on 20/09/2017.
 */

public class NotificationUtils {

    private Context mContext;

    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotificationService(final String title, final String message, final int priority) {
        if (TextUtils.isEmpty(message))
            return;

        showNotification(title, message, null, priority);
    }

    private void showNotification(String title, String message, PendingIntent resultPendingIntent, int priority) {
        final int icon = R.mipmap.ic_launcher_round;

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setPriority(priority)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_stat_logo_png)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setColor(ContextCompat.getColor(TivitApplication.getAppContext(), R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }


    public void showNotificationMessage( String title, String message, final JSONObject data) throws JSONException {
        /*
        if (TextUtils.isEmpty(message))
            return;
        */

        Intent resultIntent = null;
        resultIntent = new Intent(mContext, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if(data != null) {
            if (TextUtils.isEmpty(title))  title = data.getString("title");
            if (TextUtils.isEmpty(message)) message = data.getString("body");

            String code = data.getString("code");
            String activity = data.getString("activity");

            message += " "+code+"**"+activity;

            if (TextUtils.equals(activity, "ticketView")) {
                resultIntent = new Intent(mContext, MainActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //resultIntent.putExtra(TicketViewActivity.PARAM_TICKET_ÇODE, code);
            }
        }
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
//        stackBuilder.addNextIntent(resultIntent);

//        final PendingIntent resultPendingIntent = PendingIntent.getActivity( mContext, Configuracion.NOTIFICATION_ID, resultIntent, PendingIntent.FLAG_ONE_SHOT );
//        final PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        //**************

        Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        showNotification(title, message, resultPendingIntent);
    }


    private void showNotification(String title, String message, PendingIntent resultPendingIntent) {
        final int icon = R.mipmap.ic_launcher_round;

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_noti_1)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());
    }

}
