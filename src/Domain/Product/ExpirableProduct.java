package Domain.Product;

import Domain.Interfaces.IExpirable;

import java.time.LocalDate;

public class ExpirableProduct extends Product implements IExpirable {
    private LocalDate expirationDate;
    public ExpirableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity, weight);
        setExpirationDate(expirationDate);
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public void setExpirationDate(LocalDate date) {
        this.expirationDate = date;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable() && !isExpired();
    }
}
