package com.dpb.store.services.controller;

import com.dpb.store.entites.Category;
import com.dpb.store.entites.Product;
import com.dpb.store.repos.CategoryRepo;
import com.dpb.store.repos.ProductRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
@CrossOrigin
@RestController("/category/")
public class CategoryController {
    CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("getTree")
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

    @GetMapping("name")
    Category getCategoryByName(@PathVariable String name) {
        Category category = categoryRepo.findOneByName(name);
        return category;
    }

    @GetMapping("productPerPath/{path}")
    List<Product> getProductPerPath(@PathVariable String path) throws IOException {
        List<Product> products = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>(Arrays.stream(path.split("\\$")).toList());
        List<Category> allCategory = new ArrayList<>();
        Map<Category, List<Category>> categoryTree = getCategoryTree();
        for ( var entry : categoryTree.entrySet()){
           if (entry.getKey().getName().equalsIgnoreCase(categoryNames.get(0))){
               List<String> str = categoryNames;
               str.remove(0);
               List<Category> cats = entry.getValue();
               products = getProductsPerPath(cats,str);
               break;
           }
        }
return products;
    }

    public List<Product> getProductsPerPath(List<Category> child,List<String> path){
        List<Product> products = new ArrayList<>();
         if (path.size() > 1){
             for (Category cat : child){
                 if (path.get(0).equalsIgnoreCase(cat.getName())){
                     path.remove(0);
                     List<Category> cats = cat.getChildren();
                     products = getProductsPerPath(cats, path);
                 }
             }
         } else if ( path.size()==1){
             for (Category cat : child){
                 if (path.get(0).equalsIgnoreCase(cat.getName())){
                     products = cat.getProducts();
                 }
             }
             return products;
         }
        return products;
    }



}
