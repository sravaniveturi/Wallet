package com.tw.money;

import com.tw.money.exception.InvalidAmountException;
import com.tw.money.exception.LowBalanceException;

public class Wallet {
    private final Money balance;

    private Wallet(Money money) {
        balance = money;
    }
    public static Wallet createWallet(Money money){
        return new Wallet(money);
    }
    public Wallet addMoney(Money moneyToAdd) throws InvalidAmountException {
        if (moneyToAdd.isAmountInvalid())
            throw new InvalidAmountException();
        Money newAmount = balance.addAmount(moneyToAdd);
         return createWallet(newAmount);
    }

    public Wallet takeMoney(Money moneyToTake) throws LowBalanceException {
        if (balance.isAmountLess(moneyToTake))
            throw new LowBalanceException();
        Money newAmount = balance.takeAmount(moneyToTake);
        return Wallet.createWallet(newAmount);
    }


    public double findBalance(CurrencyType currency) {
        return currency.convert(this.balance.getAmount());
    }


}
