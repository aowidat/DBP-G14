package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "availableInStores")
    private Set<Product> availableProducts;

    public void addNewProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
    }

    public void addNewAvailableProduct(Product product) {
        if (availableProducts == null) {
            availableProducts = new HashSet<>();
        }
        availableProducts.add(product);
    }

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
