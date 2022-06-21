package com.tw.money;

import com.tw.money.exception.InvalidAmountException;
import com.tw.money.exception.LowBalanceException;

public class Wallet {
    private Money balance;

    public Wallet() {
        balance = new Money();
    }

    public void addMoney(Money moneyToAdd) throws InvalidAmountException {
        if (moneyToAdd.isAmountInvalid())
            throw new InvalidAmountException();
        balance.addAmount(moneyToAdd);
    }

    public void takeMoney(Money moneyToTake) throws LowBalanceException {
        if (balance.isAmountLess(moneyToTake))
            throw new LowBalanceException();
        balance.takeAmount(moneyToTake);
    }


    public Money findSum(CurrencyType givenCurrency) {
        double sum = (balance.getAmount() / givenCurrency.getMultiplier());
        return new Money(sum, givenCurrency);
    }

    public Money getBalance() {
        return balance.getMoney();
    }
}
