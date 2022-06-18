package com.tw.money;

import com.tw.money.exception.InvalidCurrencyException;
import com.tw.money.exception.LowBalanceException;

import java.util.Objects;

public class Wallet {
    private double amount;
    private final CurrencyType currencyType;

    public Wallet() {
        this.currencyType = CurrencyType.RUPEES;
    }

    public Wallet(CurrencyType currencyType, double value) {
        this.amount = value;
        this.currencyType = currencyType;
    }

    public void addMoney(CurrencyType currencyType, double amount) throws LowBalanceException {
        if (amount < 0)
            throw new LowBalanceException();
        this.amount += (amount * currencyType.getMultiplier());
    }

    public void takeMoney(CurrencyType currencyType, double amountNeeded) throws LowBalanceException {
        double amountInRupees = convert(amountNeeded, currencyType);
        if (amount < amountInRupees)
            throw new LowBalanceException();
        this.amount -= amountInRupees;
        System.out.println("amount: " + this.amount);
    }

    private double convert(double amount, CurrencyType currencyType) {
        return amount * currencyType.getMultiplier();
    }


    public Wallet findSum(CurrencyType givenCurrency) throws InvalidCurrencyException {
        if (givenCurrency != CurrencyType.DOLLAR && givenCurrency != CurrencyType.RUPEES)
            throw new InvalidCurrencyException();
        double sum = this.amount / givenCurrency.getMultiplier();
        System.out.println("sum:" + sum);
        return new Wallet(givenCurrency, sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.amount, amount) == 0 && currencyType == wallet.currencyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyType);
    }
}