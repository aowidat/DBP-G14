package com.dpb.store;

import com.dpb.store.entites.Product;
import com.dpb.store.entites.Store;
import com.dpb.store.services.parser.Categories;
import com.dpb.store.services.parser.Parser;
import com.dpb.store.services.parser.Review;
import com.dpb.store.services.parser.Shop;
import com.dpb.store.services.validator.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void trysomethnig() throws IOException {
        Parser parser = new Parser();
        parser.lunchParser();
        Shop leipzig = parser.getLeipzig();
        Shop dresden = parser.getDresden();
        Validator validator = new Validator();
        Store storeLeipzig = validator.storeValidator(leipzig);
        Store storeDresden = validator.storeValidator(dresden);
        Categories categories = parser.getCategories();
//        for (Product pr : storeLeipzig.getProducts()) {
//            System.out.println(pr.toString());
//        }
        List<Review> reviews = parser.getReview();
        int t = 0;
        int f = 0;
        for (Review r : reviews) {
            if (validator.reviewValidator(r)) t++;
            else f++;
        }
        System.out.println(t);
        System.out.println(f);
        System.out.println("ID " + validator.getErrorID());
        System.out.println("Rating " + validator.getErrorR());
        System.out.println("Date " + validator.getErrorD());
        System.out.println("Helpful " + validator.getErrorH());
        System.out.println("Summery " + validator.getErrorS());
        System.out.println("Content " + validator.getErrorC());
//        System.out.println("Number  " + validator.getErrorU());
//        String str = "Ahmad";
//        String str2 = "\"\"" + str + "\"\"";
//        System.out.println(str2);
//        System.out.println(str2.replaceAll("\"", ""));
    }

}
