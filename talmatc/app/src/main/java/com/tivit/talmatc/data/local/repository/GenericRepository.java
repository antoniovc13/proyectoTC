package com.tivit.talmatc.data.local.repository;

import android.database.SQLException;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 10/09/2017.
 */

public interface GenericRepository<T>  {
    void insertAll(List<T> list) throws SQLException;

    Long insert(T item);

    boolean update(T item, Long id);

    boolean delete(Long id);

    T findById(Long id);

    List<T> findAll();

    long count();
}
