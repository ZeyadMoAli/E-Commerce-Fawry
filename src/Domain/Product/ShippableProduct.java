package Domain.Product;

public class ShippableProduct extends Product implements IShippable{
    private double weight;
    private double shippingCost;

    public ShippableProduct(String name, double price, int quantity, double weight ,double shippingCost) {
        super(name, price, quantity);
        setWeight(weight);
        setShippingCost(shippingCost);
    }
    public void setWeight(double weight){
        if(weight <= 0)
            throw new IllegalArgumentException("Weight must be greater than zero!");
        this.weight = weight;
    }
    public void setShippingCost(double shippingCost){
        if(shippingCost <= 0)
            throw new IllegalArgumentException("Shipping cost cannot be negative!");
        this.shippingCost = shippingCost;
    }

    public double getShippingCost() {
        return shippingCost;
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
