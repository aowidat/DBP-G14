package com.dpb.store;

import com.dpb.store.entites.*;
import com.dpb.store.repos.*;
import com.dpb.store.services.parser.*;
import com.dpb.store.services.parser.Review;
import com.dpb.store.services.validator.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
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
    CategoryRepo categoryRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    OfferRepo offerRepo;

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
        List<Review> r = parser.getReview();

        Store storeDresden = validator.storeValidator(dresden);
        Store storeLeipzig = validator.storeValidator(leipzig);
        validator.categoriesConverter(parser.getCategories());
        validator.reviewsValidator(r);

        List<CD> cds = validator.getValidCD();
        List<DVD> dvds = validator.getValidDVD();
        List<Book> books = validator.getValidBook();
        List<Category> categories = validator.getValidCategory();
        List<Person> personList = validator.getValidPerson();
        List<com.dpb.store.entites.Review> reviewList = validator.getValidReview();
        validator.setAllsiliers();
//        for (DVD dvd: dvds){
//            for (Offer offer: dvd.getOffers()){
//                System.out.println(offer);
//            }
//        }
//        for (CD cd: cds){
//            for (Offer offer: cd.getOffers()){
//                System.out.println(offer);
//            }
//        }
//        for (Book book: books){
//            for (Offer offer: book.getOffers()){
//                System.out.println(offer);
//            }
//        }
        storeLeipzig.getOffers().forEach(System.out::println);
        storeDresden.getOffers().forEach(System.out::println);
//        categoryRepo.saveAll(categories);
//        leipzigRepo.save(storeLeipzig);
//        dresdenRepo.save(storeDresden);
//        productRepo.saveAll(validator.getValidProduct());
//        reviewRepo.saveAll(reviewList);
//        personRepo.saveAll(personList);
//        dvdRepo.saveAll(dvds);
//        cdRepo.saveAll(cds);
//        bookRepo.saveAll(books);
//        for (DVD dvd : dvds) {
//            offerRepo.saveAll(dvd.getOffers());
//        }
//        for (CD cd : cds) {
//            offerRepo.saveAll(cd.getOffers());
//        }
//        for (Book book : books) {
//            offerRepo.saveAll(book.getOffers());
//        }
    }

}
