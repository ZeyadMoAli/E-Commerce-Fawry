package Domain.Product;

import Domain.Interfaces.IShippable;

public class ShippableProduct extends Product implements IShippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight ) {
        super(name, price, quantity);
        setWeight(weight);
    }
    public void setWeight(double weight){
        if(weight <= 0)
            throw new IllegalArgumentException("Weight must be greater than zero!");
        this.weight = weight;
    }


    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
