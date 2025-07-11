package Domain.Product;

public  class Product {
    private String Name;
    private double Price;
    private int Quantity;
    private double weight;

    public Product (String name, double price, int quantity, double weight){
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setWeight(weight);
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

    public void setWeight(double weight){
        if(weight <= 0)
            throw new IllegalArgumentException("Weight must be greater than zero!");
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }
}
