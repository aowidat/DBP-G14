package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Store Entity
 */

@Entity
@Getter
@Setter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String street;
    private String zip;

    @ManyToMany(mappedBy = "stores")
    private Set<Product> products;

    @OneToMany(mappedBy = "store")
    private List<Offer> offers = new ArrayList<>();

    /**
     * Method to add a product to a HashSet of Products
     * @param product to be added
     */
    public void addNewProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
    }
    public void addNewOffer(Offer offer){
        offer.setStore(this);
        offers.add(offer);
    }

    /**
     * toString method to print a product
     * @return a product as a String
     */
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zip=" + zip +
                '}';
    }
}
