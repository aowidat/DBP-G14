package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "text", length = 10485760)
    private String content;
    private int helpful;
    private int rating;
    private String summery;
    private LocalDate date;

    @ManyToMany(mappedBy = "reviews")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "person")
    private Person person;

    public void addProduct(Product pr) {
        if (this.product == null) {
            product = new ArrayList<>();
        }
        this.product.add(pr);
    }

}
