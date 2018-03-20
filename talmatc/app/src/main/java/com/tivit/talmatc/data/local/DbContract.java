package com.tivit.talmatc.data.local;

/**
 * Created by Alexzander Guillermo on 08/09/2017.
 */

public class DbContract {

    private DbContract() {
    }

    // ============================================================================================
    // COMMONS
    // ============================================================================================

    private static final String COMMA_SPACE     = ", ";
    private static final String CREATE_TABLE    = "CREATE TABLE IF NOT EXISTS ";
    private static final String DROP_TABLE      = "DROP TABLE IF EXISTS ";
    private static final String TYPE_TEXT       = " TEXT ";
    private static final String TYPE_DATE       = " DATETIME ";
    private static final String TYPE_REAL       = " REAL ";
    private static final String TYPE_INT        = " INTEGER ";

    public static final String SELECT_BY_       = "SELECT * FROM %s WHERE %s = %s";
    public static final String SELECT_          = "SELECT * FROM %s";

    private static final String ID = "_id";
    private static final String CODE = "code";
    private static final String CREATION_DATE = "creation_date";
    private static final String ENABLED = "enabled";

    public static String dropTable(String tableName) {
        return DROP_TABLE + tableName;
    }

    // ============================================================================================
    // TABLES
    // ============================================================================================

    public static final class TB_ORDER {
        public static final String TABLE_NAME = "orders";

        public static final String KEY_ID                                    = ID;
        public static final String KEY_CODE                                  = CODE;
        public static final String KEY_ASSIGNATION_DATE                      = "assignationDate";
        public static final String KEY_CREATION_DATE                         = "creationDate";
        public static final String KEY_USERNAME                              = "username";
        public static final String KEY_SUBTYPE_DESCRIPTION                   = "subTypeDescription";
        public static final String KEY_STATUS_CODE                           = "statusCode";
        public static final String KEY_STATUS_DESCRIPTION                    = "statusDescription";
        public static final String KEY_CUSTOMER_CODE                         = "customerCode";
        public static final String KEY_CUSTOMER_DOCUMENT_NUMBER              = "customerDocumentNumber";
        public static final String KEY_CUSTOMER_PHONE                        = "customerPhone";
        public static final String KEY_CUSTOMER_MOBILE_PHONE                 = "customerMobilePhone";
        public static final String KEY_CUSTOMER_FULLNAME                     = "customerFullName";
        public static final String KEY_CUSTOMER_DOCUMENT_TYPE_DESCRIPTION    = "customerDocumentTypeDescription";
        public static final String KEY_CUSTOMER_ADDRESS_DESCRIPTION          = "customerAddressDescription";
        public static final String KEY_CUSTOMER_ADDRESS_REFERENCIA           = "customerAddressReferencia";
        public static final String KEY_CUSTOMER_ADDRESS_LATITUDE              = "customerAddressLatitude";
        public static final String KEY_CUSTOMER_ADDRESS_LONGITUDE             = "customerAddressLongitude";
        public static final String KEY_CUSTOMER_ADDRESS_DISTRICT_DESCRIPTION  = "customerAddressDistrictDescription";
        public static final String KEY_CUSTOMER_ADDRESS_PROVINCE_DESCRIPTION  = "customerAddressProvinceDescription";
        public static final String KEY_RECONNECTION_CUT_ID_FK                 = "reconnection_cut_id";

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID                                  + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_CODE                                + TYPE_TEXT + COMMA_SPACE +
                KEY_ASSIGNATION_DATE                    + TYPE_INT + COMMA_SPACE +
                KEY_CREATION_DATE                       + TYPE_INT + COMMA_SPACE +
                KEY_USERNAME                            + TYPE_TEXT + COMMA_SPACE +
                KEY_SUBTYPE_DESCRIPTION                 + TYPE_TEXT + COMMA_SPACE +
                KEY_STATUS_CODE                         + TYPE_TEXT + COMMA_SPACE +
                KEY_STATUS_DESCRIPTION                  + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_CODE                       + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_DOCUMENT_NUMBER            + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_PHONE                      + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_MOBILE_PHONE               + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_FULLNAME                   + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_DOCUMENT_TYPE_DESCRIPTION  + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_DESCRIPTION        + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_REFERENCIA         + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_LATITUDE            + TYPE_REAL + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_LONGITUDE           + TYPE_REAL + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_DISTRICT_DESCRIPTION + TYPE_TEXT + COMMA_SPACE +
                KEY_CUSTOMER_ADDRESS_PROVINCE_DESCRIPTION + TYPE_TEXT + COMMA_SPACE +
                KEY_RECONNECTION_CUT_ID_FK                + TYPE_INT + "REFERENCES " + TB_RECONNECTION_CUT.TABLE_NAME + " ON DELETE CASCADE "+
                ")";
    }

    // ============================================================================================

    public static final class TB_RECONNECTION_CUT {
        public static final String TABLE_NAME = "reconnection_cut";

        public static final String KEY_ID                       = ID;
        public static final String KEY_ORDER_CODE               = "orderCode";
        public static final String KEY_SITUATION_CODE           = "situationCode";
        public static final String KEY_SITUATION_DESCRIPTION    = "situationDescription";
        public static final String KEY_ACTION_CODE              = "actionCode";
        public static final String KEY_ACTION_DESCRIPTION       = "actionDescription";
        public static final String KEY_MEASUREMENT              = "measurement";
        public static final String KEY_OBSERVATION              = "observation";
        public static final String KEY_PICTURE                  = "picture";
        public static final String KEY_CREATION_DATE            = CREATION_DATE;

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID                      + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_ORDER_CODE              + TYPE_TEXT + COMMA_SPACE +
                KEY_SITUATION_CODE          + TYPE_TEXT + COMMA_SPACE +
                KEY_SITUATION_DESCRIPTION   + TYPE_TEXT + COMMA_SPACE +
                KEY_ACTION_CODE             + TYPE_TEXT + COMMA_SPACE +
                KEY_ACTION_DESCRIPTION      + TYPE_TEXT + COMMA_SPACE +
                KEY_MEASUREMENT             + TYPE_REAL + COMMA_SPACE +
                KEY_OBSERVATION             + TYPE_TEXT + COMMA_SPACE +
                KEY_PICTURE                 + TYPE_TEXT + COMMA_SPACE +
                KEY_CREATION_DATE           + TYPE_INT +
                ")";
    }

    // ============================================================================================

    public static final class TB_PARAMETER {
        public static final String TABLE_NAME = "parameters";

        public static final String KEY_ID               = TABLE_NAME + ID;
        public static final String KEY_CODE             = TABLE_NAME + CODE;
        public static final String KEY_DESCRIPTION      = TABLE_NAME + "description";
        public static final String KEY_ENTIDAD          = TABLE_NAME + "entidad";

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID              + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_CODE            + TYPE_TEXT + COMMA_SPACE +
                KEY_DESCRIPTION     + TYPE_TEXT + COMMA_SPACE +
                KEY_ENTIDAD         + TYPE_TEXT +
                ")";
    }

    // ============================================================================================

    public static final class TB_PERSON {
        public static final String TABLE_NAME = "persons";

        public static final String KEY_ID               = ID;
        public static final String KEY_NUMERO_DOCUM     = "numeroDocum";
        public static final String KEY_NOMBRE           = "nombre";
        public static final String KEY_APELLIDO_PAT     = "apellidoPat";
        public static final String KEY_APELLIDO_MAT     = "apellidoMat";
        public static final String KEY_FULLNAME         = "fullname";
        public static final String KEY_PHONE            = "phone";
        public static final String KEY_MOBILE_PHONE     = "mobile_phone";
        public static final String KEY_EMAIL            = "email";
        public static final String KEY_CREATION_DATE    = CREATION_DATE;
        public static final String KEY_ENABLED          = ENABLED;

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID              + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_NUMERO_DOCUM    + TYPE_TEXT + COMMA_SPACE +
                KEY_NOMBRE          + TYPE_TEXT + COMMA_SPACE +
                KEY_APELLIDO_PAT    + TYPE_TEXT + COMMA_SPACE +
                KEY_APELLIDO_MAT    + TYPE_TEXT + COMMA_SPACE +
                KEY_FULLNAME        + TYPE_TEXT + COMMA_SPACE +
                KEY_PHONE           + TYPE_TEXT + COMMA_SPACE +
                KEY_MOBILE_PHONE    + TYPE_TEXT + COMMA_SPACE +
                KEY_EMAIL           + TYPE_TEXT +
                ")";
    }

    // ============================================================================================

    public static final class TB_ARCHIVO {
        public static final String TABLE_NAME = "order_adjunto";

        public static final String KEY_ID                       = ID;
        public static final String KEY_PATH                     = "path";
        public static final String KEY_NAME                     = "name";
        public static final String KEY_SIZE                     = "size";
        public static final String KEY_PARENT_ID                = "parent_id";

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID              + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_PATH            + TYPE_TEXT + COMMA_SPACE +
                KEY_NAME            + TYPE_TEXT + COMMA_SPACE +
                KEY_SIZE            + TYPE_TEXT + COMMA_SPACE +
                KEY_PARENT_ID       + TYPE_INT +
                ")";
    }

    // ============================================================================================

    // ============================================================================================

    public static final class TB_FLIGHT {
        public static final String TABLE_NAME = "flight";

        public static final String KEY_ID               = ID;
        public static final String KEY_CODE             = CODE;
        public static final String KEY_DESCRIPTION      = "description";
        public static final String KEY_PEA              = "pea";
        public static final String KEY_ETA              = "eta";
        public static final String KEY_ETD              = "etd";
        public static final String KEY_COUNT_ELEMENT    = "count_element";
        public static final String KEY_FLAG_ASSOCIATE    = "flag_associate";

        public static final String CREATE = CREATE_TABLE + TABLE_NAME  + " (" +
                KEY_ID              + TYPE_INT + "PRIMARY KEY AUTOINCREMENT" + COMMA_SPACE +
                KEY_CODE            + TYPE_TEXT + COMMA_SPACE +
                KEY_DESCRIPTION     + TYPE_TEXT + COMMA_SPACE +
                KEY_PEA             + TYPE_TEXT + COMMA_SPACE +
                KEY_ETA             + TYPE_TEXT + COMMA_SPACE +
                KEY_ETD             + TYPE_TEXT + COMMA_SPACE +
                KEY_COUNT_ELEMENT   + TYPE_TEXT + COMMA_SPACE +
                KEY_FLAG_ASSOCIATE  + TYPE_INT +            //1:associate 0:
                ")";
    }
}
