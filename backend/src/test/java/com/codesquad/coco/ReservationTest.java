package com.codesquad.coco;

import com.codesquad.coco.global.exception.business.AlreadyReserved;
import com.codesquad.coco.domain.room.model.AdditionalCost;
import com.codesquad.coco.domain.room.model.Money;
import com.codesquad.coco.domain.room.model.ReservationList;
import com.codesquad.coco.domain.room.model.Room;
import com.codesquad.coco.domain.user.model.Reservation;
import com.codesquad.coco.utils.LocalDateUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


public class ReservationTest {


    @Test
    @DisplayName("기본 요금 추가가 잘 되는지")
    void totalPrice() {
        Room room = getRoom();
        int fewNights = 1;

        Money totalPrice = room.calcTotalPrice(fewNights);

        assertThat(totalPrice.getMoney()).isEqualTo(128_000);

    }

    @Test
    @DisplayName("일주일 이상의 요금이 잘 할인되는지")
    void oneWeekTotalPrice() {
        Room room = getRoom();

        int fewNights = 8;

        Money totalPrice = room.calcTotalPrice(fewNights);

        assertThat(totalPrice.getMoney()).isEqualTo(852_000);

    }

    @Test
    @DisplayName("예약 불가 날짜에 예외를 확인")
    void checkReservationAvailable() {
        Room room = getRoom();
        ReservationList reservations = room.getReservations();
        assertThatThrownBy(() -> reservations.reservationDateCheck(
                LocalDate.of(2021, 05, 25)
                , LocalDate.of(2021, 05, 27)))
                .isInstanceOf(AlreadyReserved.class)
                .hasMessageContaining("그 날에는 예약이 이미 있음");

        assertThatThrownBy(() -> reservations.reservationDateCheck(
                LocalDate.of(2021, 04, 25)
                , LocalDate.of(2021, 05, 30)))
                .isInstanceOf(AlreadyReserved.class)
                .hasMessageContaining("그 날에는 예약이 이미 있음");
    }


    @Test
    @DisplayName("체크아웃 날에는 체크인이 가능해야한다")
    void checkInOnCheckOutDay() {
        Reservation reservation = getReservation();

        boolean chekInTest = reservation.reservationDateCheck(
                LocalDate.of(2021, 05, 01),
                LocalDate.of(2021, 05, 25)
        );

        boolean chekOutTest = reservation.reservationDateCheck(
                LocalDate.of(2021, 05, 27),
                LocalDate.of(2021, 05, 28)
        );

        assertAll(
                () -> assertFalse(chekInTest),
                () -> assertFalse(chekOutTest)
        );
    }

    @Test
    @DisplayName("날짜가 중간에 들어가는지, 예약을 할 수 있는 날인지 체크")
    void dateIsBetween() {
        LocalDate targetDate1 = LocalDate.of(2021, 05, 26);
        LocalDate targetDate2 = LocalDate.of(2021, 05, 28);
        LocalDate targetDate3 = LocalDate.of(2021, 05, 30);
        LocalDate checkIn = LocalDate.of(2021, 05, 27);
        LocalDate checkOut = LocalDate.of(2021, 05, 29);

        boolean before = LocalDateUtil.betweenCheck(targetDate1, checkIn, checkOut);
        boolean middle = LocalDateUtil.betweenCheck(targetDate2, checkIn, checkOut);
        boolean after = LocalDateUtil.betweenCheck(targetDate3, checkIn, checkOut);

        assertAll(
                () -> assertFalse(before),
                () -> assertTrue(middle),
                () -> assertFalse(after)
        );
    }


    @Test
    @DisplayName("날짜가 동일한 날이면 안된다")
    void dateIsSameDate() {
        LocalDate checkIn = LocalDate.of(2021, 05, 27);
        LocalDate checkOut = LocalDate.of(2021, 05, 29);

        boolean checkInSameDate = LocalDateUtil.betweenCheck(checkIn, checkIn, checkOut);

        assertAll(
                () -> assertFalse(checkInSameDate)
        );
    }

    @NotNull
    private Room getRoom() {
        Room room = new Room.Builder()
                .additionalCost(new AdditionalCost(4, 20_000, 4, 4))
                .pricePerDate(new Money(100_000))
                .build();
        room.addReservation(getReservation());
        return room;
    }

    private Reservation getReservation() {
        return new Reservation.Builder()
                .checkIn(LocalDate.of(2021, 05, 25))
                .checkOut(LocalDate.of(2021, 05, 27)
                ).builder();
    }
}
