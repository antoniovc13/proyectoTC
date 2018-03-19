package com.tivit.talmatc.utils;

import android.os.Environment;

public class Configuration {

    public static final String CLOUD_VISION_API_KEY = "AIzaSyBkWP-XKD0QAjt19toa1UVtatCSyTfCDvQ";
    public static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    public static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";

    public static final String PATH_ABSOLUTE_PICTURES = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/TivitTask";
    public static final String PATH_ABSOLUTE_ADJUNTOS = Environment.getExternalStorageDirectory() + "/TivitTask/adjuntos";
    public static final String PATH_RELATIVE_ADJUNTOS = "/TivitTask/adjuntos";
    public static final String PATH_ABSOLUTE_ARCHIVOS = Environment.getExternalStorageDirectory() + "//archivos";
    public static final String PATH_RELATIVE_ARCHIVOS = "/TivitTask/archivos";
    public static final String PATH_ABSOLUTE_APK      = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/TalmaTC/apk/";

    // DATABASE
    public static final String DATABASE = "TIVIT_TASK_DB";

    // CONFIG TOAST SNACK TIME
    public static final int PARAM_SNACK_MORE_DURATION_MAX = 1350;
    public static final int PARAM_SNACK_MORE_DURATION_MIN = 1000;
    public static final int PARAM_TOAST_MORE_DURATION_MAX = 1350;
    public static final int PARAM_TOAST_MORE_DURATION_MIN = 1000;

    // NOTIFICATION
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;

    public static final String SHARED_PREF = "ah_firebase";

    // FILE SOURCE
    public static final String SOURCE_REMOTE = "source_remote";
    public static final String SOURCE_LOCAL = "source_local";
    public static final String SOURCE_RESUME = "source_resume";
    public static final String SOURCE_BUTTOM = "source_buttom";

    public static final String CODE_FLIGHT = "CODE_FLIGHT";
    public static final String CODE_ARRIVE = "ARRIVE";
    public static final String CODE_DEPARTURE = "DEPARTURE";

}
