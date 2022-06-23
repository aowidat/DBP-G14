package com.dpb.store.services.controller;

import com.dpb.store.entites.Category;
import com.dpb.store.repos.CategoryRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/categroy")
    Map<Category,List<Category>> getCategoryTree() {
        List<Category> allcat = categoryRepo.findAll();
        Map<Category,List<Category>> finalCategories = new HashMap<>();
        for (Category cat : allcat){
            if (cat.getChildren().size() > 0){
                List<Category> children = new ArrayList<>();
                children.addAll(cat.getChildren());
                finalCategories.put(cat,children);
            } else{
                finalCategories.put(cat,null);
            }


        }


        return  finalCategories;


    }

    @GetMapping("categroy/name?={name}")
    Category getCategoryByName(@PathVariable String name) {
        Category category = categoryRepo.findOneByName(name);
        return category;
    }
}
