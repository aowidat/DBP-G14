package com.dpb.store.entites;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    private String id;
    private String title;
    private int salesRank;
    private double price;
    private String status;
    private String image;
    private int rating;
    @ElementCollection
    private Set<String> listmania;
    public void addNewListMania(String str) {
        if (this.listmania == null) {
            this.listmania = new HashSet<>();
        }
        this.listmania.add(str);
    }
    public void addNewSimProcuct(Product pr) {
        if (this.similars == null) {
            this.similars = new HashSet<>();
        }
        this.similars.add(pr);
    }


    @OneToMany
    private Set<Product> similars;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_store", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private Set<Store> stores;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;
}
