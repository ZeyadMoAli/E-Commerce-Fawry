import Domain.Cart.ShoppingCart;
import Domain.Product.Product;
import Domain.Product.ShippableProduct;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product tv = new ShippableProduct("tv",50,5,100,50);
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.addItem(tv, 2);
        System.out.println(shoppingCart.getItems().size());
        shoppingCart.addItem(tv, 1);
        System.out.println(shoppingCart.getItems().size());
        shoppingCart.addItem(tv, 0);


    }
}