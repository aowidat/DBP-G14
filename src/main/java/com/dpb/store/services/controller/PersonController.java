package com.dpb.store.services.controller;

import com.dpb.store.entites.Person;
import com.dpb.store.entites.Review;
import com.dpb.store.repos.PersonRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController("/person/")
public class PersonController {
    PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("avgrating/{avg}")
    List<Person> getPersonsUnderAvg(@PathVariable Double avg){
        List<Person> persons = new ArrayList<>();
        for (Person pr : personRepo.findAll()){
            if (personAvgRating(pr)<avg){
                persons.add(pr);
            }
        }
        return persons;
    }


    Double personAvgRating(Person person){
        Double avg = 0.0;
        if (person.getReviews().size() > 0) {
            for (Review review : person.getReviews()){
                avg = (avg + review.getRating());
            }
        }
        return avg/person.getReviews().size();
    }


    @GetMapping("avgratingup/{avg}")
    List<Person> getPersonsUnderAvgUp(@PathVariable Double avg){
        List<Person> persons = new ArrayList<>();
        for (Person pr : personRepo.findAll()){
            if (personAvgRating(pr)>avg){
                persons.add(pr);
            }
        }
        return persons;
    }


}
