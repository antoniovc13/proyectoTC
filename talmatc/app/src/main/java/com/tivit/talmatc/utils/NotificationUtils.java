package com.tivit.talmatc.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;

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

}
