package Application.Services;

import Application.Interfaces.IShippingService;
import Domain.Interfaces.IShippable;
import Domain.Product.Product;
import Domain.Product.ShippableProduct;

import java.util.List;
import java.util.Map;

public class ShippingService implements IShippingService {
    double total = 0;

    @Override
    public double calculateShippingCost(Map<Product, Integer> products) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            IShippable shippable = (IShippable) product;
            total += shippable.getWeight() * RATE_PER_KG * quantity;

        }
        return total;
    }

}
