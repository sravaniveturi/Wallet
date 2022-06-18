package com.tw.money.exception;

public class LowBalanceException  extends Exception{

    private final String message;

    public LowBalanceException() {
        message = "Balance is low.";
    }
}
