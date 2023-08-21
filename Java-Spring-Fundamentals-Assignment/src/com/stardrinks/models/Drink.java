package com.stardrinks.models;

import com.stardrinks.base.Product;

public class Drink extends Product {

    public Drink(String name, String startMonth, String endMonth) {
        super(name, startMonth, endMonth);
    }

    @Override
    public String toString() {
        return "Drink: " + super.toString();
    }
}
