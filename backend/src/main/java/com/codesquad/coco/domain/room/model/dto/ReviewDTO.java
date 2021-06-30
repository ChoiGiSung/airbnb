package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.domain.room.model.Review;

public class ReviewDTO {

    private double star;
    private double review;

    public ReviewDTO(double star, double review) {
        this.star = star;
        this.review = review;
    }

    public static ReviewDTO of(Review review) {
        return new ReviewDTO(
                review.getStar(),
                review.getReview()
        );
    }

    public double getStar() {
        return star;
    }

    public double getReview() {
        return review;
    }
}
