package com.volkov.client.manager;

import com.volkov.client.manager.exception.SaveConfigException;

public interface ConfigurationManger {

    String getServerUrl();

    void setServerUrl(String url) throws SaveConfigException;

    Integer getServerPort();

    void setServerPort(Integer port) throws SaveConfigException;

    void save() throws SaveConfigException;

    void createProperties() throws SaveConfigException;

    boolean checkProperties();
}
