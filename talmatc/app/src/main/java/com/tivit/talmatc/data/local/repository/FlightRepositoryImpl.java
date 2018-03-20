package com.tivit.talmatc.data.local.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.tivit.talmatc.data.local.DbContract;
import com.tivit.talmatc.data.local.DbOpenHelper;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 07/09/2017.
 */

public class FlightRepositoryImpl extends GenericRepositoryImpl<Flight> implements FlightRepository {

    public FlightRepositoryImpl(DbOpenHelper dbOpenHelper) {
        super(dbOpenHelper);
        setTableName(DbContract.TB_FLIGHT.TABLE_NAME);
        setKeyId(DbContract.TB_FLIGHT.KEY_ID);
    }

    static class FlightRepositoryMapper {
        public ContentValues toContentValues(Flight object) {
            ContentValues cv = new ContentValues();

            cv.put(DbContract.TB_FLIGHT.KEY_CODE, object.getCode());
            cv.put(DbContract.TB_FLIGHT.KEY_DESCRIPTION, object.getDescripcion());
            cv.put(DbContract.TB_FLIGHT.KEY_ETA, object.getEta());
            cv.put(DbContract.TB_FLIGHT.KEY_ETD, object.getEtd());
            cv.put(DbContract.TB_FLIGHT.KEY_COUNT_ELEMENT, object.getCountElements());
            cv.put(DbContract.TB_FLIGHT.KEY_PEA, object.getPea());

            return cv;
        }

        public Flight toObject(Cursor c) {
            Flight object = new Flight();

            object.setId(c.getLong(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_ID)));
            object.setCode(c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_CODE)));
            object.setDescripcion((c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_DESCRIPTION))));
            object.setEtd((c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_ETD))));
            object.setEta((c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_ETA))));
            object.setCountElements((c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_COUNT_ELEMENT))));
            object.setPea((c.getString(c.getColumnIndexOrThrow(DbContract.TB_FLIGHT.KEY_PEA))));

            return object;
        }
    }

    private FlightRepositoryMapper getMapper() {
        return new FlightRepositoryMapper();
    }

    @Override
    protected ContentValues itemToContentValues(Flight object) {
        return getMapper().toContentValues(object);
    }

    @Override
    protected Flight cursorToItem(Cursor c) {
        return getMapper().toObject(c);
    }

    @Override
    public List<Flight> findAllByFlagAssociate(int flag) {
        String query =  "SELECT * FROM %s " +
                        "WHERE %s = %s";

        String selectQuery = String.format(query,
                DbContract.TB_FLIGHT.TABLE_NAME,
                DbContract.TB_FLIGHT.KEY_FLAG_ASSOCIATE, flag);
        Timber.d(selectQuery);
        //List<Flight> list = findAllBy(selectQuery);
        //return list==null?new ArrayList<>():list;
        return findAllBy(selectQuery);
    }

    @Override
    public Flight findByCode(String code) {
        String query =  "SELECT * FROM %s " +
                "WHERE %s = '%s'";

        String selectQuery = String.format(query,
                DbContract.TB_FLIGHT.TABLE_NAME,
                DbContract.TB_FLIGHT.KEY_CODE, code);

        Timber.d(selectQuery);
        return findBy(selectQuery);
    }

}
