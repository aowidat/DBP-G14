package com.dpb.store;

import com.dpb.store.entites.CD;
import com.dpb.store.entites.Person;
import com.dpb.store.repos.CDRepo;
import com.dpb.store.repos.PersonRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Array;
import java.util.*;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);

    }
}
