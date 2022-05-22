package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SimiProduct {
    @Id
    private String id;
    private String title;

    @ManyToMany(mappedBy = "similar", cascade = {CascadeType.ALL})
    private List<Product> product;

    public void addNewProduct(Product pr) {
        if (product == null) {
            product = new ArrayList<>();
        }
        product.add(pr);
    }
}
