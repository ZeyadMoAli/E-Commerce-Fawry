package Application.Interfaces;

import Domain.Product.Product;
import Domain.User.Customer;

import java.util.Map;

public interface ICheckoutService {

    public void checkout(Customer customer);
    public Map<Product, Integer> getShippableProductsFromCart(Map<Product, Integer> cartItems);
    public double calculateShippingCost(Map<Product, Integer> products);
    public double calculateSubTotalCost(Map<Product, Integer> products);
    public void printReceipt (double subTotal, double shippingCost, Map<Product, Integer> cartItems);

}
