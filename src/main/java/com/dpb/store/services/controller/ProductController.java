package com.dpb.store.services.controller;

import com.dpb.store.entites.Offer;
import com.dpb.store.entites.Product;
import com.dpb.store.repos.ProductRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/product/")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("findById/{id}")
    List<Product> getOne(@PathVariable String id) {
        List<Product> products= new ArrayList<>();

        products.add(productRepo.findById(id).orElseThrow());
        return products;
    }
    @CrossOrigin
    @GetMapping("findAllByPattern/{pattern}")
    List<Product> getAllByPattern(@PathVariable String pattern) {
        return productRepo.findAllByTitle(pattern);
    }

    @GetMapping("TopProducts")
    List<Product> topProduct() {
        Double max = Double.MIN_VALUE;
        for (Product pr : productRepo.findAll()) {
            if (pr.getRating() > max) {
                max = pr.getRating();
            }
        }
        return productRepo.findAllByRating(max);
    }

    @GetMapping("getSimilarCheaperProduct/{id}")
    List<Product> getSimilarCheaperProduct(@PathVariable String id) {

        Product product = productRepo.findById(id).orElseThrow();
        Double min = Double.MAX_VALUE;
        List<Product> similars = product.getSimilar();
        for (Offer offer : product.getOffers()) {
            if (offer.getPrice() <= min) {
                min = offer.getPrice();
            }
        }
        List<Product> simisCheaper = new ArrayList<>();
        for (Product pro : similars) {
            for (Offer offer : pro.getOffers()) {
                if (offer.getPrice() < min) {
                    simisCheaper.add(pro);
                }
            }
        }
        return simisCheaper;
    }


    @GetMapping("GetOffers/{id}")
    List<Offer> getOffers(@PathVariable String id) {
        return productRepo.findById(id).orElseThrow().getOffers();
    }
    @GetMapping("getAllProduct")
    List<Product> allProducts(){
        return productRepo.findAll();
    }
}
