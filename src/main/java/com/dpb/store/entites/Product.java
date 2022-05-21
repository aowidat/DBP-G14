package com.dpb.store.entites;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(columnDefinition = "text", length = 10485760)
    private String image;
    private int rating;
    private boolean available;
    @ElementCollection
    private List<String> listmania;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_store", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> stores;
    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
//    private Set<Category> categories;
    @OneToMany(mappedBy = "product_review")
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_similar", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "simi_id", referencedColumnName = "id"))
    private List<SimiProduct> similar;


    public void addNewListMania(String str) {
        if (this.listmania == null) {
            this.listmania = new ArrayList<>();
        }
        this.listmania.add(str);
    }

    public void addNewSimProduct(SimiProduct pr) {
        if (this.similar == null) {
            this.similar = new ArrayList<>();
        }
        this.similar.add(pr);
    }

    public void addNewReview(Review rw) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(rw);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
