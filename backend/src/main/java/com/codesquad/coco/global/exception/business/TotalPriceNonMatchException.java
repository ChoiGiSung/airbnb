package com.codesquad.coco.global.exception.business;

import com.codesquad.coco.global.exception.ErrorCode;
import com.codesquad.coco.domain.room.model.Money;

public class TotalPriceNonMatchException extends BusinessException {

    public TotalPriceNonMatchException(Money totalPrice) {
        super(ErrorCode.TOTAL_PRICE_NON_MATCH.plusMessage(String.valueOf(totalPrice.getMoney())));
    }

}
