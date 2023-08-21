package com.stardrinks.models;


import com.stardrinks.base.Product;

public class Bean extends Product {
    public Bean(String name, String startMonth, String endMonth) {
        super(name, startMonth, endMonth);
    }

    @Override
    public String toString() {
        return "Bean: " + super.toString();
    }
}
