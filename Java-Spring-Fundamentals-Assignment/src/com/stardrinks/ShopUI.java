package com.stardrinks;

import com.stardrinks.base.Product;
import com.stardrinks.base.ResourceType;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ui")
public class ShopUI {
	@Autowired
    Shop shop;

    public void start() {
        System.out.println("Welcome to Stardrinks!");
        System.out.println("***************************");

        while(true) {
            System.out.println("\nSelect a product category: ");
            System.out.println("1. Drinks");
            System.out.println("2. Coffee Beans");
            System.out.println("3. Goodies");
            System.out.println("4. Show Favorite Products");
            System.out.println("Enter 'exit!' to quit.");

            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit!")) {
                System.out.println("Goodbye! Thank you for visiting.");
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                continue;
            }

            if (choice >= 1 && choice <= 3) {
                ResourceType resourceType = ResourceType.values()[choice - 1];
                List<Product> products = this.shop.getMenu().get(resourceType);

                if (products == null || products.isEmpty()) {
                    System.out.println("No products available in this category.");
                } else {
                    System.out.println("\n" + resourceType + " Menu:");
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println((i + 1) + ". " + products.get(i));
                    }

                    System.out.println("Enter the number of your favorite product to add it to the favorites list (or 'back' to go back to the main menu):");
                    String favoriteInput = scanner.nextLine();

                    if (favoriteInput.equalsIgnoreCase("back")) {
                        continue;
                    }

                    try {
                        int favoriteChoice = Integer.parseInt(favoriteInput);
                        if (favoriteChoice >= 1 && favoriteChoice <= products.size()) {
                            Product favoriteProduct = products.get(favoriteChoice - 1);
                            this.shop.addFavourite(resourceType, favoriteProduct);
                            System.out.println("Added " + favoriteProduct + " to your favorites list!");
                        } else {
                            System.out.println("Invalid input. Please enter a valid option.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid option.");
                    }

                }

            } else if (choice == 4) {
                Set<Product> favourites = this.shop.getFavourites();
                if (favourites.isEmpty()) {
                    System.out.println("You haven't added any products to your favorites yet.");
                } else {
                    System.out.println("Your Favorite Products:");
                    for (Product product : favourites) {
                        System.out.println(product);
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }
}
