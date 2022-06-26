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
        Money money = Money.createMoney(0, CurrencyType.RUPEES);
        wallet = Wallet.createWallet(money);
    }

    @Test
    void canAddHundredRupeesToWallet() throws InvalidAmountException {
        Money hundredRupees = Money.createMoney(100, CurrencyType.RUPEES);
        Wallet newWallet = wallet.addMoney(hundredRupees);

        assertEquals(100, newWallet.findBalance(CurrencyType.RUPEES));
    }

    @Test
    void shouldThrowExceptionWhenAddInvalidAmount() {
        Money invalidMoney = Money.createMoney(-100, CurrencyType.DOLLAR);
        assertThrows(InvalidAmountException.class, () -> wallet.addMoney(invalidMoney));
    }

    @Test
    void canTakeAmountFiftyRupeesFromWallet() throws InvalidAmountException, LowBalanceException {
        Money hundredRupees = Money.createMoney(100, CurrencyType.RUPEES);
        Money fiftyRupees = Money.createMoney(50, CurrencyType.RUPEES);

        Wallet wallet = Wallet.createWallet(hundredRupees);
        Wallet actualWallet = wallet.takeMoney(fiftyRupees);

        assertEquals(50, actualWallet.findBalance(CurrencyType.RUPEES));
    }

    @Test
    void throwExceptionWithLowBalanceInWallet() throws InvalidAmountException {
        Money hundredRupees = Money.createMoney(100, CurrencyType.RUPEES);
        Money hundredFiftyRupees = Money.createMoney(150, CurrencyType.RUPEES);

        wallet.addMoney(hundredRupees);

        assertThrows(LowBalanceException.class, () -> wallet.takeMoney(hundredFiftyRupees));
    }

    @Test
    void findWalletBalanceInDollars() throws InvalidAmountException {
        Money seventyEightRupees = Money.createMoney(78.14, CurrencyType.RUPEES);

        Wallet newWallet = wallet.addMoney(seventyEightRupees);

        assertEquals(1, newWallet.findBalance(CurrencyType.DOLLAR));
    }

    @Test
    void findWalletBalanceInRupees() throws InvalidAmountException {
        Money hundredDollars = Money.createMoney(100, CurrencyType.DOLLAR);

        Wallet newWallet = wallet.addMoney(hundredDollars);

        assertEquals(7814, newWallet.findBalance(CurrencyType.RUPEES));
    }

}
