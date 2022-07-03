package com.dpb.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * Category Entity
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "parnet_id")
    @JsonIgnore
    private Category parent;


    @ManyToMany(mappedBy = "categories" , cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    /**
     * Method to add new Product
     * @param product to be added
     */
    public void addNewItem(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    /**
     * Method to add a Parent of Category
     * @param category to be added as a Parent
     */
//    public void addNewParent(Category category) {
//        if (children == null) {
//            children = new ArrayList<>();
//        }
//        children.add(category);
//    }
    /**
     * Method to add a child of Category
     * @param category to be added as a child
     */
    public void addNewChild(Category category) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(category);
    }
    /**
     * ToString method to print a category
     * @return a category as a String
     */
    @Override
    public String toString() {
        return name;
    }
    public void addAllChildren(List<Category> chil){
        children.addAll(chil);
    }
//    public void addAllParent(List<Category> parent){
//        children.addAll(parent);
//    }
    public void addAllItem(List<Product> products ){
        products.addAll(products);
    }

}
