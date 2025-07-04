package Domain.Product;

public abstract class Product {
    private String Name;
    private double Price;
    private int Quantity;

    public Product (String name, double price, int quantity){
        this.Name = name;
        setPrice(price);
        setQuantity(quantity);
    }

    protected Product() {
    }

    public String getName() {return Name;}
    public double getPrice() {return Price;}
    public int getQuantity() {return Quantity;}

    public void setQuantity(int quantity) {
        if(quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative!");

        this.Quantity = quantity;
    }
    public void setPrice(double price) {
        if(price < 0)
            throw new IllegalArgumentException("Price cannot be negative!");
        this.Price = price;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public boolean isAvailable() {return Quantity > 0;}


}
