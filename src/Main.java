import Application.Interfaces.ICheckoutService;
import Application.Services.CheckoutService;
import Application.Services.ShippingService;
import Domain.Cart.ShoppingCart;
import Domain.Product.ExpirableProduct;
import Domain.Product.Product;
import Domain.Product.ShippableProduct;
import Domain.User.Customer;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ICheckoutService checkoutService = new CheckoutService(new ShippingService());
        Product tv = new ShippableProduct("tv",50,5,1);
        ExpirableProduct apple = new ExpirableProduct("Apple",10,10,0.002 , LocalDate.of(2027,12,12));



        Customer customer = new Customer("John",1000);
        customer.addToMyCart(tv,1);
        customer.addToMyCart(tv,1);
        customer.addToMyCart(tv,1);
        customer.addToMyCart(tv,1);
        customer.addToMyCart(tv,1);


        checkoutService.checkout(customer);





    }
}