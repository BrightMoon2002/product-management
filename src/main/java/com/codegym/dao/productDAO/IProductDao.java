package com.codegym.dao.productDAO;

import com.codegym.dao.IDataAccessObject;
import com.codegym.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao extends IDataAccessObject<Product> {


    List<Product> selectProductByProducer(String producer);

    List<Product> selectProductByDescription(String description);

    List<Product> sortByName();
}
