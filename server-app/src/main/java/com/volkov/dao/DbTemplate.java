package com.volkov.dao;

import com.volkov.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbTemplate {

    void setDataSourceUrl(String url);

    void setDataSourceUsername(String username);

    void setDataSourcePassword(String password);

    <E extends AbstractEntity> List<E> getEtitys(String query) throws SQLException;

    ResultSet saveResultSet(ResultSet resultSet) throws SQLException;

    ResultSet saveResultSet(ResultSet resultSet, Object... params) throws SQLException;


    void executeUpdate(String query) throws SQLException;

    void delete(String query) throws SQLException;

    void close() throws SQLException;
}
