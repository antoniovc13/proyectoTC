package com.tivit.talmatc.data.local.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.tivit.talmatc.data.local.DbContract;
import com.tivit.talmatc.data.local.DbOpenHelper;
import com.tivit.talmatc.data.local.model.User;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;

/**
 * Created by Alexzander Guillermo on 07/09/2017.
 */

public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(DbOpenHelper dbOpenHelper) {
        super(dbOpenHelper);
        setTableName(DbContract.TB_PARAMETER.TABLE_NAME);
        setKeyId(DbContract.TB_PARAMETER.KEY_ID);
    }

    @Override
    protected ContentValues itemToContentValues(User object) {
        return getMapper().toContentValues(object);
    }

    static class UserRepositoryMapper {
        public ContentValues toContentValues(User object) {
            ContentValues cv = new ContentValues();

            cv.put(DbContract.TB_USER.KEY_USERNAME, object.getUsername());
            cv.put(DbContract.TB_USER.KEY_PASSWORD, object.getPassword());
            cv.put(DbContract.TB_USER.KEY_FULL_NAME, object.getFullName());
            cv.put(DbContract.TB_USER.KEY_ROLE, object.getRole());
            cv.put(DbContract.TB_USER.KEY_UNIT, object.getUnidad());

            return cv;
        }

        public User toObject(Cursor c) {
            User object = new User();

            object.setId(c.getLong(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_ID)));
            object.setUsername(c.getString(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_USERNAME)));
            object.setPassword((c.getString(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_PASSWORD))));
            object.setFullName(c.getString(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_FULL_NAME)));
            object.setRole(c.getString(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_ROLE)));
            object.setUnidad(c.getString(c.getColumnIndexOrThrow(DbContract.TB_USER.KEY_UNIT)));

            return object;
        }
    }

    private UserRepositoryMapper getMapper() {
        return new UserRepositoryMapper();
    }

    @Override
    protected User cursorToItem(Cursor c) {
        return getMapper().toObject(c);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String query =  "SELECT * FROM %s " +
                        "WHERE %s = '%s' " +
                        "AND %s = '%s' " ;

        String selectQuery = String.format(query,
                DbContract.TB_USER.TABLE_NAME,
                DbContract.TB_USER.KEY_USERNAME, username,
                DbContract.TB_USER.KEY_PASSWORD, password);


        return findBy(selectQuery);
    }


    //TODO AVC agregado
    @Override
    public Authorization getAuthorizationLogin(Login login) {

        Authorization  authorization = null;

        String query =  "SELECT * FROM %s " +
                "WHERE %s = '%s' " +
                "AND %s = '%s' " ;

        String selectQuery = String.format(query,
                DbContract.TB_USER.TABLE_NAME,
                DbContract.TB_USER.KEY_USERNAME, login.getUsername(),
                DbContract.TB_USER.KEY_PASSWORD, login.getPassword());


        User user = findBy(selectQuery);
        if(user!= null){
            authorization = new Authorization();
            authorization.setAccessToken("123456");
            authorization.setRefreshToken("123456");
            authorization.setUsername(login.getUsername());
            authorization.setUser(user);
        }

        return authorization;
    }
}
