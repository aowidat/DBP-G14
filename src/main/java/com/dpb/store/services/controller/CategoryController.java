package com.dpb.store.services.controller;

import com.dpb.store.entites.Category;
import com.dpb.store.repos.CategoryRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("categroy/")
    List<Category> getCategoryTree() {
        List<Category> tree = new ArrayList<>();
        List<Category> parent = categoryRepo.findByParent(null);
        for (Category main : parent) {
            tree.addAll(getTree(main));
        }
        return tree;
    }

    List<Category> getTree(Category category) {
        List<Category> all = new ArrayList<>();
        if (category == null) {
            return null;
        } else if (category.getChildren() == null || category.getChildren().size() == 0) {
            all.add(category);
            return all;
        }
        for (Category child : category.getChildren()) {
            all.addAll(getTree(child));
        }
        return all;
    }

    @GetMapping("categroy/name?={name}")
    Category getCategoryByName(@PathVariable String name) {
        Category category = categoryRepo.findOneByName(name);
        return category;
    }
}
