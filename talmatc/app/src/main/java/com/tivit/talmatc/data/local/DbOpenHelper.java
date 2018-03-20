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
        db.execSQL(DbContract.TB_USER.CREATE);
        //db.execSQL(DbContract.TB_ROLE.CREATE);
        //db.execSQL(DbContract.TB_USER_ROLE.CREATE);
        insertParemeters(db);
        insertFlights(db);

        insertUsers(db);
        //insertRoles(db);
        //insertUserRoles(db);
    }

    private void insertUsers(SQLiteDatabase db) {

        String cab = "insert into user(username, password, fullname, role, unit) ";
        String sql = "";

        sql = cab + " values('usuario1','admin','JUAN PEREZ VACA','ROL1','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('usuario2','admin','JOSE RODRIGUEZ SOLORZANO','ROL1','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('usuario3','admin','ALAN HUAMANI SALAZ','ROL2','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('usuario4','admin','LUIS LEIVA PEREZ','ROL3','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('admin','admin','ADMINISTRADOR DEL SISTEMA','ROL1','TRACTOR')";
        db.execSQL(sql);
    }
/*
    private void insertRoles(SQLiteDatabase db) {
        String cab = "insert into role(code, description) ";
        String sql = "";

        sql = cab + " values('ROL1','ROL1')";
        db.execSQL(sql);
        sql = cab + " values('ROL2','ROL2')";
        db.execSQL(sql);
        sql = cab + " values('ROL3','ROL3')";
        db.execSQL(sql);
        sql = cab + " values('ROL4','ROL4')";
        db.execSQL(sql);
    }

    private void insertUserRoles(SQLiteDatabase db) {
        String cab = "insert into user_role(id_user, id_role) ";
        String sql = "";

        sql = cab + " values(1,1)";
        db.execSQL(sql);
        sql = cab + " values(2,2)";
        db.execSQL(sql);
        sql = cab + " values(3,3)";
        db.execSQL(sql);
        sql = cab + " values(4,4)";
        db.execSQL(sql);
        sql = cab + " values(5,1)";
        db.execSQL(sql);
    }
*/

    private void insertParemeters(SQLiteDatabase db) {
        String cab = "insert into parameters(parameterscode, parametersdescription, parametersentidad) ";
        String sql = "";
        sql = cab + " values('10','10','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('11','11','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('12','12','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('13','13','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('14','14','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('15','15','TRACTOR')";
        db.execSQL(sql);
        sql = cab + " values('16','16','TRACTOR')";
        db.execSQL(sql);


        sql = cab + " values('ABC-123','ABC-123','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-234','ABC-234','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-345','ABC-345','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-456','ABC-456','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-567','ABC-567','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-678','ABC-678','CAMION')";
        db.execSQL(sql);
        sql = cab + " values('ABC-789','ABC-789','CAMION')";
        db.execSQL(sql);


        sql = cab + " values('PEA','PEA','POINT_INIT')";
        db.execSQL(sql);
        sql = cab + " values('ALMACEN','ALMACEN','POINT_INIT')";
        db.execSQL(sql);
        sql = cab + " values('ZT','ZONA DE TRANSFERENCIA','POINT_INIT')";
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
