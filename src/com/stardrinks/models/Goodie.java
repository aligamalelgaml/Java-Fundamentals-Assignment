package com.stardrinks.models;

import com.stardrinks.base.Product;

public class Goodie extends Product {
    public Goodie(String name, String startMonth, String endMonth) {
        super(name, startMonth, endMonth);
    }

    @Override
    public String toString() {
        return "Goodie: " + super.toString();
    }
}
