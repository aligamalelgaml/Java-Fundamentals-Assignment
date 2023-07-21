package com.stardrinks;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Shop {
    private List<Path> dataPaths;

    /**
     * Constructor for the Shop class.
     *
     * @param dataPaths Accepts an array of strings where:
     *                  - First string -> drinks resource path,
     *                  - Second string -> beans resource path,
     *                  - Third string -> goodies resource path
     * @throws IllegalArgumentException if any of the paths are invalid or not as expected.
     */
    public Shop(String... dataPaths) {
        try {
            this.dataPaths = verifyPaths(dataPaths);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(dataPaths));
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
    private List<Path> verifyPaths(String[] dataPaths) throws IllegalArgumentException {
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

            return List.of(drinksResourcePath, beansResourcePath, goodiesResourcePath);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path format: " + e.getMessage());
        }
    }
}
