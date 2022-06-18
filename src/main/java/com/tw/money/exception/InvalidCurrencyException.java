package com.tw.money.exception;

public class InvalidCurrencyException extends Exception {

    private  final String messsage;

    public InvalidCurrencyException() {
        this.messsage = "Currency is invalid.";
    }
}
