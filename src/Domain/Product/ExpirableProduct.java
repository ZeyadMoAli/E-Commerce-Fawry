package Domain.Product;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements IExpirable{
    private LocalDate expirationDate;
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        setExpirationDate(expirationDate);
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public void setExpirationDate(LocalDate date) {
        if(date.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Expiration date cannot be in the past!");

        this.expirationDate = date;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable() || isExpired();
    }
}
