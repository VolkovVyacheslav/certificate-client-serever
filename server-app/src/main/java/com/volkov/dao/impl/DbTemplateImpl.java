package com.volkov.dao.impl;

import com.sun.org.apache.xerces.internal.dom.EntityImpl;
import com.sun.rowset.internal.Row;
import com.volkov.converter.AbstractConverter;
import com.volkov.converter.impl.AbstractConverterImpl;
import com.volkov.dao.DbTemplate;
import com.volkov.entity.AbstractEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbTemplateImpl implements DbTemplate {

    private final String DB_DRIVER_NAME = "org.postgresql.Driver";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String url;
    private String user;
    private String password;

    private <C extends AbstractConverter> converter = new AbstractConverterImpl();

    public DbTemplateImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            Class.forName(DB_DRIVER_NAME);
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DbTemplateImpl() {
    }

    @Override
    public void setDataSourceUrl(String url) {
        this.url = url;
    }

    @Override
    public void setDataSourceUsername(String username) {
        this.user = username;
    }

    @Override
    public void setDataSourcePassword(String password) {
        this.password = password;
    }

    @Override
    public <E extends AbstractEntity> List<E> getEtitys(String query) throws SQLException {
        if (getResultSetByQuery(query).next()){
            List<E> entities = new ArrayList<>();
            entities.add((E)new EntityImpl(getResultSetByQuery(query)));
        }
        return Collections.emptyList();
    }

    private ResultSet getResultSetByQuery(String query) throws SQLException {
        try {
            if (null != conn) {
                if (!conn.isClosed()) {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           close();
        }
        return rs;
    }

    @Override
    public ResultSet saveResultSet(ResultSet resultSet) throws SQLException {
       for (Row row : resultSet) {

       }
        return null;
    }

    @Override
    public ResultSet saveResultSet(ResultSet resultSet, Object... params) throws SQLException {
        return null;
    }


    @Override
    public void executeUpdate(String query) throws SQLException {

    }

    @Override
    public void delete(String query) throws SQLException {

    }

    @Override
    public void close() throws SQLException {
       stmt.close();
       conn.close();
    }
}
