package com.tw.money;

import com.tw.money.exception.InvalidAmountException;
import com.tw.money.exception.LowBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    Wallet wallet;

    @BeforeEach
    void setUp(){
        wallet = new Wallet();
    }

    @Test
    void testCanAddHundredDollarsToWallet() throws InvalidAmountException {
        Money hundredRupees = new Money(100, CurrencyType.RUPEES);
        wallet.addMoney(hundredRupees);

        assertEquals(wallet.getBalance(),hundredRupees);
    }

    @Test
    void shouldThrowExceptionWhenAddAmountNotSuccessFul() {
        Money invalidMoney = new Money(-100, CurrencyType.DOLLAR);
        assertThrows(InvalidAmountException.class, () -> wallet.addMoney(invalidMoney));
    }

    @Test
    void testCanTakeAmountFiftyRupeesFromWallet() throws InvalidAmountException, LowBalanceException {
        Money hundredRupees = new Money(100, CurrencyType.RUPEES);
        Money fiftyRupees = new Money(50, CurrencyType.RUPEES);

        wallet.addMoney(hundredRupees);
        wallet.takeMoney(fiftyRupees);

        assertEquals(wallet.getBalance(),fiftyRupees);
    }

    @Test
    void throwExceptionWithLowBalanceInWallet() throws InvalidAmountException {
        Money hundredRupees = new Money(100, CurrencyType.RUPEES);
        Money hundredFiftyRupees = new Money(150, CurrencyType.RUPEES);

        wallet.addMoney(hundredRupees);

        assertThrows(LowBalanceException.class, () -> wallet.takeMoney(hundredFiftyRupees));
    }

    @Test
    void findSumOfMoneyInWalletInDollars() throws InvalidAmountException {
        Money fiftyDollars = new Money(50, CurrencyType.DOLLAR);
        Money seventyEightRupees = new Money(78.14, CurrencyType.RUPEES);

        wallet.addMoney(fiftyDollars);
        wallet.addMoney(seventyEightRupees);

        Money actualValue = wallet.findSum(CurrencyType.DOLLAR);
        Money expectedValue = new Money(51, CurrencyType.DOLLAR);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    void findSumOfMoneyInWalletInRupees() throws InvalidAmountException {
        Money hundredDollars = new Money(100, CurrencyType.DOLLAR);
        Money tenRupees = new Money(10, CurrencyType.RUPEES);


        wallet.addMoney(hundredDollars);
        wallet.addMoney(tenRupees);

        Money actualValue = wallet.findSum(CurrencyType.RUPEES);
        Money expectedValue = new Money(7824, CurrencyType.RUPEES);

        assertEquals(expectedValue, actualValue);
    }

}
