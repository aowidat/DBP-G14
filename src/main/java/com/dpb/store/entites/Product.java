package com.dpb.store.entites;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Product Entity
 */
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
    @ElementCollection
    private List<String> listmania;
    @ManyToMany
    @JoinTable(name = "product_store", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> stores;

    @ManyToMany
    @JoinTable(name = "product_in_store", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> availableInStores;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;
    @OneToMany(mappedBy = "product_review")
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_similar", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "similar_id", referencedColumnName = "id"))
    private List<Product> similar;

    /**
     * Method to add a new List-Mania to a List of listManias
     * @param listMania to be added
     */
    public void addNewListMania(String listMania) {
        if (this.listmania == null) {
            this.listmania = new ArrayList<>();
        }
        this.listmania.add(listMania);
    }
    /**
     * Method to add a new simi=Product to a List of similars-Product
     * @param simiProduct to be added
     */
    public void addNewSimProduct(Product simiProduct) {
        if (this.similar == null) {
            this.similar = new ArrayList<>();
        }
        this.similar.add(simiProduct);
    }
    /**
     * Method to add a new review to a List of reviews
     * @param review to be added
     */
    public void addNewReview(Review review) {
        if (this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
    }
    /**
     * Method to add a new Store to a List of Stores
     * @param store to be added
     */
    public void addNewStore(Store store) {
        if (this.stores == null) {
            this.stores = new ArrayList<>();
        }
        this.stores.add(store);
    }
    /**
     * Method to add  a Store where the product is available to a List of available-in-store
     * @param store to be added
     */
    public void addNewAvailableInStore(Store store) {
        if (this.availableInStores == null) {
            this.availableInStores = new ArrayList<>();
        }
        this.availableInStores.add(store);
    }
    /**
     * Method to add a new categoty to a List of categories
     * @param category to be added
     */
    public void addNewCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(category);
    }

    /**
     * toString method to print a product
     * @return
     */
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
