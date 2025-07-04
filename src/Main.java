import Application.Interfaces.ICheckoutService;
import Application.Services.CheckoutService;
import Application.Services.ShippingService;
import Domain.Product.ExpirableProduct;
import Domain.Product.ExpirableShippableProduct;
import Domain.Product.Product;
import Domain.Product.ShippableProduct;
import Domain.User.Customer;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running Test Cases...");

        ICheckoutService checkoutService = new CheckoutService(new ShippingService());

        // Test Case 1: Normal checkout process
        try {
            Customer customer1 = new Customer("Alice", 500.0);
            Product tv = new ShippableProduct("TV", 200.0, 3, 10.0);

            customer1.addToMyCart(tv, 1);
            checkoutService.checkout(customer1);
            System.out.println("Test Case 1: PASSED");
        } catch (Exception e) {
            System.out.println("Test Case 1: FAILED - " + e.getMessage());
        }
        System.out.println();
        // Test Case 2: Insufficient balance for checkout
        try {
            Customer customer2 = new Customer("Bob", 100.0);
            Product fridge = new ShippableProduct("Fridge", 300.0, 2, 30.0);

            customer2.addToMyCart(fridge, 1);
            checkoutService.checkout(customer2);
            System.out.println("Test Case 2: FAILED");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 2: PASSED - " + e.getMessage());
        }
        System.out.println();

        // Test Case 3: Adding multiple products to the cart
        try {
            Customer customer3 = new Customer("Charlie", 1000.0);
            Product laptop = new ShippableProduct("Laptop", 800.0, 5, 5.0);
            Product mouse = new ShippableProduct("Mouse", 50.0, 10, 0.5);

            customer3.addToMyCart(laptop, 1);
            customer3.addToMyCart(mouse, 2);
            checkoutService.checkout(customer3);
            System.out.println("Test Case 3: PASSED");
        } catch (Exception e) {
            System.out.println("Test Case 3: FAILED - " + e.getMessage());
        }
        System.out.println();

        // Test Case 4: Checking expired product
        try {
            ExpirableProduct milk = new ExpirableProduct("Milk", 2.0, 10, 2.0, LocalDate.of(2025, 1, 1));
            System.out.println("Test Case 4: isExpired = " + milk.isExpired());
        } catch (Exception e) {
            System.out.println("Test Case 4: FAILED - " + e.getMessage());
        }
        System.out.println();

        // Test Case 5: Cart with expired and valid products
        try {
            Customer customer5 = new Customer("Eve", 300.0);
            Product bread = new ExpirableProduct("Bread", 3.0, 5, 1.0, LocalDate.of(2027, 1, 1));
            Product expiredMilk = new ExpirableProduct("Expired Milk", 2.5, 10, 2.0, LocalDate.of(2023, 6, 1));

            customer5.addToMyCart(bread, 2);
            customer5.addToMyCart(expiredMilk, 1);
            System.out.println("Test Case 5: Cannot checkout expired product");
            checkoutService.checkout(customer5);
        } catch (Exception e) {
            System.out.println("Test Case 5: PASSED - " + e.getMessage());
        }
        System.out.println();
        System.out.println();

        // Test Case 6: Empty shopping cart checkout
        try {
            Customer customer6 = new Customer("John", 200.0);
            System.out.println("Test Case 6. Empty shopping cart");
            checkoutService.checkout(customer6);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 6: PASSED - " + e.getMessage());
        }

        System.out.println();
        // Test Case 7: Zero quantity product handling
        try {
            Customer customer7 = new Customer("Mike", 200.0);
            Product book = new ShippableProduct("Book", 10.0, 0, 0.2);
            customer7.addToMyCart(book, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 7: PASSED - " + e.getMessage());
        }
        System.out.println();

        // Test Case 8: Edge case for exact balance match
        try {
            Customer customer8 = new Customer("Linda", 250.0);
            Product phone = new Product("Smartphone", 250.0, 1, 2.0);
            customer8.addToMyCart(phone, 1);
            checkoutService.checkout(customer8);
            System.out.println("Test Case 8: PASSED");
        } catch (Exception e) {
            System.out.println("Test Case 8: FAILED - " + e.getMessage());
        }


        System.out.println();
        // Test Case 9: ExpirableShippableProduct functionality
        try {
            Customer customer9 = new Customer("George", 500.0);

            ExpirableShippableProduct expiredMedicine = new ExpirableShippableProduct(
                    "Expired Medicine", 50.0, 5, 1.0, LocalDate.of(2024, 6, 1) // expired
            );
            ExpirableShippableProduct validVaccine = new ExpirableShippableProduct(
                    "Vaccine", 200.0, 3, 0.5, LocalDate.of(2027, 12, 31) // not expired
            );

            customer9.addToMyCart(expiredMedicine, 1);
            customer9.addToMyCart(validVaccine, 1);

            System.out.println("Test Case 9: Testing ExpirableShippableProduct functionality...");
            checkoutService.checkout(customer9);
            System.out.println("Test Case 9: FAILED - Expired product should not allow checkout");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 9: PASSED - " + e.getMessage());
        }

        System.out.println();
        // Test Case 10: Normal scenario for ExpirableShippableProduct
        try {
            Customer customer10 = new Customer("Sophia", 1000.0);

            ExpirableShippableProduct freshMedicine = new ExpirableShippableProduct(
                    "Painkiller", 100.0, 10, 2.0, LocalDate.of(2028, 1, 1)
            );

            customer10.addToMyCart(freshMedicine, 2);
            System.out.println("Test Case 10: Testing normal ExpirableShippableProduct scenario...");
            checkoutService.checkout(customer10);
            System.out.println("Test Case 10: PASSED - Checkout completed successfully");
        } catch (Exception e) {
            System.out.println("Test Case 10: FAILED - " + e.getMessage());
        }


        System.out.println();
        System.out.println("All Test Cases Completed");
    }
}