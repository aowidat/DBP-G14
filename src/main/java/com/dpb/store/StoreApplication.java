package com.dpb.store;

import com.dpb.store.services.controller.DataLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Array;
import java.util.*;

@Slf4j
@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(DataLoader dataLoader) {
        return (args) -> {
            log.info("\t\t\t========== DATA IS BEING LOADING ==========");
            dataLoader.loadAllData();
            System.out.println("\t\t\t========== DATA LOADING IS FINISHED ==========");
        };
    }
}
