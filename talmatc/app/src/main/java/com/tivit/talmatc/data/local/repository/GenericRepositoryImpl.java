package com.tivit.talmatc.data.local.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tivit.talmatc.data.local.DbContract;
import com.tivit.talmatc.data.local.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexzander Guillermo on 10/09/2017.
 */

public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

    protected final DbOpenHelper dbOpenHelper;
    protected String TABLE_NAME;
    protected String KEY_ID;

    public GenericRepositoryImpl(DbOpenHelper dbOpenHelper) {
        this.dbOpenHelper = dbOpenHelper;
    }

    protected void setTableName(String tableName) {
        this.TABLE_NAME = tableName;
    }

    protected void setKeyId(String keyId) {
        this.KEY_ID = keyId;
    }

    protected abstract ContentValues itemToContentValues(T object);

    protected abstract T cursorToItem(Cursor c);

    @Override
    public void insertAll(List<T> list) throws SQLException {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        for (T item : list) {
            db.insertOrThrow(TABLE_NAME, null, itemToContentValues(item));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public Long insert(T item) throws SQLException {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        Long id = db.insertOrThrow(TABLE_NAME, null, itemToContentValues(item));
        db.setTransactionSuccessful();
        db.endTransaction();
        return id;
    }

    @Override
    public boolean update(T item, Long id) throws SQLException {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int res = db.update(TABLE_NAME, itemToContentValues(item), KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.setTransactionSuccessful();
        db.endTransaction();
        return res > 0;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        int res = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.setTransactionSuccessful();
        db.endTransaction();
        return res > 0;
    }

    @Override
    public T findById(Long id) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        String selectQuery = String.format(DbContract.SELECT_BY_, TABLE_NAME, KEY_ID, id);

        Cursor c = db.rawQuery(selectQuery, null);
        T object = null;

        if (c != null && c.moveToFirst()) {
            object = cursorToItem(c);
        }

        return object;
    }

    protected T findBy(String selectQuery) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        T object = null;

        if (c != null && c.moveToFirst()) {
            object = cursorToItem(c);
        }

        return object;
    }

    @Override
    public List<T> findAll() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        String selectQuery = String.format(DbContract.SELECT_, TABLE_NAME);

        Cursor c = db.rawQuery(selectQuery, null);
        List<T> list = null;

        if (c != null && c.moveToFirst()) {
            list = new ArrayList<>();

            do {
                T item = cursorToItem(c);
                list.add(item);

            } while (c.moveToNext());
        }

        return list;
    }

    protected List<T> findAllBy(String selectQuery) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);
        List<T> list = null;

        if (c != null && c.moveToFirst()) {
            list = new ArrayList<>();

            do {
                T item = cursorToItem(c);
                list.add(item);

            } while (c.moveToNext());
        }

        return list;
    }

    @Override
    public long count() {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
    }

}
