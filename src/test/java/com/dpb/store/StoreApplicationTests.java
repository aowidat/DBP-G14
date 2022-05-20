package com.dpb.store;

import com.dpb.store.entites.*;
import com.dpb.store.repos.*;
import com.dpb.store.services.parser.Categories;
import com.dpb.store.services.parser.Parser;
import com.dpb.store.services.parser.Review;
import com.dpb.store.services.parser.Shop;
import com.dpb.store.services.validator.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    DVDRepo dvdRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    CDRepo cdRepo;
    @Autowired
    StoreRepo leipzigRepo;
    @Autowired
    StoreRepo testRepo;
    @Autowired
    StoreRepo dresdenRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void trysomethnig() throws IOException {

        Parser parser = new Parser();
        parser.lunchParser();
        Shop leipzig = parser.getLeipzig();
        Shop dresden = parser.getDresden();
//        Shop test = parser.getTest();
        Validator validator = new Validator();
        Store storeLeipzig = validator.storeValidator(leipzig);
        Store storeDresden = validator.storeValidator(dresden);
//        Store storeTest = validator.storeValidator(test);

//        Categories categories = parser.getCategories();
//        for (Product pr : storeLeipzig.getProducts()) {
//            System.out.println(pr.toString());
//        }
//        List<Review> reviews = parser.getReview();
//        List<com.dpb.store.entites.Review> entiteRevies = new ArrayList<>();
//        for (Review r : reviews) {
//            if (validator.reviewValidator(r)) ;
//        }
//        entiteRevies.addAll(validator.getValidReview());
        List<CD> cds = validator.getValidCD();
        List<DVD> dvds = validator.getValidDVD();
        List<Book> books = validator.getValidBook();
//        dresdenRepo.save(storeDresden);
//        leipzigRepo.save(storeLeipzig);

        dvdRepo.saveAll(dvds);
        cdRepo.saveAll(cds);
        bookRepo.saveAll(books);
//        System.out.println(t);
//        System.out.println(f);
//        System.out.println("v p " + validator.getVp());
//        System.out.println("u p " + validator.getUp());
//        System.out.println("v d " + validator.getVd());
//        System.out.println("u d " + validator.getUd());
//        System.out.println("v c " + validator.getVc());
//        System.out.println("u c " + validator.getUc());
//        System.out.println("v b " + validator.getVb());
//        System.out.println("u b " + validator.getUb());
//        System.out.println("u r " + validator.getUr());
//        System.out.println("Number  " + validator.getErrorU());
//        String str = "Ahmad";
//        String str2 = "\"\"" + str + "\"\"";
//        System.out.println(str2);
//        System.out.println(str2.replaceAll("\"", ""));
    }

}
