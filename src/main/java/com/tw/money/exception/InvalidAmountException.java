package com.tw.money.exception;

public class InvalidAmountException extends Exception {

    private  final String messsage;

    public InvalidAmountException() {
        this.messsage = "Amount is invalid.";
    }
}
