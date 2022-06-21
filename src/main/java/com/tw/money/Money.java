package com.tw.money;

import java.util.Objects;

public class Money {

    private double amount;
    private CurrencyType currencyType;

    public Money() {

    }

    public Money(double amount, CurrencyType currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public boolean isAmountInvalid() {
        return (this.amount < 0);
    }

    public void addAmount(Money moneyToAdd) {
        this.amount += (moneyToAdd.amount * moneyToAdd.currencyType.getMultiplier());
        this.currencyType = CurrencyType.RUPEES;
    }

    public void takeAmount(Money moneyToTake) {
        double amountInRupees = moneyToTake.convert();
        this.amount -= amountInRupees;
    }

    public boolean isAmountLess(Money money) {
        double amountInRupees = money.convert();
        return (this.amount < amountInRupees);
    }

    private double convert() {
        return (this.amount * this.currencyType.getMultiplier());
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 && currencyType == money.currencyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyType);
    }

    public Money getMoney() {
        return this;
    }
}
