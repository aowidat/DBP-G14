package com.dpb.store.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Category() {
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "parent_of_category", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"))
    private List<Category> up_categories;

    @ManyToMany(mappedBy = "up_categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> children;

//    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.PERSIST})
//    private List<Product> products;

    public void addNewItem(Product pr) {
//        if (products == null) {
//            products = new ArrayList<>();
//        }
//        products.add(pr);
    }

    public void addNewParent(Category cr) {
        if (up_categories == null) {
            up_categories = new ArrayList<>();
        }
        up_categories.add(cr);
    }

    public void addNewChild(Category cr) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(cr);
    }

    @Override
    public String toString() {
        return "Name:" + name;
    }
}
