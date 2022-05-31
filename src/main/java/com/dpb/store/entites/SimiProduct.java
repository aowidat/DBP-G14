package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Similar Product Entity
 */
@Entity
@Getter
@Setter
public class SimiProduct {
    @Id
    private String id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    /**
     * Method to add a product as a Similar product
     * @param pr to be added
     */
    public void addNewProduct(Product pr) {
        this.product = pr;
    }
}
