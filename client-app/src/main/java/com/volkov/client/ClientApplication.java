package com.volkov.client;

import com.volkov.client.manager.ConfigurationManger;
import com.volkov.client.manager.ConfigurationMangerImpl;
import com.volkov.client.manager.exception.SaveConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {
    private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);
    public static void main(String[] args) {
        log.info("Starting application Client");
        try{
            ConfigurationManger configurationManger = new ConfigurationMangerImpl();
            if(!configurationManger.checkProperties()){
                log.warn("Parameters for start client is incorrect, try set default");
                configurationManger.createProperties();
            }
        } catch (SaveConfigException e) {
            log.warn("All parameters set as default");
        }
        SpringApplication.run(ClientApplication.class, args);
    }
}
