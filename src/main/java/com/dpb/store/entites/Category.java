package com.dpb.store.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Category() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParents(List<Category> parents) {
        this.parents = parents;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getParents() {
        return parents;
    }

    public List<Category> getChildren() {
        return children;
    }

    public List<Product> getProducts() {
        return products;
    }

    @ManyToMany
    private List<Category> parents;
    @ManyToMany
    private List<Category> children;
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public void addNewItem(Product pr) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(pr);
    }

    public void addNewParent(Category cr) {
        if (parents == null) {
            parents = new ArrayList<>();
        }
        parents.add(cr);
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
