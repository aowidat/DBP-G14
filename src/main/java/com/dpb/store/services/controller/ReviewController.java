package com.dpb.store.services.controller;

import com.dpb.store.entites.Person;
import com.dpb.store.entites.Review;
import com.dpb.store.repos.ReviewRepo;
import org.hibernate.exception.ConstraintViolationException;
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

    public ReviewController(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @PostMapping("addReview")
    @ExceptionHandler(ConstraintViolationException.class)
    public String addNewReview(@RequestBody Review review) {
        try {
            Review review1 = new Review();
            if (!review.getContent().isEmpty() && !review.getSummery().isEmpty()) {
                if (review.getRating() > 0 && review.getRating() < 6) {
                    review1.setPerson(review.getPerson());
                    review1.setContent(review.getContent());
                    review1.setDate(LocalDate.now());
                    review1.setHelpful(0);
                    review1.setRating(review.getRating());
                    review1.setSummery(review.getSummery());
                    review1.setProduct_review(review.getProduct_review());
                    reviewRepo.save(review1);
                    return "DD";
                }
            }
            return "HH";

        } catch (DataIntegrityViolationException e) {

            return e.getMessage();
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
