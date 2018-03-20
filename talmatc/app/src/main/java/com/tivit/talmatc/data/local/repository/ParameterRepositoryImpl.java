package com.tivit.talmatc.data.local.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.tivit.talmatc.data.local.DbContract;
import com.tivit.talmatc.data.local.DbOpenHelper;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 07/09/2017.
 */

public class ParameterRepositoryImpl extends GenericRepositoryImpl<Parameter> implements ParameterRepository {

    public ParameterRepositoryImpl(DbOpenHelper dbOpenHelper) {
        super(dbOpenHelper);
        setTableName(DbContract.TB_PARAMETER.TABLE_NAME);
        setKeyId(DbContract.TB_PARAMETER.KEY_ID);
    }

    static class ParameterRepositoryMapper {
        public ContentValues toContentValues(Parameter object) {
            ContentValues cv = new ContentValues();

            cv.put(DbContract.TB_PARAMETER.KEY_CODE, object.getCode());
            cv.put(DbContract.TB_PARAMETER.KEY_DESCRIPTION, object.getDescription());
            cv.put(DbContract.TB_PARAMETER.KEY_ENTIDAD, object.getEntidad());

            return cv;
        }

        public Parameter toObject(Cursor c) {
            Parameter object = new Parameter();

            object.setId(c.getLong(c.getColumnIndexOrThrow(DbContract.TB_PARAMETER.KEY_ID)));
            object.setCode(c.getString(c.getColumnIndexOrThrow(DbContract.TB_PARAMETER.KEY_CODE)));
            object.setDescription((c.getString(c.getColumnIndexOrThrow(DbContract.TB_PARAMETER.KEY_DESCRIPTION))));
            object.setEntidad(c.getString(c.getColumnIndexOrThrow(DbContract.TB_PARAMETER.KEY_ENTIDAD)));

            return object;
        }
    }

    private ParameterRepositoryMapper getMapper() {
        return new ParameterRepositoryMapper();
    }

    @Override
    protected ContentValues itemToContentValues(Parameter object) {
        return getMapper().toContentValues(object);
    }

    @Override
    protected Parameter cursorToItem(Cursor c) {
        return getMapper().toObject(c);
    }

    @Override
    public List<Parameter> findAllByEntity(String type) {
        String query =  "SELECT * FROM %s " +
                        "WHERE %s = '%s'";

        String selectQuery = String.format(query,
                DbContract.TB_PARAMETER.TABLE_NAME,
                DbContract.TB_PARAMETER.KEY_ENTIDAD, type);

        return findAllBy(selectQuery);
    }

    @Override
    public long countByEntidad(String[] whereArgs) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db, DbContract.TB_PARAMETER.TABLE_NAME, DbContract.TB_PARAMETER.KEY_ENTIDAD + "=?", whereArgs);
        return count;
    }
/*
    //TODO AVC agregado
    @Override
    public Authorization getAuthorizationLogin(Login login) {

        Authorization  authorization = new Authorization();
        authorization.setAccessToken("123456");
        authorization.setRefreshToken("123456");
        authorization.setUsername(login.getUsername());

        return authorization;
    }
    */
}
