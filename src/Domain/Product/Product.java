package Domain.Product;

public abstract class Product {
    private String Name;
    private double Price;
    private int Quantity;

    public Product (String name, double price, int quantity){
        setName(name);
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
        this.Name = name.toLowerCase();
    }

    public void decreaseQuantity(int quantity ){
        if(!isAvailable() )
            throw new IllegalArgumentException("Product is not available!");

        if(quantity > Quantity)
            throw new IllegalArgumentException("Quantity must be less than or equal to the quantity in stock!");

        Quantity-= quantity;
    }
    public boolean isAvailable() {return Quantity > 0;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return Name.equals(other.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }
}
