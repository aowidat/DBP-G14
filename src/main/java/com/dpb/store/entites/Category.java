package com.dpb.store.entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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



    @ManyToMany
    @JoinTable(name = "parent", joinColumns = @JoinColumn(name = "categ_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"))
    private List<Category> parents;

    @ManyToMany(mappedBy = "parents")
    private List<Category> parent;
    @ManyToMany
    @JoinTable(name = "children", joinColumns = @JoinColumn(name = "categ_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
    private List<Category> children;

    @ManyToMany(mappedBy = "children")
    private List<Category> child;
//    @ManyToMany(mappedBy = "categories")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private List<Product> products;

    public void addNewItem(Product pr) {
//        if (products == null) {
//            products = new ArrayList<>();
//        }
//        products.add(pr);
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
