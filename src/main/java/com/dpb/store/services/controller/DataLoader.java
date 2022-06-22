package com.dpb.store.services.controller;

import com.dpb.store.entites.*;
import com.dpb.store.repos.*;
import com.dpb.store.services.parser.Parser;
import com.dpb.store.services.parser.Review;
import com.dpb.store.services.parser.Shop;
import com.dpb.store.services.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Configuration
@Slf4j
public class DataLoader {
    ReviewRepo reviewRepo;
    DVDRepo dvdRepo;
    BookRepo bookRepo;
    CDRepo cdRepo;
    StoreRepo testRepo;
    StoreRepo storeRepo;
    PersonRepo personRepo;
    CategoryRepo categoryRepo;
    ProductRepo productRepo;
    OfferRepo offerRepo;

    public DataLoader(ReviewRepo reviewRepo, DVDRepo dvdRepo, BookRepo bookRepo, CDRepo cdRepo, StoreRepo testRepo, StoreRepo storeRepo, PersonRepo personRepo, CategoryRepo categoryRepo, ProductRepo productRepo, OfferRepo offerRepo) throws IOException {
        this.reviewRepo = reviewRepo;
        this.dvdRepo = dvdRepo;
        this.bookRepo = bookRepo;
        this.cdRepo = cdRepo;
        this.testRepo = testRepo;
        this.storeRepo = storeRepo;
        this.personRepo = personRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.offerRepo = offerRepo;
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            log.info("\t\t\t========== DATA IS BEING LOADING ==========");
            loadAllData();
            System.out.println("\t\t\t========== DATA LOADING IS FINISHED ==========");
        };
    }

    public void loadAllData() throws IOException {
        Parser parser = new Parser();
        parser.lunchParser();
        Validator validator = new Validator();
        Shop leipzig = parser.getLeipzig();
        Shop dresden = parser.getDresden();
        List<Review> r = parser.getReview();

        Store storeLeipzig = validator.storeValidator(leipzig);
        Store storeDresden = validator.storeValidator(dresden);

        validator.categoriesConverter(parser.getCategories());
        validator.reviewsValidator(r);

        List<Category> categories = validator.getValidCategory();
        List<Person> personList = validator.getValidPerson();
        List<com.dpb.store.entites.Review> reviewList = validator.getValidReview();

        validator.setAllsiliers();

        storeRepo.save(storeLeipzig);
        storeRepo.save(storeDresden);
        categoryRepo.saveAll(categories);
        personRepo.saveAll(personList);
        productRepo.saveAll(validator.getValidProduct());
        reviewRepo.saveAll(reviewList);
        validator.getValidProduct().forEach(x -> offerRepo.saveAll(x.getOffers()));
    }
}
