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

    @Bean
    CommandLineRunner cmd(PersonRepo pr, CDRepo cr) {
        return args -> {
            Person john = new Person();
            john.setName("maen");
            pr.save(john);
            CD cd = new CD();
            cd.setId("hajskdfhaskpjhdfa");
            cd.setBinding("asdasdasdasd");
            cd.setImage("asdfasdfasd");
            List<Person> rre = new ArrayList<>();
            rre.add(john);
            cd.setArtists(rre);
            cr.save(cd);
        };
    }


}
