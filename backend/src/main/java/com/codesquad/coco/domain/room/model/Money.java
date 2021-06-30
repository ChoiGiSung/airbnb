package com.codesquad.coco.domain.room.model;

import java.util.Objects;

public class Money {

    private static final Money MIN_PRICE = new Money(0);
    private static final Money MAX_PRICE = new Money(999_999_999);

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public static Money minPrice(Integer money) {
        if (Objects.isNull(money)) {
            return MIN_PRICE;
        }
        return new Money(money);
    }

    public static Money maxPrice(Integer money) {
        if (Objects.isNull(money)) {
            return MAX_PRICE;
        }
        return new Money(money);
    }

    public static Money of() {
        return MIN_PRICE;
    }

    public static Money of(int money) {
        return new Money(money);
    }

    public Money plus(Money money) {
        return new Money(this.money + money.money);
    }

    public Money minus(Money money) {
        return new Money(this.money - money.money);
    }

    public Money multiplication(int number) {
        return new Money(number * money);
    }

    public Money percent(int percent) {
        return new Money((int) (money * (percent / 100.0)));
    }

    public boolean same(int money) {
        return this.money == money;
    }


    public int getMoney() {
        return money;
    }

    public Money setMoney(int money) {
        return new Money(money);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }
}
