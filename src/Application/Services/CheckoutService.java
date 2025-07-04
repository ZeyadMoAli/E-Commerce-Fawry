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

        printReceipt(customer, subTotal, shippingCost, customer.getShoppingCart().getItems());

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
        return shippingService.calculateShippingCost( shippableProductsFromCart);
    }

    @Override
    public double calculateSubTotalCost(Map<Product, Integer> products) {
        double subTotal = 0;
        for(Map.Entry<Product, Integer> product : products.entrySet()){
            subTotal+= product.getKey().getPrice() * product.getValue();
        }
        return subTotal;
    }


    public void printReceipt(Customer customer,double subTotal, double shippingCost, Map<Product, Integer> cartItems) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof IShippable) {
                double weight = ((IShippable) product).getWeight();
                double productTotalWeight = weight * quantity;
                totalWeight += productTotalWeight;

                System.out.println( quantity+"x "+ product.getName()+" "+ weight * 1000 * quantity + "g");
            }
        }

        System.out.println("Total package weight " + totalWeight+"kg");
        System.out.println();

        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

                double weight =  product.getWeight();
                double productTotalWeight = weight * quantity;
                totalWeight += productTotalWeight;

                System.out.println( quantity+"x "+ product.getName()+" "+ weight * 1000+ "g");
        }

        System.out.println();
        System.out.println("Subtotal " + subTotal);
        System.out.println("Shipping " + shippingCost);
        System.out.println("Amount " + (subTotal + shippingCost));
        System.out.println("New Customer Balance " + customer.getBalance());
    }

}
