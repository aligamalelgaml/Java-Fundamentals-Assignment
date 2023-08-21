package com.stardrinks;

import com.stardrinks.base.Product;
import com.stardrinks.base.ResourceType;
import com.stardrinks.models.Bean;
import com.stardrinks.models.Drink;
import com.stardrinks.models.Goodie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Shop {
    private static final String DRINKS_FILE_NAME = "drinks.csv";
    private static final String BEANS_FILE_NAME = "beans.csv";
    private static final String GOODIES_FILE_NAME = "goodies.csv";
    Map<ResourceType, List<Product>> menu = new HashMap<>();
    Set<Product> favourites = new HashSet<>();

    public Map<ResourceType, List<Product>> getMenu() {
        return Collections.unmodifiableMap(this.menu);
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
            Map<ResourceType, Path> dataPaths = getVerifiedResourcesPaths(paths);

            if (!dataPaths.isEmpty()) {
                dataPaths.forEach(this::retrieveStoreData);
                this.retrieveFavouriteData();
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Writes new favourite product to favourites.csv and calls the method to update the internal arraylist containing all favourite products.
     * @param productType The type of the product being added to the favourites.
     * @param product Data of product being added to favourites.
     */
    public void addFavourite(ResourceType productType, Product product) {
        String newProduct = String.format("%s,%s,%s,%s", productType.toString(), product.getName(), product.getStartMonth(), product.getEndMonth());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/favourites.csv", true))) {
            writer.write("\n" + newProduct);
        } catch (IOException e) {
            System.err.println("Error writing favourite product to favourite.csv!");
            e.printStackTrace();
        }

        this.retrieveFavouriteData(); // Updates internal arraylist containing favourite items after writing new items.
    }

    private void retrieveFavouriteData() {
        try(Stream<String> lines = Files.lines(Path.of("src/resources/favourites.csv")).skip(1)) {
            lines.map(line -> line.split(",")).forEach(fields -> {
                ResourceType type = ResourceType.valueOf(fields[0]);
                String name = fields[1];
                String startMonth = fields[2];
                String endMonth = fields[3];

                switch (type) {
                    case DRINKS -> this.favourites.add(new Drink(name, startMonth, endMonth));
                    case GOODIES -> this.favourites.add(new Goodie(name, startMonth, endMonth));
                    case BEANS -> this.favourites.add(new Bean(name, startMonth, endMonth));
                }
            });
        } catch (Exception e) {
            System.err.println("Invalid line format at favourites.csv!");
            e.printStackTrace();
        }
    }

    public Set<Product> getFavourites() {
        return Collections.unmodifiableSet(favourites);
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
    private Map<ResourceType, Path> getVerifiedResourcesPaths(String[] dataPaths) throws IllegalArgumentException {
        try {
            Path drinksResourcePath = Paths.get(dataPaths[0]);
            if (!drinksResourcePath.getFileName().toString().equals(DRINKS_FILE_NAME)) {
                throw new IllegalArgumentException("Invalid drinks resource path!");
            }

            Path beansResourcePath = Paths.get(dataPaths[1]);
            if (!beansResourcePath.getFileName().toString().equals(BEANS_FILE_NAME)) {
                throw new IllegalArgumentException("Invalid beans resource path!");
            }

            Path goodiesResourcePath = Paths.get(dataPaths[2]);
            if (!goodiesResourcePath.getFileName().toString().equals(GOODIES_FILE_NAME)) {
                throw new IllegalArgumentException("Invalid goodies resource path!");
            }

            return Map.of(ResourceType.DRINKS, drinksResourcePath, ResourceType.BEANS, beansResourcePath, ResourceType.GOODIES, goodiesResourcePath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path format: " + e.getMessage());
        }
    }
}
