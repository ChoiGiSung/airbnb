package com.codesquad.coco.domain.room.model;

import org.springframework.data.annotation.Id;

public class Review {

    @Id
    private Long id;

    private double star;
    private double review;

    public Review(double star, double review) {
        this.star = star;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public double getStar() {
        return star;
    }

    public double getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", star=" + star +
                ", review=" + review +
                '}';
    }
}
