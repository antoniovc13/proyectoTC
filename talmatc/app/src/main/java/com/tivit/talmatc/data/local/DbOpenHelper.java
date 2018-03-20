package com.tivit.talmatc.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tivit.talmatc.data.local.constant.OrderEnum;

/**
 * Created by Alexzander Guillermo on 04/09/2017.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static DbOpenHelper sInstance;

    private static final int    DB_VERSION  = 1;
    private static final String DB_NAME     = "talmatc.db";

    private DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DbOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        //db.execSQL(DbContract.TB_ARCHIVO.CREATE);
        db.execSQL(DbContract.TB_PARAMETER.CREATE);
        //db.execSQL(DbContract.TB_RECONNECTION_CUT.CREATE);
        db.execSQL(DbContract.TB_PERSON.CREATE);
        //db.execSQL(DbContract.TB_ORDER.CREATE);
        db.execSQL(DbContract.TB_FLIGHT.CREATE);
        //insertParemeters(db);
        //insertOrders(db);
        insertFlights(db);
    }


    private void insertParemeters(SQLiteDatabase db) {
        String cab = "insert into parameters(parameterscode, parametersdescription, parametersentidad) ";
        String sql = cab + " values('CS','CON SUMINISTRO','SITUACION')";
        db.execSQL(sql);
        sql = cab + " values('RF','RECIBO PAGADO','SITUACION')";
        db.execSQL(sql);
        sql = cab + " values('SE','SUMINISTRO ENREJADO','SITUACION')";
        db.execSQL(sql);
        sql = cab + " values('SI','SUMINISTRO INTERIOR','SITUACION')";
        db.execSQL(sql);

        sql = cab + " values('BT','BAJO TERMICO','ACCION')";
        db.execSQL(sql);
        sql = cab + " values('NR','NO REALIZADO','ACCION')";
        db.execSQL(sql);
        sql = cab + " values('RE','REALIZADO','ACCION')";
        db.execSQL(sql);
    }

    private void insertOrders(SQLiteDatabase db) {

        String cab = "insert into "+DbContract.TB_ORDER.TABLE_NAME
                +"("
                +DbContract.TB_ORDER.KEY_CODE                                + ","
                +DbContract.TB_ORDER.KEY_ASSIGNATION_DATE                    + ","
                +DbContract.TB_ORDER.KEY_CREATION_DATE                       + ","
                +DbContract.TB_ORDER.KEY_CUSTOMER_ADDRESS_DESCRIPTION        +","
                +DbContract.TB_ORDER.KEY_SUBTYPE_DESCRIPTION                 + ","
                +DbContract.TB_ORDER.KEY_CUSTOMER_ADDRESS_DISTRICT_DESCRIPTION        + ","
                +DbContract.TB_ORDER.KEY_CUSTOMER_ADDRESS_LATITUDE            + ","
                +DbContract.TB_ORDER.KEY_CUSTOMER_ADDRESS_LONGITUDE           + ","
                +DbContract.TB_ORDER.KEY_CUSTOMER_CODE                          +","
                +DbContract.TB_ORDER.KEY_CUSTOMER_FULLNAME                      +","
                +DbContract.TB_ORDER.KEY_USERNAME                           +","
                +DbContract.TB_ORDER.KEY_STATUS_CODE                        +","
                +DbContract.TB_ORDER.KEY_STATUS_DESCRIPTION                 +")";
        String sql = cab + " values('ORD000152'"
                +",datetime()+1518940000"//+","+(new Date()).getTime()
                +",datetime()+1518940000"//+","+(new Date()).getTime()
                +","+"'JR LAS LIRAS 154'"
                +","+"'CORTE'"
                +","+"'BREÑA'"
                +","+"-12.06622"
                +","+"-77.04837"
                +","+"'243351'"
                +","+"'JOSE ESCALANTE SOSA'"
                +","+"'pe43126065'"
                +",'"+ OrderEnum.STATE_CARGADO.getValue()
                +"','CARGADO'"
                +")";
        db.execSQL(sql);

        sql = cab + " values('ORD000153'"
                +",datetime()+1518930000"//+","+(new Date()).getTime()
                +",datetime()+1518930000"//+","+(new Date()).getTime()
                +","+"'JIRON CENTENARIO 123'"
                +","+"'CORTE'"
                +","+"'BREÑA'"
                +","+"-12.06505"
                +","+"-77.05039"
                +","+"'4447213'"
                +","+"'ROXANA QUISPE ARIAS'"
                +","+"'pe43126065'"
                +",'"+ OrderEnum.STATE_CARGADO.getValue()
                +"','CARGADO'"
                +")";
        db.execSQL(sql);

        sql = cab + " values('ORD000154'"
                +",datetime()+1518940000"//+","+(new Date()).getTime()
                +",datetime()+1518940000"//+","+(new Date()).getTime()
                +","+"'CALLE LAS GARDENIAS 401'"
                +","+"'RECONEXION'"
                +","+"'CERCADO DE LIMA'"
                +","+"-12.065110"
                +","+"-77.045960"
                +","+"'4447213'"
                +","+"'JUAN PEREZ VARGAS'"
                +","+"'pe43126065'"
                +","+"'"+OrderEnum.STATE_CARGADO.getValue()
                +"','CARGADO'"
                +")";
        db.execSQL(sql);

        sql = cab + " values('ORD000155'"
                +",datetime()+1518950000"//+","+(new Date()).getTime()
                +",datetime()+1518950000"//+","+(new Date()).getTime()
                +","+"'CALLE LAS GARDENIAS 402'"
                +","+"'RECONEXION'"
                +","+"'CERCADO DE LIMA'"
                +","+"-12.065110"
                +","+"-77.045960"
                +","+"'4447213'"
                +","+"'JOSE RODRIGUEZ SOLORSANO'"
                +","+"'pe43126065'"
                +",'"+ OrderEnum.STATE_CARGADO.getValue()
                +"','CARGADO'"
                +")";
        db.execSQL(sql);
    }

    private void insertFlights(SQLiteDatabase db) {

        String cab = "insert into "+DbContract.TB_FLIGHT.TABLE_NAME
                +"("
                +DbContract.TB_FLIGHT.KEY_CODE                                + ","
                +DbContract.TB_FLIGHT.KEY_DESCRIPTION                         + ","
                +DbContract.TB_FLIGHT.KEY_PEA                                 + ","
                +DbContract.TB_FLIGHT.KEY_ETA                                 + ","
                +DbContract.TB_FLIGHT.KEY_ETD                                 + ","
                +DbContract.TB_FLIGHT.KEY_COUNT_ELEMENT                       + ","
                +DbContract.TB_FLIGHT.KEY_FLAG_ASSOCIATE                      + ")";
        String sql = cab + " values('LA 2071'"
                +",'LA 2071'"
                +",8"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 2072'"
                +",'LA 2072'"
                +",12"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 2073'"
                +",'LA 2073'"
                +",10"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 2081'"
                +",'LA 2081'"
                +",8"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 2082'"
                +",'LA 2082'"
                +",9"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 2083'"
                +",'LA 2083'"
                +",10"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 3011'"
                +",'LA 3011'"
                +",8"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 3012'"
                +",'LA 3012'"
                +",9"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);

        sql = cab + " values('LA 3013'"
                +",'LA 3013'"
                +",10"
                +","+"'08:00'"
                +","+"'08:45'"
                +","+"null"
                +","+"0"
                +")";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        //db.execSQL(DbContract.dropTable(DbContract.TB_ARCHIVO.TABLE_NAME));
        db.execSQL(DbContract.dropTable(DbContract.TB_PARAMETER.TABLE_NAME));
        //db.execSQL(DbContract.dropTable(DbContract.TB_RECONNECTION_CUT.TABLE_NAME));
        db.execSQL(DbContract.dropTable(DbContract.TB_PERSON.TABLE_NAME));
        //db.execSQL(DbContract.dropTable(DbContract.TB_ORDER.TABLE_NAME));
        db.execSQL(DbContract.dropTable(DbContract.TB_FLIGHT.TABLE_NAME));
        // create new tables
        onCreate(db);
    }

}
