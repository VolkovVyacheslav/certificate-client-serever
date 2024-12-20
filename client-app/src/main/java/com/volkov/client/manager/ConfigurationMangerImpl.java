package com.volkov.client.manager;

import com.volkov.client.manager.exception.SaveConfigException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class ConfigurationMangerImpl implements ConfigurationManger {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationMangerImpl.class);

    private static final String PREF_KEY_SERVER_URL = "server.http.url";
    private static final String PREF_KEY_SERVER_PORT = "server.http.port";
    private static final String CONFIG_FILE_PATH = "./client.properties";

    private Properties prop = new Properties();

    public ConfigurationMangerImpl() throws SaveConfigException{
        InputStream input = null;

        try {
            input = Files.newInputStream(Paths.get(CONFIG_FILE_PATH));
            prop.load(input);
        } catch (IOException io) {
            log.error("Could not load config " + CONFIG_FILE_PATH, io);
            log.info("Try create properties file");
            createProperties();
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    @Override
    public String getServerUrl() {
            return prop.getProperty(PREF_KEY_SERVER_URL, "localhost");
    }

    @Override
    public void setServerUrl(String url) throws SaveConfigException {
        prop.put(PREF_KEY_SERVER_URL, url != null ? url : "localhost");
        save();
    }

    @Override
    public Integer getServerPort() {
        if(isCorrectPort()){
            try{
                return Integer.getInteger(prop.getProperty(PREF_KEY_SERVER_PORT));
            }catch (Exception e){
                log.warn("Incorrect server port value or not set, leave default: 8085");
                return 8085;
            }
        }
        return 8085;
    }

    @Override
    public void setServerPort(Integer port) throws SaveConfigException {
         prop.put(PREF_KEY_SERVER_PORT, port != null ? port.toString() : "8085");
         save();
    }

    @Override
    public void save() throws SaveConfigException {
        OutputStream outputStream = null;
        try{
            outputStream = Files.newOutputStream(Paths.get(CONFIG_FILE_PATH));
            prop.store(outputStream, null);
        } catch (IOException e){
            log.error("Error! save configuration to " + CONFIG_FILE_PATH, e);
            throw new SaveConfigException();
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    @Override
    public void createProperties() throws SaveConfigException {
        if(!isCorrectURL()) {
            setServerUrl("localhost");
        }
        if(!isCorrectPort()) {
            setServerPort(8085);
        }
    }

    @Override
    public boolean checkProperties() {
        return isCorrectPort() &&
                isCorrectURL();
    }

    private boolean isCorrectURL(){
        return null != prop.getProperty(PREF_KEY_SERVER_URL) &&
                prop.getProperty(PREF_KEY_SERVER_URL).length() > 0;
    }

    private boolean isCorrectPort(){
        return null != prop.getProperty(PREF_KEY_SERVER_PORT) &&
                prop.getProperty(PREF_KEY_SERVER_PORT).length() > 0;
    }
}
