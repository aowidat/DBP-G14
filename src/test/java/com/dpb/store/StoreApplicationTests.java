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
        List<Review> r = parser.getReview();

//        System.out.println(validator.getValidCategory().size());
//        for (Category categ : validator.getValidCategory()){
//            System.out.println("start >>>>>>>> ");
//            System.out.println( "name "+ categ.getName());
//            if (categ.getProducts() != null) System.out.println("pro " + categ.getProducts().size());
//            if (categ.getParents() != null) System.out.println("par "+categ.getParents().size());
//            if (categ.getChildren() != null) System.out.println("chi "+categ.getChildren().size());
//            System.out.println("end >>>>>>>> ");
//        }


        Store storeLeipzig = validator.storeValidator(leipzig);
        Store storeDresden = validator.storeValidator(dresden);
        validator.categoriesConverter(parser.getCategories());
        validator.reviewsValidator(r);

        List<CD> cds = validator.getValidCD();
        List<DVD> dvds = validator.getValidDVD();
        List<Book> books = validator.getValidBook();
        List<Category> categories = validator.getValidCategory();
        List<Person> personList = validator.getValidPerson();
        List<com.dpb.store.entites.Review> reviewList = validator.getValidReview();
        List<SimiProduct> sims = validator.getValidSimiProduct();
        List<Product> validProduct = validator.getValidProduct();
        leipzigRepo.save(storeLeipzig);
        dresdenRepo.save(storeDresden);
        productRepo.saveAll(validProduct);
        simiRepo.saveAll(sims);
        dvdRepo.saveAll(dvds);
        cdRepo.saveAll(cds);
        bookRepo.saveAll(books);
        personRepo.saveAll(personList);
        reviewRepo.saveAll(reviewList);
        categoryRepo.saveAll(categories);
    }

}
