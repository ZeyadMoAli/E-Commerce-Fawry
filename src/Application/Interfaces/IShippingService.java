package Application.Interfaces;

import Domain.Interfaces.IShippable;
import Domain.Product.Product;
import Domain.Product.ShippableProduct;

import java.util.List;
import java.util.Map;

public interface IShippingService {
    final double RATE_PER_KG = 2.0;

    double calculateShippingCost(Map<Product, Integer>  products);
}
