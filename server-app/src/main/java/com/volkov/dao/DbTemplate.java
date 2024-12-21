package com.volkov.dao;

import com.volkov.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbTemplate {

    void setDataSourceUrl(String url);

    void setDataSourceUsername(String username);

    void setDataSourcePassword(String password);

    void init() throws SQLException;

    ResultSet executeReadQuery(String sql) throws SQLException;

    void close() throws SQLException;
}
