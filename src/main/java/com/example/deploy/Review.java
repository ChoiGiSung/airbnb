package com.example.deploy;

public class Review {
    private double star;
    private int review;

    public Review(double star, int review) {
        this.star = star;
        this.review = review;
    }

    public double getStar() {
        return star;
    }

    public int getReview() {
        return review;
    }
}
