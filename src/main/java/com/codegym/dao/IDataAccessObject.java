package com.codegym.dao;

import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IDataAccessObject<T> {
    List<T> selectAllProduct();

    void insertProduct(T t) throws SQLException;

    T selectProduct(int id);

    boolean updateProduct(T t) throws SQLException;

    boolean deleteProduct(int id) throws SQLException;
}
