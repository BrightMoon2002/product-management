package com.codegym.dao.typeProductDAO;

import com.codegym.model.TypeProduct;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeProductDAO implements ITypeProductDAO{
    private Connection connection = SingletonConnection.getConnection();
    @Override
    public List<TypeProduct> selectAllProduct() {
        List<TypeProduct> typeProductList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM typeProduct;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idTypeProduct");
                String name = rs.getString("name");
                typeProductList.add(new TypeProduct(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeProductList;
    }

    @Override
    public void insertProduct(TypeProduct typeProduct) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSSERT INTO from product VALUE (name) VALUE (?);");
            preparedStatement.setString(1, typeProduct.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public TypeProduct selectProduct(int id) {
        TypeProduct typeProduct = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM typeProduct WHERE idTypeProduct = ?;");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int idType = rs.getInt("idTypeProduct");
                String name = rs.getString("name");
                typeProduct = new TypeProduct(idType, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return typeProduct;

    }

    @Override
    public boolean updateProduct(TypeProduct typeProduct) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE typeProduct name = ? WHERE id = ?;");
            preparedStatement.setString(1, typeProduct.getName());
            preparedStatement.setInt(2, typeProduct.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDelete = false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE from typeProduct where id = ?");
            preparedStatement.setInt(1 , id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }
}
