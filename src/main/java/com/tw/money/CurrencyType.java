package com.tw.money;

public enum CurrencyType {
    DOLLAR(78.14), RUPEES(1);

    private final double value;

    CurrencyType(double value) {
        this.value = value;
    }

    public double convertToRupees(double amount) {

        return  amount * this.value;
    }

    public double convert(double amount) {
        System.out.println("hi");
        return amount / this.value ;
    }
}
