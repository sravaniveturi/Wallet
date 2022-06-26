package com.tw.money;

import java.util.Objects;

public class Money {

    private final double amount;
    private final CurrencyType currencyType;


    private Money(double amount, CurrencyType currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public static Money createMoney(double amount, CurrencyType currencyType){
        return new Money(amount, currencyType);
    }

    public boolean isAmountInvalid() {
        return (this.amount < 0);
    }

    public Money addAmount(Money moneyToAdd) {
        double newAmount = this.amount + (moneyToAdd.currencyType.convertToRupees(moneyToAdd.amount));
        return createMoney(newAmount, CurrencyType.RUPEES);
    }

    public Money takeAmount(Money moneyToTake) {
        double amountInRupees = this.amount - moneyToTake.currencyType.convertToRupees(moneyToTake.amount);
        return createMoney(amountInRupees, CurrencyType.RUPEES);
    }

    public boolean isAmountLess(Money money) {
        double amountInRupees = money.currencyType.convert(money.amount);
        return (this.amount < amountInRupees);
    }

    public double getAmount() {
        return amount;
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
}
