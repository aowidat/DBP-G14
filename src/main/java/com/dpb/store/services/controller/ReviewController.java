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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> addNewReview(@RequestBody Review review) {
        Map<String, String> respons = new HashMap<>();
        try {
            Review review1 = new Review();
            if (!review.getContent().isEmpty() || !review.getContent().isBlank()) {
                if (review.getRating() > 0 && review.getRating() < 6) {
                    if (!review.getSummery().isEmpty() || !review.getSummery().isBlank()) {
                        if (!review.getPerson().getName().isEmpty() || !review.getPerson().getName().isBlank()) {
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
                            respons.put("status", "success");
                            return respons;
                        }
                        respons.put("status", "Error User");
                        return respons;
                    }
                    respons.put("status", "Error Summery");
                    return respons;
                }
                respons.put("status", "Error Rating");
                return respons;
            }
            respons.put("status", "Error Content");
            return respons;
        } catch (DataIntegrityViolationException e) {
            respons.put("status", "Error " + e.getMessage());
            return respons;
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
