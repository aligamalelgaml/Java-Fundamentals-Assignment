package com.stardrinks;

public class Main {
    public static void main(String[] args)
    {
        Shop stardrinks = new Shop("src/resources/drinks.csv", "src/resources/beans.csv", "src/resources/goodies.csv");
        ShopUI storefront = new ShopUI(stardrinks);
        storefront.start();
    }
}