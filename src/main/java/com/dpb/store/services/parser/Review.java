package com.dpb.store.services.parser;

public class Review {
    private String product;
    private String rating;
    private String helpful;
    private String reviewdate;
    private String user;
    private String summery;
    private String content;

    public Review(String product, String rating, String helpful, String reviewdate, String user, String summery, String content) {
        this.product = product;
        this.rating = rating;
        this.helpful = helpful;
        this.reviewdate = reviewdate;
        this.user = user;
        this.summery = summery;
        this.content = content;
    }

    public String getProduct() {
        return product;
    }

    public String getRating() {
        return rating;
    }

    public String getHelpful() {
        return helpful;
    }

    public String getReviewdate() {
        return reviewdate;
    }

    public String getUser() {
        return user;
    }

    public String getSummery() {
        return summery;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setHelpful(String helpful) {
        this.helpful = helpful;
    }

    public void setReviewdate(String reviewdate) {
        this.reviewdate = reviewdate;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

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
