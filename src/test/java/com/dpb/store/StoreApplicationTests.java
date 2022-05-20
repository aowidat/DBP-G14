package com.dpb.store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class StoreApplicationTests {

    @Test
    void contextLoads() {
       String i = "";
       LocalDate x = LocalDate.parse(i);
        System.out.println(x);

    }

}
