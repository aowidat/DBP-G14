package com.dpb.store.services.controller;

import com.dpb.store.entites.Person;
import com.dpb.store.entites.Review;
import com.dpb.store.repos.PersonRepo;
import com.dpb.store.repos.ReviewRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/review/")
public class ReviewController {

    ReviewRepo reviewRepo;
    @Autowired
    PersonRepo personRepo;

    public ReviewController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
    @CrossOrigin
    @PostMapping("addReview")
    @ExceptionHandler(ConstraintViolationException.class)
    public Review addNewReview(@RequestBody Review review) {
        try {
            Review review1 = new Review();
            if (!review.getContent().isEmpty() && !review.getSummery().isEmpty()) {
                if (review.getRating() > 0 && review.getRating() < 6) {
                    Person p = new Person();
                    p.setName(review.getPerson().getName());
                    review1.setPerson(p);
                    review1.setContent(review.getContent());
                    review1.setDate(LocalDate.now());
                    review1.setHelpful(0);
                    review1.setRating(review.getRating());
                    review1.setSummery(review.getSummery());
                    review1.setProduct_review(review.getProduct_review());
                    personRepo.save(p);
                    reviewRepo.save(review1);
                    return review1;
                }
            }
            return null;

        } catch (DataIntegrityViolationException e) {
            return null;
//            return e.getMessage();
        }
    }

    @GetMapping("getReview/{id}")
    Review getReview(@PathVariable Integer id) {
        return reviewRepo.findById(id).orElseThrow();
    }

    @GetMapping("getReviews")
    List<Review> getReviews() {
        return reviewRepo.findAll();
    }
}
