package com.dpb.store.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

@RestController
public class DataController {
    DataSource dataSource;

    public void buildConnection() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("root");
        DataSource ds = dataSourceBuilder.build();
        dataSource = ds;
    }

    @Autowired
    private WebApplicationContext context;
    DataLoader dataLoader;

    public DataController(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

//    @GetMapping("/init")
    public String init() throws IOException {
        this.buildConnection();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(context.getResource("classpath:/schema.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);
        dataLoader.loadAllData();
        return "DATA HAS BEEN LOADED";
    }

    @GetMapping("/finish")
    public String finish() throws SQLException {
        //todo
        dataSource.getConnection().close();
        return "FINISH";
    }
}