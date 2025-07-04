package Application.Services;

import Application.Interfaces.ICheckoutService;
import Application.Interfaces.IShippingService;
import Domain.Interfaces.IShippable;
import Domain.Product.Product;
import Domain.User.Customer;

import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutService implements ICheckoutService {
    final IShippingService shippingService;

    public CheckoutService(IShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Override
    public void checkout(Customer customer) {
        if(customer.getShoppingCart().isEmpty())
            throw new IllegalArgumentException("Shopping cart is empty!");

        double subTotal = calculateSubTotalCost(customer.getShoppingCart().getItems());
        double shippingCost = calculateShippingCost(customer.getShoppingCart().getItems());
        double total = subTotal + shippingCost;
        if(total > customer.getBalance())
            throw new IllegalArgumentException("Insufficient balance!");

        customer.DeductFromBalance(total);

    }

    @Override
    public Map<Product, Integer> getShippableProductsFromCart(Map<Product, Integer> cartItems) {
        return cartItems.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof IShippable)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    @Override
    public double calculateShippingCost(Map<Product, Integer> cartItems) {
        Map<Product, Integer> shippableProductsFromCart = getShippableProductsFromCart(cartItems);
        return shippingService.calculateShippingCost((Map<? extends IShippable, Integer>) shippableProductsFromCart);
    }

    @Override
    public double calculateSubTotalCost(Map<Product, Integer> products) {
        double subTotal = 0;
        for(Map.Entry<Product, Integer> product : products.entrySet()){
            subTotal+= product.getKey().getPrice() * product.getValue();
        }
        return subTotal;
    }


    public void printReceipt(double subTotal, double shippingCost, Map<Product, Integer> cartItems) {
        // === Shipment Notice ===
        System.out.println("** Shipment notice **\n");

        double totalWeight = 0;

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof IShippable) {
                double weight = ((IShippable) product).getWeight();
                double productTotalWeight = weight * quantity;
                totalWeight += productTotalWeight;

                System.out.printf("%dx %s %.0fg\n", quantity, product.getName(), weight * 1000); // grams
            }
        }

        System.out.printf("\nTotal package weight\n%.1fkg\n\n", totalWeight);

        // === Checkout Receipt ===
        System.out.println("** Checkout receipt **\n");


        System.out.println();
        System.out.printf("Subtotal %.0f\n", subTotal);
        System.out.printf("Shipping %.0f\n", shippingCost);
        System.out.printf("Amount %.0f\n", subTotal + shippingCost);
    }

}
