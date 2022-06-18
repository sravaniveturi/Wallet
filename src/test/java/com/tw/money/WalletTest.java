package com.tw.money;

import com.tw.money.exception.InvalidCurrencyException;
import com.tw.money.exception.LowBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletTest {

    @Test
    void checkAddMoneyHundredDollarsIsSuccessfull() throws LowBalanceException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);

        assertTrue(wallet.equals(new Wallet(CurrencyType.RUPEES, 7814)));
    }

    @Test
    void checkAddMoneyHundredRupeesIsSuccessfull() throws Exception {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.RUPEES, 100);
        wallet.addMoney(CurrencyType.RUPEES, 100);

        assertTrue(wallet.equals(new Wallet(CurrencyType.RUPEES, 200)));
    }

    @Test
    void testAddMoneyNotSuccessfull() throws LowBalanceException {
        Wallet wallet = new Wallet();
        assertThrows(Exception.class, () -> wallet.addMoney(CurrencyType.DOLLAR, -100));
    }

    @Test
    void takeAmountFromWalletIsSuccessfull() throws Exception {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);
        wallet.takeMoney(CurrencyType.RUPEES, 50);

        assertTrue(wallet.equals(new Wallet(CurrencyType.RUPEES, 7764)));
    }

    @Test
    void throwExceptionWithLowBalanceInWallet() throws Exception {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.RUPEES, 100);

        assertThrows(Exception.class, () -> wallet.takeMoney(CurrencyType.RUPEES, 150));
    }

    @Test
    void findSumOfMoneyInWalletInDollars() throws LowBalanceException, InvalidCurrencyException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 50);
        wallet.addMoney(CurrencyType.RUPEES, 78.14);

        assertTrue(new Wallet(CurrencyType.DOLLAR, 51).equals(wallet.findSum(CurrencyType.DOLLAR)));
    }

    @Test
    void findSumOfMoneyInWalletInRupees() throws LowBalanceException, InvalidCurrencyException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);
        wallet.addMoney(CurrencyType.RUPEES, 10);

        assertTrue(new Wallet(CurrencyType.RUPEES, 7824).equals(wallet.findSum(CurrencyType.RUPEES)));
    }

}
