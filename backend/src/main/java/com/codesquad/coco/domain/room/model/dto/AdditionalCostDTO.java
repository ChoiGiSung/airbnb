package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.domain.room.model.AdditionalCost;

public class AdditionalCostDTO {

    private int weekSalePercent;
    private int cleaningFee;
    private int serviceFeePercent;
    private int lodgmentFeePercent;

    public AdditionalCostDTO(int weekSalePercent, int cleaningFee, int serviceFeePercent, int lodgmentFeePercent) {
        this.weekSalePercent = weekSalePercent;
        this.cleaningFee = cleaningFee;
        this.serviceFeePercent = serviceFeePercent;
        this.lodgmentFeePercent = lodgmentFeePercent;
    }

    public static AdditionalCostDTO of(AdditionalCost additionalCost) {
        return new AdditionalCostDTO(
                additionalCost.getWeekSalePercent(),
                additionalCost.getCleaningFee(),
                additionalCost.getServiceFeePercent(),
                additionalCost.getLodgmentFeePercent()
        );
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
}
