package com.tw.money;

import com.tw.money.exception.InvalidAmountException;
import com.tw.money.exception.LowBalanceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletTest {

    @Test
    void checkAddMoneyHundredDollarsIsSuccessfull() throws InvalidAmountException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);

        assertTrue(wallet.equals(new Wallet(CurrencyType.RUPEES, 7814)));
    }

    @Test
    void checkAddMoneyHundredRupeesIsSuccessfull() throws InvalidAmountException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.RUPEES, 100);
        wallet.addMoney(CurrencyType.RUPEES, 100);

        assertTrue(wallet.equals(new Wallet(CurrencyType.RUPEES, 200)));
    }

    @Test
    void testAddMoneyNotSuccessfull() {
        Wallet wallet = new Wallet();
        assertThrows(InvalidAmountException.class, () -> wallet.addMoney(CurrencyType.DOLLAR, -100));
    }

    @Test
    void takeAmountFromWalletIsSuccessfull() throws InvalidAmountException, LowBalanceException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);

        wallet.takeMoney(CurrencyType.RUPEES, 50);
        Wallet expectedValue = new Wallet(CurrencyType.RUPEES, 7764);

        assertTrue(expectedValue.equals(wallet));
    }

    @Test
    void throwExceptionWithLowBalanceInWallet() throws LowBalanceException, InvalidAmountException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.RUPEES, 100);

        assertThrows(LowBalanceException.class, () -> wallet.takeMoney(CurrencyType.RUPEES, 150));
    }

    @Test
    void findSumOfMoneyInWalletInDollars() throws InvalidAmountException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 50);
        wallet.addMoney(CurrencyType.RUPEES, 78.14);

        Wallet actualValue = wallet.findSum(CurrencyType.DOLLAR);
        Wallet expectedValue = new Wallet(CurrencyType.DOLLAR, 51);

        assertTrue(expectedValue.equals(actualValue));
    }

    @Test
    void findSumOfMoneyInWalletInRupees() throws InvalidAmountException {
        Wallet wallet = new Wallet();
        wallet.addMoney(CurrencyType.DOLLAR, 100);
        wallet.addMoney(CurrencyType.RUPEES, 10);

        Wallet actualValue = wallet.findSum(CurrencyType.RUPEES);
        Wallet expectedValue = new Wallet(CurrencyType.RUPEES, 7824);

        assertTrue(expectedValue.equals(actualValue));
    }

}
