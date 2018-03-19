package com.tivit.talmatc.data.remote;

/**
 * Created by Alexzander Guillermo on 28/08/2017.
 */

public class ApiEndPoint {

//    public static final String SERVER_URL = "192.168.173.1";
//    public static final String SERVER_URL = "10.0.2.2";
//    public static final String SERVER_PORT = "8080";
    public static final String WS_PATH = "/api/";
//    public static final String WS_URL = "http://" + SERVER_URL + ":" + SERVER_PORT + WS_PATH;
//    public static final String WS_URL = "http://2dc41bc3.ngrok.io/Orders" + WS_PATH;
    //public static final String WS_URL = "http://tivitswf.ddns.net:8085/Orders" + WS_PATH;
    public static final String WS_URL = "http://ec2-34-231-42-240.compute-1.amazonaws.com:8085/Orders" + WS_PATH;

    // REST
    public static final String AUTHORIZATION = "X-Authorization";
    public static final String BEARER = "Bearer ";

    public static final String WS_LOGIN = "auth/login";
    public static final String WS_REFRESH_TOKEN = "auth/token";
    public static final String WS_FCM = "version/fcm";
    public static final String WS_ORDER_GENERATED = "orders/assigned";
    public static final String WS_ORDER_LIGHT = "orders/light";
    public static final String WS_RECONNECTION_SAVE = "orders/breakReconnection";
    public static final String WS_RECONNECTION_SAVE_MULTIPART = "orders/breakReconnection2";

    public static final String WS_VERSION = "version";

    public static final String WS_CATALOG_SIITUATION = "situations";
    public static final String WS_CATALOG_ACTION = "actions";
//    public static final String WS_TICKET_PDF_URL = WS_URL + WS_TICKET + "/pdf/";
//    public static final String WS_TICKET_FILE_URL = WS_URL + WS_TICKET + "/file/";

}
