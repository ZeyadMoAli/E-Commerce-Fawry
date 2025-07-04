package Domain.Cart;

import Domain.Product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<Product,Integer> cartItems;

    public ShoppingCart(){
        this.cartItems = new HashMap<>();
    }

    public void addItem(Product product, int quantityToBuy){
        if(quantityToBuy <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero!");

        if(quantityToBuy > product.getQuantity())
            throw new IllegalArgumentException("Quantity must be less than or equal to the quantity in stock!");

        if(!product.isAvailable()){
            throw new IllegalArgumentException("Product is not available!");
        }

        int current = cartItems.getOrDefault(product, 0);
        int newQuantity = current + quantityToBuy;
        cartItems.put(product, newQuantity);
        product.decreaseQuantity(quantityToBuy);


    }


    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return Map.copyOf(cartItems);
    }


}
