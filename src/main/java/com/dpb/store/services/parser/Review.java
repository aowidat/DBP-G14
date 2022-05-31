package com.dpb.store.services.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A class to represent one review, which has product_id rating, helpful, reviewdate, user_id, summery and content
 */
@Getter
@Setter
@AllArgsConstructor
public class Review {
    private String product;
    private String rating;
    private String helpful;
    private String reviewdate;
    private String user;
    private String summery;
    private String content;
    /**
     * ToString Method to print a Review
     * @return Review as a String
     */
    @Override
    public String toString() {
        return "Review{" +
                "product='" + product + '\'' +
                ", rating='" + rating + '\'' +
                ", helpful='" + helpful + '\'' +
                ", reviewdate='" + reviewdate + '\'' +
                ", user='" + user + '\'' +
                ", summery='" + summery + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
