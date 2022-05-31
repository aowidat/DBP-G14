package com.dpb.store.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parent_of_category", joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private List<Category> up_categories;

    @ManyToMany(mappedBy = "up_categories",cascade = CascadeType.ALL)
    private List<Category> children;

    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.MERGE})
    private List<Product> products;

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
    public void addNewParent(Category category) {
        if (up_categories == null) {
            up_categories = new ArrayList<>();
        }
        up_categories.add(category);
    }
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
        return "Name:" + name;
    }
}
