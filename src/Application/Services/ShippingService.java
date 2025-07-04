package Application.Services;

import Application.Interfaces.IShippingService;
import Domain.Interfaces.IShippable;

import java.util.List;
import java.util.Map;

public class ShippingService implements IShippingService {
    double total = 0;

    @Override
    public double calculateShippingCost(Map<? extends IShippable, Integer> products) {
        for(Map.Entry<? extends IShippable, Integer> product : products.entrySet()){
            total+= product.getKey().getWeight() * RATE_PER_KG * product.getValue();
        }
        return total;
    }
}
