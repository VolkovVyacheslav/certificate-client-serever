package com.volkov.dao.impl;

import com.volkov.dao.DbTemplate;

import java.sql.*;

public class DbTemplateImpl implements DbTemplate {

    private final String DB_DRIVER_NAME = "org.postgresql.Driver";
    private final String DB_CREATE_CERTIFICATE_TABLE = "CREATE TABLE IF NOT EXISTS certificates (";
    private final String DB_CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS schedule (id BIGSERIAL NOT NULL, ";

    private Connection conn;
    private String url;
    private String user;
    private String password;


    public DbTemplateImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            Class.forName(DB_DRIVER_NAME);
            this.conn = DriverManager.getConnection(url, user, password);
            if (!conn.isValid(5)){
                throw new SQLException();
            }
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
    public void init(){
        try {
            if (null != conn) {
                int result = 0;
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(DB_CREATE_CERTIFICATE_TABLE);
                result = +stmt.executeUpdate(DB_CREATE_SCHEDULE_TABLE);
                if (result < 2){
                    System.out.println("Warning! DB create exception, or DB already exist");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeReadQuery(String query) throws SQLException {
        Statement stmt;
        ResultSet rs = null;
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
    public void close() throws SQLException {
        if (null != conn && !conn.isClosed()) {
            conn.close();
        }
    }
}
