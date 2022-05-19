package com.dpb.store.entites;

import com.dpb.store.services.parser.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@IdClass(Store_Id.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String street;
    private int zip;

    @ManyToMany(mappedBy = "stores")
    private Set<Product> products;

    public void addNewProduct(Product product){
        if (products==null){
            products=new HashSet<>();
        }
        products.add(product);
    }
}
