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
    @Autowired
    PersonRepo personRepo;
    @Autowired
    SimiRepo simiRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void trysomethnig() throws IOException {

        Parser parser = new Parser();
        parser.lunchParser();
        Validator validator = new Validator();
        Shop leipzig = parser.getLeipzig();
        Shop dresden = parser.getDresden();
        Store storeLeipzig = validator.storeValidator(leipzig);
        Store storeDresden = validator.storeValidator(dresden);
        List<CD> cds = validator.getValidCD();
        List<DVD> dvds = validator.getValidDVD();
        List<Book> books = validator.getValidBook();
        List<Review> reviews = parser.getReview();
        for (Review r : reviews) {
            validator.reviewValidator(r);
        }
        dresdenRepo.save(storeDresden);
        leipzigRepo.save(storeLeipzig);
        simiRepo.saveAll(validator.getValidSimiProduct());
        dvdRepo.saveAll(dvds);
        cdRepo.saveAll(cds);
        bookRepo.saveAll(books);
        personRepo.saveAll(validator.getValidPerson());
        reviewRepo.saveAll(validator.getValidReview());

    }

}
