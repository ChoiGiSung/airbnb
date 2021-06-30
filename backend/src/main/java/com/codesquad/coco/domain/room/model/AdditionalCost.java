package com.codesquad.coco.domain.room.model;

import org.springframework.data.annotation.Id;

public class AdditionalCost {

    private static final int ONE_WEEK = 7;

    @Id
    private Long id;

    private int weekSalePercent;
    private int cleaningFee;
    private int serviceFeePercent;
    private int lodgmentFeePercent;

    public AdditionalCost(int weekSalePercent, int cleaningFee, int serviceFeePercent, int lodgmentFeePercent) {
        this.weekSalePercent = weekSalePercent;
        this.cleaningFee = cleaningFee;
        this.serviceFeePercent = serviceFeePercent;
        this.lodgmentFeePercent = lodgmentFeePercent;
    }

    public Money calculateAdditionalCost(Money basicPrice, int fewNights) {

        Money weekSalePrice = calculateWeekSale(basicPrice, fewNights);

        Money lodgmentFee = calculateLodgmentFee(basicPrice);
        Money serviceFee = calculateServiceFee(basicPrice);
        Money cleaningFee = Money.of(this.cleaningFee);

        return basicPrice
                .minus(weekSalePrice)
                .plus(lodgmentFee)
                .plus(serviceFee)
                .plus(cleaningFee);

    }

    public Money calculateWeekSale(Money basicPrice, int fewNights) {
        if (fewNights >= ONE_WEEK) {
            return basicPrice.percent(weekSalePercent);
        }
        return Money.of();
    }

    public Money calculateServiceFee(Money basicPrice) {
        return basicPrice.percent(serviceFeePercent);
    }

    public Money calculateLodgmentFee(Money basicPrice) {
        return basicPrice.percent(lodgmentFeePercent);
    }

    public Long getId() {
        return id;
    }

    public int getWeekSalePercent() {
        return weekSalePercent;
    }

    public int getCleaningFee() {
        return cleaningFee;
    }

    public int getServiceFeePercent() {
        return serviceFeePercent;
    }

    public int getLodgmentFeePercent() {
        return lodgmentFeePercent;
    }

    @Override
    public String toString() {
        return "AdditionalCost{" +
                "id=" + id +
                ", weekSalePercent=" + weekSalePercent +
                ", cleaningFee=" + cleaningFee +
                ", serviceFeePercent=" + serviceFeePercent +
                ", lodgmentFeePercent=" + lodgmentFeePercent +
                '}';
    }


}
