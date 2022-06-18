package com.tw.money;

public enum CurrencyType {
    DOLLAR(78.14), RUPEES(1);

    private final double value;

    CurrencyType(double value) {
        this.value = value;
    }

    public double getMultiplier() {
        return  this.value;
    }
}
