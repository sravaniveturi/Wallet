package com.tw.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void isAmountInvalid() {
        Money invalidMoney = Money.createMoney(-100, CurrencyType.RUPEES);
        assertTrue(invalidMoney.isAmountInvalid());
    }

    @Test
    void canAddHundredRupeesAndTwoHundredRupess() {
        Money hundredRupees = Money.createMoney(100, CurrencyType.RUPEES);
        Money moneyToAdd = Money.createMoney(200, CurrencyType.RUPEES);

        Money newMoney = hundredRupees.addAmount(moneyToAdd);

        assertEquals(newMoney, Money.createMoney(300, CurrencyType.RUPEES));
    }

    @Test
    void takeHundredRupeesFromTwoHundredRupess() {
        Money twoHundredRupees = Money.createMoney(200, CurrencyType.RUPEES);
        Money hundrerRupees = Money.createMoney(100, CurrencyType.RUPEES);

        Money newMoney = twoHundredRupees.takeAmount(hundrerRupees);

        assertEquals(newMoney, Money.createMoney(100, CurrencyType.RUPEES));
    }

}