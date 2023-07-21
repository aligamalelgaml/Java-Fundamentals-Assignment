package com.stardrinks;

import com.stardrinks.base.Product;
import com.stardrinks.base.ResourceType;
import com.stardrinks.models.Bean;
import com.stardrinks.models.Drink;
import com.stardrinks.models.Goodie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Shop {
    Map<ResourceType, List<Product>> menu = new HashMap<>();

    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("=====");

        for (Map.Entry<ResourceType, List<Product>> entry : this.menu.entrySet()) {
            ResourceType resourceType = entry.getKey();
            List<Product> products = entry.getValue();

            for (Product product : products) {
                System.out.println(product);
            }

            System.out.println();
        }
        System.out.println("=====");
    }

    /**
     * Constructor for the Shop class.
     *
     * @param paths Accepts an array of strings where:
     *                  - First string -> drinks resource path,
     *                  - Second string -> beans resource path,
     *                  - Third string -> goodies resource path
     * @throws IllegalArgumentException if any of the paths are invalid or not as expected.
     */
    public Shop(String... paths) {
        try {
            Map<ResourceType, Path> dataPaths = verifyPaths(paths);

            if (!dataPaths.isEmpty())
                dataPaths.forEach(this::retrieveStoreData);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void retrieveStoreData(ResourceType productType, Path path) {

        try(Stream<String> lines = Files.lines(path).skip(1)) {
            List<Product> productList = this.menu.computeIfAbsent(productType, k -> new ArrayList<>());

            lines.map(line -> line.split(","))
                    .forEach(fields -> {
                        String name = fields[0];
                        String startMonth = fields[1];
                        String endMonth = fields[2];

                        switch (productType) {
                            case DRINKS -> productList.add(new Drink(name, startMonth, endMonth));
                            case GOODIES -> productList.add(new Goodie(name, startMonth, endMonth));
                            case BEANS -> productList.add(new Bean(name, startMonth, endMonth));
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Verify the provided data paths and return a list of Path objects.
     *
     * @param dataPaths Accepts an array of strings where:
     *                  - First string -> drinks resource path,
     *                  - Second string -> beans resource path,
     *                  - Third string -> goodies resource path
     * @return A list of Path objects for the valid resource paths.
     * @throws IllegalArgumentException if any of the paths are invalid or not as expected.
     */
    private Map<ResourceType, Path> verifyPaths(String[] dataPaths) throws IllegalArgumentException {
        try {
            Path drinksResourcePath = Paths.get(dataPaths[0]);
            if (!drinksResourcePath.getFileName().toString().equals("drinks.csv")) {
                throw new IllegalArgumentException("Invalid drinks resource path!");
            }

            Path beansResourcePath = Paths.get(dataPaths[1]);
            if (!beansResourcePath.getFileName().toString().equals("beans.csv")) {
                throw new IllegalArgumentException("Invalid beans resource path!");
            }

            Path goodiesResourcePath = Paths.get(dataPaths[2]);
            if (!goodiesResourcePath.getFileName().toString().equals("goodies.csv")) {
                throw new IllegalArgumentException("Invalid goodies resource path!");
            }

            return Map.of(ResourceType.DRINKS, drinksResourcePath, ResourceType.BEANS, beansResourcePath, ResourceType.GOODIES, goodiesResourcePath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path format: " + e.getMessage());
        }
    }
}
