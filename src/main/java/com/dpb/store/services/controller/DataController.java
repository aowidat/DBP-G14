package com.dpb.store.services.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Slf4j(topic = "DataController")
@CrossOrigin
@RestController
public class DataController {
    @Autowired
    DataSource dataSource;

    @Autowired
    private WebApplicationContext context;
    DataLoader dataLoader;

    public DataController(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @GetMapping("/init")
    public Map<String, String> init() {
        Map<String, String> respon = new HashMap<>();
        try {
            log.info("Starting SQL Scripts");
            ResourceDatabasePopulator populator1 = new ResourceDatabasePopulator(context.getResource("classpath:/schema.sql"));
            DatabasePopulatorUtils.execute(populator1, dataSource);
            dataSource.getConnection().beginRequest();
            ResourceDatabasePopulator populator3 = new ResourceDatabasePopulator(context.getResource("classpath:/data/other.sql"));
            DatabasePopulatorUtils.execute(populator3, dataSource);
            ResourceDatabasePopulator populator2 = new ResourceDatabasePopulator(context.getResource("classpath:/data/data.sql"));
            DatabasePopulatorUtils.execute(populator2, dataSource);
            respon.put("status", "done");
            log.info("Loading SQL Scripts is done");
            return respon;
        } catch (Exception e) {
            log.error("Something went wrong {}", e.getMessage());
            respon.put("status", e.getMessage());
            return respon;
        }
    }

    @GetMapping("/finish")
    public Map<String, String> finish() throws SQLException {
        Map<String, String> respon = new HashMap<>();
        try {
            log.info("Finishing the App");
            dataSource.getConnection().close();
            respon.put("status", "done");
            return respon;
        } catch (Exception e){
            log.error("Something went wrong {}", e.getMessage());
            respon.put("status", e.getMessage());
            return respon;
        }
    }
}