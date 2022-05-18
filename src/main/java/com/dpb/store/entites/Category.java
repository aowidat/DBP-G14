package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ElementCollection
    private Set<Integer> parents;
    @ElementCollection
    private Set<Integer> children;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

}
