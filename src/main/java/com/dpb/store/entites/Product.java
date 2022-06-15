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
    @Column(columnDefinition = "text", length = 10485760)
    private String image;
    private int rating;
    @ElementCollection
    private List<String> listmania;

    @OneToMany(mappedBy = "product")
    private List<Offer> offers = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "product_store", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> stores;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;
    @OneToMany(mappedBy = "product_review")
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_similar", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "similar_id", referencedColumnName = "id"))
    private List<Product> similar;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Order> orders = new ArrayList<>();
    /**
     * Method to add a new List-Mania to a List of listManias
     *
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
     *
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
     *
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
     *
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
     *
     * @param offer to be added
     */
    public void addNewOffer(Offer offer) {
        offer.setProduct(this);
        offers.add(offer);
    }

    /**
     * Method to add a new categoty to a List of categories
     *
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
     *
     * @return
     */
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!getId().equals(product.getId())) return false;
        return getTitle() != null ? getTitle().equals(product.getTitle()) : product.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }
}
