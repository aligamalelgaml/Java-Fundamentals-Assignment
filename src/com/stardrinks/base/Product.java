package com.stardrinks.base;
import java.time.Month;

public class Product {
    private final String name;
    private final Month startMonth;
    private final Month endMonth;
    public Product(String name, String startMonth, String endMonth) {
        this.name = name;
        this.startMonth = Month.valueOf(startMonth.toUpperCase());
        this.endMonth = Month.valueOf(endMonth.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public Month getStartMonth() {
        return startMonth;
    }

    public Month getEndMonth() {
        return endMonth;
    }

    @Override
    public String toString() {
        return String.format("%s, valid from: %s to %s", this.name, this.startMonth.toString(), this.endMonth.toString());
    }
}
