package com.codesquad.coco.domain.room.model.dto;

import com.codesquad.coco.utils.LocalDateUtil;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

public class SearchPriceDTO {

    private static final String DEFAULT_CITY_NAME = "서울";
    private static final LocalDate DEFAULT_CHECK_IN = LocalDate.now();
    private static final LocalDate DEFAULT_CHECK_OUT = LocalDate.now().plusMonths(3L);

    protected LocalDate checkIn;
    protected LocalDate checkOut;
    protected String cityName;

    @ConstructorProperties({"check-in", "check-out", "city-name"})
    public SearchPriceDTO(String checkIn, String checkOut, String cityName) {
        this.checkIn = checkIn != null ? LocalDateUtil.StringToLocalDate(checkIn) : DEFAULT_CHECK_IN;
        this.checkOut = checkOut != null ? LocalDateUtil.StringToLocalDate(checkOut) : DEFAULT_CHECK_OUT;
        this.cityName = cityName != null ? cityName : DEFAULT_CITY_NAME;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "SearchInfoDTO{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
