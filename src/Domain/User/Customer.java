package Domain.User;

import Domain.Cart.ShoppingCart;
import Domain.Product.Product;

public class Customer {
    private String name;
    private double balance;
    private ShoppingCart shoppingCart;

    public Customer(String name, double balance) {
        this.name = name;
        setBalance(balance);
        shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void addToMyCart(Product product, int quantity){
        shoppingCart.addItem(product, quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance <= 0)
            throw new IllegalArgumentException("Balance must be greater than zero");
        this.balance = balance;
    }

    public void DeductFromBalance(double amount){
        if(amount > balance)
            throw new IllegalArgumentException("Insufficient balance!");
        balance-= amount;
    }
}
