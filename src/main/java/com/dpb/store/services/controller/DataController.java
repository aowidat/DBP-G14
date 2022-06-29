package com.dpb.store.services.controller;

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
    public String init() throws IOException {
//        this.buildConnection();
        ResourceDatabasePopulator populator1 = new ResourceDatabasePopulator(context.getResource("classpath:/schema.sql"));
        DatabasePopulatorUtils.execute(populator1, dataSource);
        ResourceDatabasePopulator populator2 = new ResourceDatabasePopulator(context.getResource("classpath:/data/data.sql"));
        DatabasePopulatorUtils.execute(populator2, dataSource);
        return "DATA HAS BEEN LOADED";
    }

    @GetMapping("/finish")
    public String finish() throws SQLException {
        dataSource.getConnection().close();
        return "FINISH";
    }
}