package Domain.Product;

import Domain.Interfaces.IShippable;

public class ShippableProduct extends Product implements IShippable {

    public ShippableProduct(String name, double price, int quantity, double weight ) {
        super(name, price, quantity,weight);
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }


}
