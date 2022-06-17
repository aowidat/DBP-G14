package com.dpb.store.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Review Entity
 */
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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product_review;

    @ManyToOne
    @JoinColumn(name = "person")
    private Person person;

    /**
     * Method to add a product to review
     * @param product to be added to a review
     */
    public void addProduct(Product product) {
        this.product_review = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;

        Review review = (Review) o;

        if (getProduct_review() != null ? !getProduct_review().equals(review.getProduct_review()) : review.getProduct_review() != null)
            return false;
        return getPerson().equals(review.getPerson());
    }

    @Override
    public int hashCode() {
        int result = getProduct_review() != null ? getProduct_review().hashCode() : 0;
        result = 31 * result + getPerson().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "summery='" + summery + '\'' +
                ", person=" + person +
                '}';
    }
}
