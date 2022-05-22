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
    SimiRepo simiRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ProductRepo productRepo;

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
        validator.categoriesConverter(parser.getCategories());
        System.out.println(validator.getValidCategory().size());
        for (Category categ : validator.getValidCategory()){
            System.out.println("start >>>>>>>> ");
            System.out.println( "name "+ categ.getName());
            if (categ.getProducts() != null) System.out.println("pro " + categ.getProducts().size());
            if (categ.getParents() != null) System.out.println("par "+categ.getParents().size());
            if (categ.getChildren() != null) System.out.println("chi "+categ.getChildren().size());
            System.out.println("end >>>>>>>> ");
        }

        List<CD> cds = validator.getValidCD();
        List<DVD> dvds = validator.getValidDVD();
        List<Book> books = validator.getValidBook();
        List<Review> reviews = parser.getReview();
        List<Category> categories = validator.getValidCategory();
        List<Person> personList = validator.getValidPerson();
        for (Review r : reviews) {
            validator.reviewValidator(r);
        }
//        List<com.dpb.store.entites.Review> reviewList = validator.getValidReview();
//        leipzigRepo.save(storeLeipzig);
//        dresdenRepo.save(storeDresden);
//        productRepo.saveAll(validator.getValidProduct());
//        categoryRepo.saveAll(categories);
//        dvdRepo.saveAll(dvds);
//        cdRepo.saveAll(cds);
//        bookRepo.saveAll(books);
//        personRepo.saveAll(validator.getValidPerson());
//        reviewRepo.saveAll(validator.getValidReview());
//        simiRepo.saveAll(validator.getValidSimiProduct());
    }

}
